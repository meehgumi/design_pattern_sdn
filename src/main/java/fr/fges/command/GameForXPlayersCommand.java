package fr.fges.command;

import fr.fges.GameService;
import java.util.Scanner;

public class GameForXPlayersCommand implements Command {
    private final GameService service;

    public GameForXPlayersCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() {
        return "Games for X players";
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players : ");
        int nbPlayers = Integer.parseInt(sc.nextLine());
        service.getGamesForPlayers(nbPlayers)
                .forEach(g -> System.out.println("- " + g.title()));
    }
}