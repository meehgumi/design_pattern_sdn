package fr.fges.command;
import fr.fges.GameCollection;

public class StatsCommand implements Command {
    @Override
    public String getLabel() { return "View statistics"; }

    @Override
    public void execute() {
        var games = GameCollection.getGames();
        System.out.println("Total number of games: " + games.size());
        double avg = games.stream().mapToInt(g -> g.minPlayers()).average().orElse(0);
        System.out.println("Average min players: " + String.format("%.2f", avg));
    }
}