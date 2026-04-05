package fr.fges;

import fr.fges.command.*;
import fr.fges.storage.StorageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameCommandsTest {

    private GameService service;

    private static final StorageStrategy NO_OP = new StorageStrategy() {
        public List<BoardGame> load(String file) { return new ArrayList<>(); }
        public void save(String file, List<BoardGame> games) {}
    };

    @BeforeEach
    void setUp() {
        GameCollection collection = new GameCollection(new ArrayList<>(), NO_OP, "test");
        service = new GameService(collection);
    }

    // --- Labels ---

    @Test
    void testListLabel() {
        assertEquals("List games", new ListGamesCommand(service).getLabel());
    }

    @Test
    void testAddLabel() {
        assertEquals("Add a game", new AddGameCommand(service).getLabel());
    }

    @Test
    void testDeleteLabel() {
        assertEquals("Delete a game", new DeleteCommand(service).getLabel());
    }

    @Test
    void testGameForXPlayersLabel() {
        assertEquals("Games for X players", new GameForXPlayersCommand(service).getLabel());
    }

    @Test
    void testRecommandLabel() {
        assertEquals("Recommand a game", new RecommandGameCommand(service).getLabel());
    }

    @Test
    void testSummaryLabel() {
        assertEquals("Weekend summary", new SummaryCommand(service).getLabel());
    }

    @Test
    void testUndoLabel() {
        assertEquals("Undo last action", new UndoCommand(new ArrayDeque<>()).getLabel());
    }

    // --- Logique métier (GameService) ---

    @Test
    void shouldAddGame() {
        service.addGame(new BoardGame("Catan", 3, 4, "Family"));
        assertEquals(1, service.getAllGames().size());
    }

    @Test
    void shouldRejectDuplicateTitle() {
        service.addGame(new BoardGame("Catan", 3, 4, "Family"));
        assertThrows(IllegalArgumentException.class, () ->
            service.addGame(new BoardGame("Catan", 2, 6, "Strategy"))
        );
    }

    @Test
    void shouldRemoveGame() {
        service.addGame(new BoardGame("Catan", 3, 4, "Family"));
        service.removeGame("Catan");
        assertEquals(0, service.getAllGames().size());
    }

    @Test
    void shouldReturnNullWhenRemovingAbsentGame() {
        assertNull(service.removeGame("Monopoly"));
    }

    @Test
    void shouldFilterGamesByPlayerCount() {
        service.addGame(new BoardGame("Petit jeu", 2, 3, "Family"));
        service.addGame(new BoardGame("Moyen jeu", 3, 5, "Strategy"));
        service.addGame(new BoardGame("Grand jeu", 6, 10, "Party"));

        List<BoardGame> result = service.getGamesForPlayers(4);

        assertEquals(1, result.size());
        assertEquals("Moyen jeu", result.get(0).title());
    }

    @Test
    void shouldRecommendGameForCompatiblePlayerCount() {
        service.addGame(new BoardGame("Catan", 3, 4, "Family"));

        assertTrue(service.recommendGame(3).isPresent());
        assertTrue(service.recommendGame(5).isEmpty());
    }

    // --- Undo ---

    @Test
    void shouldUndoAddWithEmptyStackDoesNotThrow() {
        assertDoesNotThrow(() -> new UndoCommand(new ArrayDeque<>()).execute());
    }
}
