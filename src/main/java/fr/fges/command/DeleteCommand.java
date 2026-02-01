package fr.fges.command;
import fr.fges.GameCollection;
import java.util.Scanner;

public class DeleteCommand implements Command {
    @Override
    public String getLabel() { return "Supprimer un jeu"; }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Titre du jeu à supprimer : ");
        String title = sc.nextLine();
        GameCollection.removeGame(title);
        System.out.println("C'est fait !");
    }
}