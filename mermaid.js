classDiagram

namespace UI {
    class Main {
        main()
    }
    class Command {
        execute()
        getLabel()
        undo()
    }
    class AddGameCommand
    class DeleteCommand
    class ListGamesCommand
    class RecommandGameCommand
    class GameForXPlayersCommand
    class SummaryCommand
    class TournamentCommand {
        players
        selectedGame
        format
        execute()
        getLabel()
    }
    class UndoCommand {
        execute()
        getLabel()
    }
}

namespace BUSINESS {
    class GameCollection {
        games
        strategy
        init()
        getGames()
        addGame()
        removeGame()
    }
    class CommandManager {
        history
        addToHistory(cmd)
        undo()
    }
    class TournamentFormat {
        <<interface>>
        play(players)
    }
    class ChampionshipFormat {
        play(players)
    }
    class KingOfTheHillFormat {
        play(players)
    }
    class Player {
        name
        points
        wins
        addVictory()
        addDefeat()
    }
}

namespace DATA {
    class StorageStrategy {
        save()
        load()
    }
    class JsonStorage
    class CsvStorage
    class BoardGame {
        title
        minPlayers
        maxPlayers
        category
    }
}

Command <|.. AddGameCommand
Command <|.. DeleteCommand
Command <|.. ListGamesCommand
Command <|.. RecommandGameCommand
Command <|.. GameForXPlayersCommand
Command <|.. SummaryCommand
Command <|.. TournamentCommand
Command <|.. UndoCommand

TournamentFormat <|.. ChampionshipFormat
TournamentFormat <|.. KingOfTheHillFormat

StorageStrategy <|.. JsonStorage
StorageStrategy <|.. CsvStorage

GameCollection --> StorageStrategy
GameCollection --> BoardGame
Main --> GameCollection
Main --> Command
Main --> CommandManager
CommandManager --> Command
UndoCommand --> CommandManager
TournamentCommand --> TournamentFormat
TournamentCommand --> Player