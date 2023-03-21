package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class UnknownCommand extends Command{

    public UnknownCommand(String firstWord, Player player) {
        super(firstWord, player);
    }

    @Override
    public void execute() {
        System.out.println("I do not know what you mean...");
    }

    @Override
    public void back() {
        
    }
}
