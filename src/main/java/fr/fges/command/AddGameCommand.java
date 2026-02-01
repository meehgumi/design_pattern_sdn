package fr.fges.command;
import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.util.Scanner;

public class AddGameCommand implements Command {
    @Override
    public String getLabel() { return "Ajouter un jeu"; }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Titre : "); String t = sc.nextLine();
            System.out.print("Min joueurs : "); int min = Integer.parseInt(sc.nextLine());
            System.out.print("Max joueurs : "); int max = Integer.parseInt(sc.nextLine());
            System.out.print("Catégorie : "); String c = sc.nextLine();
            GameCollection.addGame(new BoardGame(t, min, max, c));
        } catch (Exception e) { System.out.println("Erreur de saisie."); }
    }
}
