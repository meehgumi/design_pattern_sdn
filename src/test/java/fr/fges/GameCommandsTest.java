package fr.fges;

import fr.fges.command.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;

import static org.junit.jupiter.api.Assertions.*;

class GameCommandsTest {

    @BeforeEach
    void setUp() {
        GameCollection.init("test.json");
        GameCollection.getGames().clear();
    }

    @Test
    void testListLabel() {
        assertEquals("List games", new ListGamesCommand().getLabel());
    }

    @Test
    void testAddLabel() {
        assertEquals("Add a game", new AddGameCommand(new ArrayDeque<>()).getLabel());
    }

    @Test
    void testAddGame() {
        GameCollection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        assertEquals(1, GameCollection.getGames().size());
    }

    @Test
    void testAddDuplicate() {
        GameCollection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        assertThrows(IllegalArgumentException.class, () -> {
            GameCollection.addGame(new BoardGame("Catan", 2, 6, "Strategy"));
        });
    }

    @Test
    void testDeleteLabel() {
        assertEquals("Delete a game", new DeleteCommand(new ArrayDeque<>()).getLabel());
    }

    @Test
    void testDeleteGame() {
        GameCollection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        GameCollection.removeGame("Catan");
        assertEquals(0, GameCollection.getGames().size());
    }

    @Test
    void testDeleteNotFound() {
        assertNull(GameCollection.removeGame("Monopoly"));
    }

    @Test
    void testGameForXPlayersLabel() {
        assertEquals("Games for X players", new GameForXPlayersCommand().getLabel());
    }

    @Test
    void testFilterByPlayers() {
        GameCollection.addGame(new BoardGame("Petit", 2, 3, "Family"));
        GameCollection.addGame(new BoardGame("Moyen", 3, 5, "Strategy"));

        long count = GameCollection.getGames().stream()
                .filter(g -> g.minPlayers() <= 4 && g.maxPlayers() >= 4)
                .count();

        assertEquals(1, count);
    }

    @Test
    void testRecommandLabel() {
        assertEquals("Recommand a game", new RecommandGameCommand().getLabel());
    }

    @Test
    void testSummaryLabel() {
        assertEquals("Weekend summary", new SummaryCommand().getLabel());
    }

    @Test
    void testUndoLabel() {
        assertEquals("Undo last action", new UndoCommand(new ArrayDeque<>()).getLabel());
    }

    @Test
    void testUndoAfterAdd() {
        ArrayDeque<Runnable> stack = new ArrayDeque<>();
        AddGameCommand add = new AddGameCommand(stack);
        GameCollection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        stack.push(() -> GameCollection.removeGame("Catan"));

        assertEquals(1, GameCollection.getGames().size());
        new UndoCommand(stack).execute();
        assertEquals(0, GameCollection.getGames().size());
    }

    @Test
    void testUndoAfterDelete() {
        ArrayDeque<Runnable> stack = new ArrayDeque<>();
        BoardGame game = new BoardGame("Catan", 3, 4, "Family");
        GameCollection.addGame(game);
        GameCollection.removeGame("Catan");
        stack.push(() -> GameCollection.addGame(game));

        assertEquals(0, GameCollection.getGames().size());
        new UndoCommand(stack).execute();
        assertEquals(1, GameCollection.getGames().size());
    }

    @Test
    void testUndoEmptyStack() {
        ArrayDeque<Runnable> stack = new ArrayDeque<>();
        assertDoesNotThrow(() -> new UndoCommand(stack).execute());
    }
}