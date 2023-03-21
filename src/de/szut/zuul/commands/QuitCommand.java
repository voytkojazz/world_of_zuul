package de.szut.zuul.commands;

import de.szut.zuul.model.Player;

public class QuitCommand extends Command{

    public QuitCommand(String firstWord, Player player) {
        super(firstWord, player);
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
