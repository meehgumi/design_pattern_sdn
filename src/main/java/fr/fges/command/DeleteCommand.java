package fr.fges.command;
import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private final GameCollection collection;
    private BoardGame deletedGame;

    public DeleteCommand(GameCollection collection) {
        this.collection = collection;
    }

    public String getLabel() { return "Delete a game"; }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title of game to delete: ");
        String title = sc.nextLine();
        this.deletedGame = collection.removeGame(title);
        if (this.deletedGame != null) {
            System.out.println("Jeu supprime !");
        } else {
            System.out.println("Jeu non trouve.");
        }
    }

    public void undo() {
        if (this.deletedGame != null) {
            collection.addGame(this.deletedGame);
            System.out.println("Undo: " + this.deletedGame.title() + " restaure.");
        }
    }
}