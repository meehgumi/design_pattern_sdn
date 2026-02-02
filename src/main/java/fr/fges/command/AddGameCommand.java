package fr.fges.command;
import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.util.Scanner;

public class AddGameCommand implements Command {
    @Override
    public String getLabel() { return "Add a game"; }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Title: "); String t = sc.nextLine();
            System.out.print("Min players: "); int min = Integer.parseInt(sc.nextLine());
            System.out.print("Max players: "); int max = Integer.parseInt(sc.nextLine());
            System.out.print("Category: "); String c = sc.nextLine();
            GameCollection.addGame(new BoardGame(t, min, max, c));
        } catch (Exception e) { System.out.println("Input error."); }
    }
}
