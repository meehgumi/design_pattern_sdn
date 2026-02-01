package fr.fges.storage;
import fr.fges.BoardGame;
import java.io.*;
import java.util.*;

public class CsvStorage implements StorageStrategy {
    @Override
    public void save(String path, List<BoardGame> games) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.println("title,minPlayers,maxPlayers,category");
            for (BoardGame g : games) {
                out.printf("%s,%d,%d,%s\n", g.title(), g.minPlayers(), g.maxPlayers(), g.category());
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public List<BoardGame> load(String path) {
        List<BoardGame> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new BoardGame(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), p[3]));
            }
        } catch (Exception e) { /* Fichier vide ou inexistant */ }
        return list;
    }
}