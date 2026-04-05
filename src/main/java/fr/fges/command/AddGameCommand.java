package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameService;
import java.util.Scanner;

public class AddGameCommand implements Command {
    private final GameService service;
    private BoardGame gameAdded;

    public AddGameCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() {
        return "Add a game";
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Titre : ");
            String t = sc.nextLine();
            System.out.print("Min joueurs : ");
            int min = Integer.parseInt(sc.nextLine());
            System.out.print("Max joueurs : ");
            int max = Integer.parseInt(sc.nextLine());
            System.out.print("Catégorie : ");
            String c = sc.nextLine();
            this.gameAdded = new BoardGame(t, min, max, c);
            service.addGame(this.gameAdded);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.gameAdded = null;
        } catch (Exception e) {
            System.out.println("Input error.");
        }
    }

    public void undo() {
        if (this.gameAdded != null) {
            service.removeGame(this.gameAdded.title());
            System.out.println("Undo: " + this.gameAdded.title() + " retiré.");
        }
    }
}