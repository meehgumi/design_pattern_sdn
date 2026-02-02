package fr.fges.command;

import fr.fges.GameCollection;
import java.util.Scanner;

public class RecommandGameCommand implements Command {
    @Override
    public String getLabel() {
        return "Recommand a game";
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players :");
        int nbPlayers = Integer.parseInt(sc.nextLine());
        GameCollection.getGames()
        .stream()
        .filter(g -> g.minPlayers() <= nbPlayers && g.maxPlayers() >= nbPlayers)
        .forEach(g -> System.out.println("- " + g.title()));
    }

}
