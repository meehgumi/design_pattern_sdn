package fr.fges;
import fr.fges.storage.StorageStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameCollection {
    private final List<BoardGame> games = new ArrayList<>();
    private final String storageFile;
    private final StorageStrategy strategy;

    public GameCollection(List<BoardGame> initialGames, StorageStrategy strategy, String storageFile) {
        this.games.addAll(initialGames);
        this.strategy = strategy;
        this.storageFile = storageFile;
    }

    public List<BoardGame> getGames() { return games; }

    public void addGame(BoardGame game) {
        games.add(game);
        save();
    }

    public BoardGame removeGame(String title) {
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

    private void save() {
        strategy.save(storageFile, games);
    }
}