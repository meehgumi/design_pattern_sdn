package fr.fges.command;
import fr.fges.GameCollection;

public class ListGamesCommand implements Command {
    private final GameCollection collection;

    public ListGamesCommand(GameCollection collection) {
        this.collection = collection;
    }

    public String getLabel() { return "List games"; }

    public void execute() {
        var games = collection.getGames();
        if (games.isEmpty()) {
            System.out.println("The collection is empty.");
        } else {
            games.forEach(g -> System.out.println("- " + g.title() + " (" + g.category() + ")"));
        }
    }
}