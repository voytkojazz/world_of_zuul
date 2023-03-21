package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.BackCommand;
import de.szut.zuul.commands.Command;
import de.szut.zuul.exception.NoCommandsHistoryException;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CommandHistory {
    private final LinkedList <Command> history;

    public CommandHistory() {
        this.history = new LinkedList<>();
    }

    public void push(Command command) {
        if (!(command instanceof BackCommand)) {
            history.push(command);
        }
    }

    public Command pop() throws NoCommandsHistoryException {
        try {
            return history.pop();
        } catch (NoSuchElementException e) {
            throw new NoCommandsHistoryException("You have not performed any commands to undo them");
        }
    }
}
