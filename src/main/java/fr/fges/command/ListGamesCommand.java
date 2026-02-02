package fr.fges.command;
import fr.fges.GameCollection;

public class ListGamesCommand implements Command {
    @Override
    public String getLabel() { return "List games"; }

    @Override
    public void execute() {
        var games = GameCollection.getGames();
        if (games.isEmpty()) {
            System.out.println("The collection is empty.");
        } else {
            games.forEach(g -> System.out.println("- " + g.title() + " (" + g.category() + ")"));
        }
    }
}