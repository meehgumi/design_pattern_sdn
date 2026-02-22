package fr.fges.tournament;

import fr.fges.Player;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class KingOfTheHillFormat implements TournamentFormat {

    @Override
    public List<Player> play(List<Player> players, Scanner sc) {
        Deque<Player> queue = new ArrayDeque<>(players);
        Player king = queue.poll();
        int matchNum = 1;

        while (!queue.isEmpty()) {
            Player challenger = queue.poll();

            System.out.println("\n=== Match " + matchNum + " ===");
            System.out.println(king.getName() + " (tenant) vs " + challenger.getName());
            System.out.print("Winner (1=" + king.getName() + ", 2=" + challenger.getName() + "): ");

            int choix = lireChoix(sc, 1, 2);
            if (choix == 1) {
                king.addVictory();
                challenger.addDefeat();
            } else {
                challenger.addVictory();
                king.addDefeat();
                king = challenger;
            }
            matchNum++;
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
            System.out.print("Choix invalide, réessayez (" + min + "-" + max + "): ");
        }
    }
}