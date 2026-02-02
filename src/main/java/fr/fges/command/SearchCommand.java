package fr.fges.command;
import fr.fges.GameCollection;
import java.util.Scanner;

public class SearchCommand implements Command {
    @Override
    public String getLabel() { return "Search by category"; }

    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Category: ");
        String cat = sc.nextLine();
        GameCollection.getGames().stream()
            .filter(g -> g.category().equalsIgnoreCase(cat))
            .forEach(g -> System.out.println("- " + g.title()));
    }
}