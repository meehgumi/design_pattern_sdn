classDiagram

namespace UI {
    class Main {
        main()
    }
}

namespace COMMAND {
    class Command {
        <<interface>>
        execute()
        getLabel()
        undo()
    }
    class CommandManager {
        commands : List~Command~
        undoStack : Deque~Runnable~
        run()
        initCommands()
        afficherMenu()
        executerCommande()
    }
    class AddGameCommand {
        undoStack : Deque~Runnable~
        gameAdded : BoardGame
        execute()
        getLabel()
        undo()
    }
    class DeleteCommand {
        undoStack : Deque~Runnable~
        deletedGame : BoardGame
        execute()
        getLabel()
        undo()
    }
    class ListGamesCommand {
        execute()
        getLabel()
    }
    class RecommandGameCommand {
        execute()
        getLabel()
    }
    class GameForXPlayersCommand {
        execute()
        getLabel()
    }
    class SummaryCommand {
        execute()
        getLabel()
    }
    class UndoCommand {
        undoStack : Deque~Runnable~
        execute()
        getLabel()
    }
    class TournamentCommand {
        players
        selectedGame
        format
        execute()
        getLabel()
    }
}

namespace BUSINESS {
    class GameCollection {
        games : List~BoardGame~
        storageFile : String
        strategy : StorageStrategy
        init()
        getGames()
        addGame()
        removeGame()
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
        <<interface>>
        save()
        load()
    }
    class JsonStorage {
        save()
        load()
    }
    class CsvStorage {
        save()
        load()
    }
    class BoardGame {
        title : String
        minPlayers : int
        maxPlayers : int
        category : String
    }
}

Command <|.. AddGameCommand
Command <|.. DeleteCommand
Command <|.. ListGamesCommand
Command <|.. RecommandGameCommand
Command <|.. GameForXPlayersCommand
Command <|.. SummaryCommand
Command <|.. UndoCommand
Command <|.. TournamentCommand

TournamentFormat <|.. ChampionshipFormat
TournamentFormat <|.. KingOfTheHillFormat

TournamentCommand --> TournamentFormat
TournamentCommand --> Player

StorageStrategy <|.. JsonStorage
StorageStrategy <|.. CsvStorage

Main --> CommandManager
CommandManager --> Command
CommandManager --> AddGameCommand : injecte undoStack
CommandManager --> DeleteCommand : injecte undoStack
CommandManager --> UndoCommand : injecte undoStack

AddGameCommand --> GameCollection
DeleteCommand --> GameCollection
ListGamesCommand --> GameCollection
RecommandGameCommand --> GameCollection
SummaryCommand --> GameCollection
GameForXPlayersCommand --> GameCollection

GameCollection --> StorageStrategy
GameCollection --> BoardGame
