package fr.fges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameCollectionTest {

    @BeforeEach
    void setUp() {
        // On réinitialise la liste avant chaque test pour éviter les effets de bord
        GameCollection.getGames().clear();
    }

    @Test
    void shouldAddGameToCollection() {
        // Arrange
        BoardGame game = new BoardGame("Terraforming Mars", 1, 5, "Strategy");

        // Act
        GameCollection.addGame(game);

        // Assert
        assertEquals(1, GameCollection.getGames().size());
        assertEquals("Terraforming Mars", GameCollection.getGames().get(0).title());
    }

    @Test
    void shouldRemoveGameByTitle() {
        // Arrange
        GameCollection.addGame(new BoardGame("Catan", 3, 4, "Family"));
        GameCollection.addGame(new BoardGame("Dixit", 3, 6, "Party"));

        // Act
        GameCollection.removeGame("Catan");

        // Assert
        assertEquals(1, GameCollection.getGames().size());
        assertFalse(GameCollection.getGames().stream().anyMatch(g -> g.title().equals("Catan")));
    }

    @Test
    void shouldReturnZeroStatsWhenCollectionIsEmpty() {
        // Arrange (Collection vide par le setUp)
        
        // Act
        int size = GameCollection.getGames().size();

        // Assert
        assertEquals(0, size);
    }
}