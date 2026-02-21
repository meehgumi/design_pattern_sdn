package fr.fges.command;

import java.util.Deque;

public class UndoCommand implements Command {
    private final Deque<Runnable> undoStack;

    public UndoCommand(Deque<Runnable> undoStack) {
        this.undoStack = undoStack;
    }

    public String getLabel() {
        return "Undo last action";
    }

    public void execute() {
        if (undoStack.isEmpty()) {
            System.out.println("Rien à annuler.");
            return;
        }
        undoStack.pop().run();
    }
}
