package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class LookCommand extends Command{

    public static final String COMMAND_WORD = "look";

    public LookCommand(Player player) {
        super(COMMAND_WORD, player);
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
