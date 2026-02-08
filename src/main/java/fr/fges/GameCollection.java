package fr.fges;
import fr.fges.storage.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameCollection {
    private static final List<BoardGame> games = new ArrayList<>();
    private static String storageFile;
    private static StorageStrategy strategy;

    public static void init(String file) {
        storageFile = file;
        strategy = file.endsWith(".json") ? new JsonStorage() : new CsvStorage();
        games.addAll(strategy.load(storageFile));
    }

    public static List<BoardGame> getGames() { return games; }

    public static void addGame(BoardGame game) {
        boolean alreadyExists = games.stream()
            .anyMatch(g -> g.title().equalsIgnoreCase(game.title()));
        if (alreadyExists) {
            throw new IllegalArgumentException("A game with this title already exists."); // interrompt la commande 
        }
        games.add(game);
        save();
    }

    public static BoardGame removeGame(String title) {
        for (Iterator<BoardGame> it = games.iterator(); it.hasNext(); ) {
            BoardGame game = it.next();
            if (game.title().equalsIgnoreCase(title)) {
                it.remove();
                save();
                return game;
            }
        }
        return null;
    }

    private static void save() {
        strategy.save(storageFile, games);
    }
}