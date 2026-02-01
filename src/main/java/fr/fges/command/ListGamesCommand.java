package fr.fges.command;
import fr.fges.GameCollection;
import fr.fges.BoardGame;

public class ListGamesCommand implements Command {
    @Override
    public String getLabel() { return "Lister les jeux"; }

    @Override
    public void execute() {
        var games = GameCollection.getGames();
        if (games.isEmpty()) {
            System.out.println("La collection est vide.");
        } else {
            games.forEach(g -> System.out.println("- " + g.title() + " (" + g.category() + ")"));
        }
    }
}