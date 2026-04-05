package fr.fges.command;

import java.util.Deque;

public class UndoCommand implements Command {
    private final Deque<Command> undoStack;

    public UndoCommand(Deque<Command> undoStack) {
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
        undoStack.pop().undo();
    }
}
