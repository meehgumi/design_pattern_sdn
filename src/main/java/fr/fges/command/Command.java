package fr.fges.command;

import java.util.Scanner;
import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.Menu;

public class Command {

    private final Scanner scanner = new Scanner(System.in);

    private String getUserInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

    public void addGame() {
        String title = getUserInput("Title");
        String minPlayersStr = getUserInput("Minimum Players");
        String maxPlayersStr = getUserInput("Maximum Players");
        String category = getUserInput("Category (e.g., fantasy, cooperative, family, strategy)");
        int minPlayers = Integer.parseInt(minPlayersStr);
        int maxPlayers = Integer.parseInt(maxPlayersStr);
        BoardGame game = new BoardGame(title, minPlayers, maxPlayers, category);
        GameCollection.addGame(game);
        System.out.println("Board game added successfully.");
    }

    public void removeGame() {
        String title = getUserInput("Title of game to remove");
        var games = GameCollection.getGames();
        for (BoardGame game : games) {
            if (game.title().equals(title)) {
                GameCollection.removeGame(game);
                System.out.println("Board game removed successfully.");
                return;
            }
        }
        System.out.println("No board game found with that title.");
    }

    public void listAllGames() {
        GameCollection.viewAllGames();
    }

    public void exit() {
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    public void handleMenu() {
        Menu.displayMainMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> addGame();
            case "2" -> removeGame();
            case "3" -> listAllGames();
            case "4" -> exit();
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}