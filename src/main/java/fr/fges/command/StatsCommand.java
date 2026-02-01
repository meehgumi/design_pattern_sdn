package fr.fges.command;
import fr.fges.GameCollection;

public class StatsCommand implements Command {
    @Override
    public String getLabel() { return "Voir les statistiques"; }

    @Override
    public void execute() {
        var games = GameCollection.getGames();
        System.out.println("Nombre total de jeux : " + games.size());
        double avg = games.stream().mapToInt(g -> g.minPlayers()).average().orElse(0);
        System.out.println("Moyenne joueurs minimum : " + String.format("%.2f", avg));
    }
}