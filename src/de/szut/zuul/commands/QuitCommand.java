package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class QuitCommand extends Command{

    private static final String COMMAND_WORD = "quit";

    public QuitCommand(Player player) {
        super(COMMAND_WORD, player);
    }

    @Override
    public void execute() {
        getPlayer().setActive(false);
    }

    @Override
    public void back() {
      // quit command have no undo feauture
    }
}
