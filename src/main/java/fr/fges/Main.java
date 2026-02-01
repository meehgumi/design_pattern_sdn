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
            new SearchCommand(),
            new StatsCommand()
        );

        while (true) {
            System.out.println("\n=== GESTION DE COLLECTION ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getLabel());
            }
            System.out.println("0. Quitter");
            System.out.print("Choix : ");

            try {
                String input = sc.nextLine();
                int choix = Integer.parseInt(input);
                
                if (choix == 0) {
                    System.out.println("Au revoir !");
                    break;
                }
                
                if (choix > 0 && choix <= commands.size()) {
                    commands.get(choix - 1).execute();
                } else {
                    System.out.println("Option inconnue.");
                }
            } catch (Exception e) {
                System.out.println("Veuillez saisir un nombre valide.");
            }
        }
    }
}