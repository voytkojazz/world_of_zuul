package de.szut.zuul.commands;

import de.szut.zuul.model.Player;



public class BackCommand extends Command{
    private static final String COMMAND_WORD = "back";
    Command command;

    /**
     * creates a BackCommand
     * @param player - Player - creator and performer of the command
     */
    public BackCommand(Player player) {
        super(COMMAND_WORD, player);
    }

    public void execute() {
        command.back();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void back() {
        // back command can not be reverted
    }

}
