package de.szut.zuul.commands;

import de.szut.zuul.gamecontrol.CommandManager;
import de.szut.zuul.model.Player;

public class HelpCommand extends Command{

    private static final String COMMAND_WORD = "help";
    CommandManager commandManager;

    public HelpCommand(Player player, CommandManager commandManager) {
        super(COMMAND_WORD, player);
        this.commandManager = commandManager;
    }

    @Override
    public void execute() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(commandManager.getAllCommands());
    }

    @Override
    public void back() {
        execute();
    }
}
