package fr.fges;

import fr.fges.command.CommandManager;
import fr.fges.storage.CsvStorage;
import fr.fges.storage.JsonStorage;
import fr.fges.storage.StorageStrategy;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar boardgames.jar <file.json/.csv>");
            return;
        }

        String file = args[0];
        StorageStrategy strategy = file.endsWith(".json") ? new JsonStorage() : new CsvStorage();
        List<BoardGame> initialGames = strategy.load(file);

        GameCollection collection = new GameCollection(initialGames, strategy, file);

        CommandManager manager = new CommandManager(collection);
        manager.run();
    }
}