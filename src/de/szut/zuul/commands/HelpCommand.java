package de.szut.zuul.commands;

import de.szut.zuul.gamecontrol.CommandWords;
import de.szut.zuul.model.Player;

public class HelpCommand extends Command{

    CommandWords commandWords;

    public HelpCommand(String firstWord, Player player, CommandWords commands) {
        super(firstWord, player);
        this.commandWords = commands;
    }

    @Override
    public void execute() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(commandWords.showAll());
    }

    @Override
    public void back() {
        execute();
    }
}
