package fr.fges;

public class Player {
    private final String name;
    private int points;
    private int wins;

    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.wins = 0;
    }

    public void addVictory() {
        this.points += 3;
        this.wins += 1;
    }

    public void addDefeat() {
        this.points += 1;
    }

    public String getName()  { return name; }
    public int getPoints()   { return points; }
    public int getWins()     { return wins; }
}
