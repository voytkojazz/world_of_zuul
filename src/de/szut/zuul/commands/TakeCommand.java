package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

import java.util.LinkedList;

public class TakeCommand extends Command{

    private static final String COMMAND_WORD = "take";
    private final LinkedList<Item> itemsHistory;

    public TakeCommand(Player player) {
        super(COMMAND_WORD, player);
        itemsHistory = new LinkedList<>();
    }

    @Override
    public void execute() {
        String itemName = getSecondWord();
        if(itemName == null) {
            System.out.println("Take what?");
            return;
        }
        try {
            Item item = getPlayer().getCurrentRoom().removeItem(itemName);
            getPlayer().takeItem(item);
            itemsHistory.push(item);
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException | ItemTooHeavyException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void back() {
        Item itemToDropBack = itemsHistory.pop();
        try {
            getPlayer().dropItem(itemToDropBack.getName());
            getPlayer().getCurrentRoom().addItem(itemToDropBack);
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
