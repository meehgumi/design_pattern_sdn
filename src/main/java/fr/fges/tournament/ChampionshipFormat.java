package fr.fges.tournament;

import fr.fges.Player;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ChampionshipFormat implements TournamentFormat {

    @Override
    public List<Player> play(List<Player> players, Scanner sc) {
        int total = (players.size() * (players.size() - 1)) / 2;
        int matchNum = 1;

        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player p1 = players.get(i);
                Player p2 = players.get(j);

                System.out.println("\n=== Match " + matchNum + "/" + total + " ===");
                System.out.println(p1.getName() + " vs " + p2.getName());
                System.out.print("Winner (1=" + p1.getName() + ", 2=" + p2.getName() + "): ");

                int choix = lireChoix(sc, 1, 2);
                if (choix == 1) {
                    p1.addVictory();
                    p2.addDefeat();
                } else {
                    p2.addVictory();
                    p1.addDefeat();
                }
                matchNum++;
            }
        }

        players.sort(Comparator
                .comparingInt(Player::getPoints).reversed()
                .thenComparingInt(Player::getWins).reversed()
                .thenComparing(Player::getName));

        return players;
    }

    private int lireChoix(Scanner sc, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.print("Invalid choice, try again (" + min + "-" + max + "): ");
        }
    }
}
