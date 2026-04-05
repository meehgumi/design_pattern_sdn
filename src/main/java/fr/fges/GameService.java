package fr.fges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GameService {
    private final GameCollection collection;

    public GameService(GameCollection collection) {
        this.collection = collection;
    }

    public List<BoardGame> getAllGames() {
        return collection.getGames();
    }

    public void addGame(BoardGame game) {
        boolean alreadyExists = collection.getGames().stream()
            .anyMatch(g -> g.title().equalsIgnoreCase(game.title()));
        if (alreadyExists) {
            throw new IllegalArgumentException("A game with this title already exists.");
        }
        collection.addGame(game);
    }

    public BoardGame removeGame(String title) {
        return collection.removeGame(title);
    }

    public List<BoardGame> getGamesForPlayers(int nbPlayers) {
        return collection.getGames().stream()
            .filter(g -> g.minPlayers() <= nbPlayers && g.maxPlayers() >= nbPlayers)
            .toList();
    }

    public Optional<BoardGame> recommendGame(int nbPlayers) {
        List<BoardGame> compatibles = getGamesForPlayers(nbPlayers);
        if (compatibles.isEmpty()) return Optional.empty();
        int index = (int) (Math.random() * compatibles.size());
        return Optional.of(compatibles.get(index));
    }

    public List<BoardGame> getRandomGames(int count) {
        List<BoardGame> copie = new ArrayList<>(collection.getGames());
        Collections.shuffle(copie);
        return copie.stream().limit(count).toList();
    }
}
