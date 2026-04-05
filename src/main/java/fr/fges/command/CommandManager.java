package fr.fges.command;

import fr.fges.GameService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class CommandManager {
    private final List<Command> commands;
    private final Scanner scanner;
    private final Deque<Command> undoStack = new ArrayDeque<>();

    public CommandManager(GameService service) {
        this.scanner = new Scanner(System.in);
        this.commands = new ArrayList<>();
        initCommands(service);
    }

    private void initCommands(GameService service) {
        commands.add(new AddGameCommand(service));
        commands.add(new ListGamesCommand(service));
        commands.add(new DeleteCommand(service));
        commands.add(new RecommandGameCommand(service));
        commands.add(new GameForXPlayersCommand(service));

        // Ajoute SummaryCommand seulement le weekend
        DayOfWeek jour = LocalDate.now().getDayOfWeek();
        if (jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY) {
            commands.add(new SummaryCommand(service));
        }

        commands.add(new TournamentCommand(service));
        commands.add(new UndoCommand(undoStack));
    }

    public void run() {
        while (true) {
            afficherMenu();

            try {
                String input = scanner.nextLine();
                int choix = Integer.parseInt(input);

                if (choix == 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                executerCommande(choix);
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println("\n=== COLLECTION MANAGEMENT ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i).getLabel());
        }
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    private void executerCommande(int choix) {
        if (choix > 0 && choix <= commands.size()) {
            Command cmd = commands.get(choix - 1);
            cmd.execute();
            undoStack.push(cmd);
        } else {
            System.out.println("Unknown option.");
        }
    }
}