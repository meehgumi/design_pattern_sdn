package fr.fges.command;
import fr.fges.GameCollection;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public String getLabel() { return "Delete a game"; }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Title of game to delete: ");
        String title = sc.nextLine();
        GameCollection.removeGame(title);
        System.out.println("Done!");
    }
}