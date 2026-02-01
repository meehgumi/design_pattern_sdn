package fr.fges;
import fr.fges.storage.*;
import java.util.*;

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
        games.add(game);
        save();
    }

    public static void removeGame(String title) {
        games.removeIf(g -> g.title().equalsIgnoreCase(title));
        save();
    }

    private static void save() {
        strategy.save(storageFile, games);
    }
}