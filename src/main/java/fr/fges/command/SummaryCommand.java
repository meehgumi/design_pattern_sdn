package fr.fges.command;

import fr.fges.BoardGame;
import fr.fges.GameService;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class SummaryCommand implements Command {
    private final GameService service;

    public SummaryCommand(GameService service) {
        this.service = service;
    }

    public String getLabel() {
        return "Weekend summary";
    }

    public void execute() {
        DayOfWeek jour = LocalDate.now().getDayOfWeek();
        if (jour == DayOfWeek.SATURDAY || jour == DayOfWeek.SUNDAY) {
            System.out.println("Weekend summary");
            List<BoardGame> selection = service.getRandomGames(3);
            if (selection.size() < 3) {
                System.out.println("Not enough games for the summary.");
            } else {
                selection.forEach(g -> System.out.println("- " + g.title()));
            }
        }
    }
}
