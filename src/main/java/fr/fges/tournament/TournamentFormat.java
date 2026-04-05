package fr.fges.tournament;

import fr.fges.Player;
import java.util.List;
import java.util.Scanner;

//Appelle le format du tournoi, championnat ou king of the hill
public interface TournamentFormat {
    List<Player> play(List<Player> players, Scanner sc);
}
