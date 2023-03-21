package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class LookCommand extends Command{

    public LookCommand(String firstWord, Player player) {
        super(firstWord, player);
    }

    @Override
    public void execute() {
        System.out.println(getPlayer().getCurrentRoom().getLongDescription());
    }

    @Override
    public void back() {
        execute();
    }
}
