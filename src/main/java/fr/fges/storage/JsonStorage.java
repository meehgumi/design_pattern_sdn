package fr.fges.storage;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.fges.BoardGame;
import java.io.File;
import java.util.*;

public class JsonStorage implements StorageStrategy {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void save(String path, List<BoardGame> games) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), games);
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<BoardGame> load(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(mapper.readValue(f, BoardGame[].class)));
        } catch (Exception e) { return new ArrayList<>(); }
    }
}