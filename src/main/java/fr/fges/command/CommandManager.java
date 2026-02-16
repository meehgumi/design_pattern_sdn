package fr.fges.command;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class CommandManager {
    private final List<Command> commands;
    private final Scanner scanner;

    public CommandManager() {
        this.scanner = new Scanner(System.in);
        this.commands = new ArrayList<>();
        initCommands();
    }

    private void initCommands() {
        commands.add(new AddGameCommand());
        commands.add(new ListGamesCommand());
        commands.add(new DeleteCommand());
        commands.add(new RecommandGameCommand());
        commands.add(new GameForXPlayersCommand());

        // Ajoute SummaryCommand seulement le weekend
        DayOfWeek jour = LocalDate.now().getDayOfWeek();
        if (jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY) {
            commands.add(new SummaryCommand());
        }
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
            commands.get(choix - 1).execute();
        } else {
            System.out.println("Unknown option.");
        }
    }
}