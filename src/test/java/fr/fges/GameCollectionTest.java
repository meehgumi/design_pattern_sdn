package fr.fges;

import fr.fges.storage.StorageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameCollectionTest {

    private GameCollection collection;

    // StorageStrategy sans fichier pour les tests
    private static final StorageStrategy NO_OP = new StorageStrategy() {
        public List<BoardGame> load(String file) { return new ArrayList<>(); }
        public void save(String file, List<BoardGame> games) {}
    };

    @BeforeEach
    void setUp() {
        collection = new GameCollection(new ArrayList<>(), NO_OP, "test");
    }

    @Test
    void shouldAddGameToCollection() {
        collection.addGame(new BoardGame("Terraforming Mars", 1, 5, "Strategy"));

        assertEquals(1, collection.getGames().size());
        assertEquals("Terraforming Mars", collection.getGames().get(0).title());
    }

    @Test
    void shouldRemoveGameByTitle() {
        collection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        collection.addGame(new BoardGame("Dixit", 3, 6, "Party"));

        collection.removeGame("Catan");

        assertEquals(1, collection.getGames().size());
        assertFalse(collection.getGames().stream().anyMatch(g -> g.title().equals("Catan")));
    }

    @Test
    void shouldRemoveCaseInsensitive() {
        collection.addGame(new BoardGame("Catan", 3, 4, "Family"));

        BoardGame removed = collection.removeGame("catan");

        assertNotNull(removed);
        assertTrue(collection.getGames().isEmpty());
    }

    @Test
    void shouldReturnNullWhenGameNotFound() {
        assertNull(collection.removeGame("Monopoly"));
    }
}
