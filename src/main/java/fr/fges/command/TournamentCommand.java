package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import fr.fges.Player;
import fr.fges.tournament.ChampionshipFormat;
import fr.fges.tournament.KingOfTheHillFormat;
import fr.fges.tournament.TournamentFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TournamentCommand implements Command {
    private final GameCollection collection;

    public TournamentCommand(GameCollection collection) {
        this.collection = collection;
    }

    @Override
    public String getLabel() {
        return "Tournament Mode";
    }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);

        // 1. Filtrer les jeux compatibles 2 joueurs
        List<BoardGame> twoPlayerGames = collection.getGames().stream()
                .filter(g -> g.minPlayers() <= 2 && g.maxPlayers() >= 2)
                .toList();

        if (twoPlayerGames.isEmpty()) {
            System.out.println("Aucun jeu compatible 2 joueurs dans la collection.");
            return;
        }

        // 2. Choisir un jeu
        System.out.println("\n=== Tournament Mode ===");
        System.out.println("Available 2-player games:");
        for (int i = 0; i < twoPlayerGames.size(); i++) {
            BoardGame g = twoPlayerGames.get(i);
            System.out.println((i + 1) + ". " + g.title()
                    + " (" + g.minPlayers() + "-" + g.maxPlayers()
                    + " players, " + g.category() + ")");
        }
        System.out.print("Select game (1-" + twoPlayerGames.size() + "): ");
        int gameChoice = lireChoix(sc, 1, twoPlayerGames.size());
        BoardGame selectedGame = twoPlayerGames.get(gameChoice - 1);

        // 3. Nombre de participants (3-8)
        System.out.print("\nNumber of participants (3-8): ");
        int nbPlayers = lireChoix(sc, 3, 8);

        // 4. Saisir les noms des joueurs
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= nbPlayers; i++) {
            System.out.print("Enter player " + i + " name: ");
            String name = sc.nextLine().trim();
            players.add(new Player(name));
        }

        // 5. Choisir le format
        System.out.println("\nChoose format:");
        System.out.println("1. Championship (everyone plays everyone)");
        System.out.println("2. King of the Hill (winner stays)");
        System.out.print("Select format (1-2): ");
        int formatChoice = lireChoix(sc, 1, 2);
        TournamentFormat format = formatChoice == 1
                ? new ChampionshipFormat()
                : new KingOfTheHillFormat();

        // 6. Lancer le tournoi
        List<Player> results = format.play(players, sc);

        // 7. Afficher le classement
        System.out.println("\n=== Tournament Results ===");
        for (int i = 0; i < results.size(); i++) {
            Player p = results.get(i);
            System.out.println((i + 1) + ". " + p.getName()
                    + " - " + p.getPoints() + " points ("
                    + p.getWins() + " win" + (p.getWins() > 1 ? "s" : "") + ")");
        }
    
    }

    private int lireChoix(Scanner sc, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.print("Choix invalide, réessayez (" + min + "-" + max + "): ");
        }
    }
}