package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameCollection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SummaryCommand implements Command {
    public String getLabel() {
        return "Weekend summary";
    }

    public void execute() {
        DayOfWeek jour = LocalDate.now().getDayOfWeek();
        if (jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY) {
            System.out.println("Weekend summary");
            List<BoardGame> copie = new ArrayList<>(GameCollection.getGames());
            Collections.shuffle(copie);
            if (copie.size() < 3) {
                System.out.println("Not enough games for the summary.");
            } else {
                for (int i = 0; i < 3; i++) {
                    System.out.println("- " + copie.get(i).title());
                }
            }
        }
    }
}
