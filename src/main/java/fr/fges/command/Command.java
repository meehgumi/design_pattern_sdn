package fr.fges.command;

public interface Command {
    void execute();    // La méthode qui lancera l'action
    String getLabel(); // Le texte qui s'affichera dans le menu
    default void undo() {}  // Permet d'éviter d'implémenter undo() dans toutes les classes
}