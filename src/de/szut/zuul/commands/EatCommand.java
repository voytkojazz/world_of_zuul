package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

import java.util.LinkedList;

public class EatCommand extends Command{
    private static final String COMMAND_WORD = "eat";
    private LinkedList<Item> itemsHistoryStack;

    public EatCommand(Player player) {
        super(COMMAND_WORD, player);
        this.itemsHistoryStack = new LinkedList<>();
    }

    @Override
    public void execute() {
        String itemName = getSecondWord();
        Item item = null;
        try {
            item = getPlayer().eat(itemName);
            if (isMagicMuffin(item)) {
                getPlayer().incrementCapacity(5);
            } else if (isHealingHerb(item)) {
                getPlayer().heal();
            }
            itemsHistoryStack.push(item);
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void back() {
        try {
            Item itemToReturn = itemsHistoryStack.pop();
            getPlayer().takeItem(itemToReturn);
            if (isMagicMuffin(itemToReturn)) {
                getPlayer().decrementCapacity(5);
            } else if (isHealingHerb(itemToReturn)) {
                getPlayer().injure();
            }
            System.out.println(getPlayer().showStatus());
        } catch (ItemTooHeavyException e) {
            e.printStackTrace();
        }
    }

    private boolean isMagicMuffin(Item item) {
        return item.getName().equals("muffin");
    }

    private boolean isHealingHerb(Item item) {
        return item.getDescription().equals("medical plant");
    }
}
