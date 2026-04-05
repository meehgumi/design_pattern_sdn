package fr.fges.command;
import fr.fges.BoardGame;
import fr.fges.GameService;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private final GameService service;
    private BoardGame deletedGame;

    public DeleteCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() { return "Delete a game"; }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title of game to delete: ");
        String title = sc.nextLine();
        this.deletedGame = service.removeGame(title);
        if (this.deletedGame != null) {
            System.out.println("Jeu supprime !");
        } else {
            System.out.println("Jeu non trouve.");
        }
    }

    public void undo() {
        if (this.deletedGame != null) {
            service.addGame(this.deletedGame);
            System.out.println("Undo: " + this.deletedGame.title() + " restaure.");
        }
    }
}