package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

import java.util.LinkedList;

public class DropCommand extends Command {

    private static final String COMMAND_WORD = "drop";
    private final LinkedList <String> commandsStack;

    public DropCommand(Player player) {
        super(COMMAND_WORD, player);
        this.commandsStack = new LinkedList<>();
    }

    @Override
    public void execute() {
        String itemName = getSecondWord();
        if(itemName == null) {
            System.out.println("Drop what?");
            return;
        }
        try {
            Item item = getPlayer().dropItem(itemName);
            getPlayer().getCurrentRoom().addItem(item);
            commandsStack.push(itemName);
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void back() {
        try {
            Item item = getPlayer().getCurrentRoom().removeItem(commandsStack.pop());
            getPlayer().takeItem(item);
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException | ItemTooHeavyException e) {
            System.out.println(e.getMessage());
        }
    }
}
