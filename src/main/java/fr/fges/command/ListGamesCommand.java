package fr.fges.command;
import fr.fges.GameService;

public class ListGamesCommand implements Command {
    private final GameService service;

    public ListGamesCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() { return "List games"; }

    public void execute() {
        var games = service.getAllGames();
        if (games.isEmpty()) {
            System.out.println("The collection is empty.");
        } else {
            games.forEach(g -> System.out.println("- " + g.title() + " (" + g.category() + ")"));
        }
    }
}