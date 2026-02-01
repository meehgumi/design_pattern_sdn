package fr.fges.storage;
import fr.fges.BoardGame;
import java.util.List;

public interface StorageStrategy {
    void save(String path, List<BoardGame> games);
    List<BoardGame> load(String path);
}