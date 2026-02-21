package fr.fges.command;
import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.util.Deque;
import java.util.Scanner;

public class DeleteCommand implements Command {
    private final Deque<Runnable> undoStack;
    private BoardGame deletedGame;

    public DeleteCommand(Deque<Runnable> undoStack) {
        this.undoStack = undoStack;
    }

    public String getLabel() { return "Delete a game"; }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title of game to delete: ");
        String title = sc.nextLine();
        this.deletedGame = GameCollection.removeGame(title);
        if (this.deletedGame != null) {
            System.out.println("Jeu supprime !");
            BoardGame removed = this.deletedGame;
            undoStack.push(() -> {
                GameCollection.addGame(removed);
                System.out.println("Undo: " + removed.title() + " restaure.");
            });
        } else {
            System.out.println("Jeu non trouve.");
        }
    }

    public void undo() {
        if (this.deletedGame != null) {
            GameCollection.addGame(this.deletedGame);
            System.out.println("Undo: " + this.deletedGame.title() + " restaure.");
        }
    }
}