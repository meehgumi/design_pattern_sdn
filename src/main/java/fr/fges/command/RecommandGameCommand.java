package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.util.List;
import java.util.Scanner;

public class RecommandGameCommand implements Command {
    public String getLabel() {
        return "Recommand a game";
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players :");
        int nbPlayers = Integer.parseInt(sc.nextLine());
        List<BoardGame> compatibles = GameCollection.getGames()
                .stream()
                .filter(g -> g.minPlayers() <= nbPlayers && g.maxPlayers() >= nbPlayers)
                .toList();
        if (compatibles.isEmpty()) {
            System.out.println("Aucun jeu trouvé.");
        } else {
            int index = (int) (Math.random() * compatibles.size());
            BoardGame jeu = compatibles.get(index);
            System.out.println("- " + jeu.title());
        }
    }
}