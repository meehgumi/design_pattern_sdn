package fr.fges;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class GameCollectionTest {

    @BeforeEach
    void setUp() {
        // On réinitialise la liste avant chaque test pour éviter les effets de bord
        GameCollection.init("test.json");
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
    void shouldFilterGamesForRecommendation() {
        //Ajouter des jeux avec différents nombres de joueurs
        GameCollection.addGame(new BoardGame("Petit jeu", 2, 3, "Family"));
        GameCollection.addGame(new BoardGame("Moyen jeu", 3, 5, "Strategy"));
        GameCollection.addGame(new BoardGame("Grand jeu", 6, 10, "Party"));

        //Filtrer pour 4 joueurs
        int nbPlayers = 4;
        List<BoardGame> compatibles = GameCollection.getGames().stream()
                .filter(g -> g.minPlayers() <= nbPlayers && g.maxPlayers() >= nbPlayers)
                .toList();

        // Assert
        assertEquals(1, compatibles.size());
        assertEquals("Moyen jeu", compatibles.get(0).title());
    }
}