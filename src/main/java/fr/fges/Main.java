package fr.fges;

import fr.fges.command.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar boardgames.jar <file.json/.csv>");
            return;
        }

        GameCollection.init(args[0]);
        Scanner sc = new Scanner(System.in);
        
        List<Command> commands = Arrays.asList(
            new AddGameCommand(),
            new ListGamesCommand(),
            new DeleteCommand(),
            new SearchCommand()
        );

        while (true) {
            System.out.println("\n=== COLLECTION MANAGEMENT ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getLabel());
            }
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            try {
                String input = sc.nextLine();
                int choix = Integer.parseInt(input);
                
                if (choix == 0) {
                    System.out.println("Goodbye!");
                    break;
                }
                
                if (choix > 0 && choix <= commands.size()) {
                    commands.get(choix - 1).execute();
                } else {
                    System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}