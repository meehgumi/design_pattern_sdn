package fr.fges.tournament;

import fr.fges.Player;
import java.util.List;
import java.util.Scanner;

public interface TournamentFormat {
    List<Player> play(List<Player> players, Scanner sc);
}
