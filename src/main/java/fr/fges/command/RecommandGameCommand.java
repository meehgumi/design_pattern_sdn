package fr.fges.command;

import fr.fges.GameService;
import java.util.Scanner;

public class RecommandGameCommand implements Command {
    private final GameService service;

    public RecommandGameCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() {
        return "Recommand a game";
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of players :");
        int nbPlayers = Integer.parseInt(sc.nextLine());
        service.recommendGame(nbPlayers)
                .ifPresentOrElse(
                    g -> System.out.println("- " + g.title()),
                    () -> System.out.println("No games found")
                );
    }
}