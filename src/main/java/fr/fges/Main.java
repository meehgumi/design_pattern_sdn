package fr.fges;

import fr.fges.command.CommandManager;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar boardgames.jar <file.json/.csv>");
            return;
        }

        GameCollection.init(args[0]);
        
        CommandManager manager = new CommandManager();
        manager.run();
    }
}