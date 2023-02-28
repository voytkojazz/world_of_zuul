package de.szut.zuul.commands;

import de.szut.zuul.gamecontrol.Game;
import de.szut.zuul.model.Player;

public class QuitCommand extends Command{

    public QuitCommand(String firstWord, Player player) {
        super(firstWord, player);
    }

    @Override
    public void execute() {

    }
}
