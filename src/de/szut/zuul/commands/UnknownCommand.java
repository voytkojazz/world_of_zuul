package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class UnknownCommand extends Command{

    private static final String COMMAND_WORD = "unknown";

    public UnknownCommand(Player player) {
        super(COMMAND_WORD, player);
    }

    @Override
    public void execute() {
        System.out.println("I do not know what you mean...");
    }

    @Override
    public void back() {
        
    }
}
