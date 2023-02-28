package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

public class EatCommand extends Command{

    public EatCommand(String firstWord, Player player) {
        super(firstWord, player);
    }

    @Override
    public void execute() {
        String itemName = getSecondWord();
        Item item = null;
        try {
            item = getPlayer().eat(itemName);
            if (isMagicMuffin(item)) {
                getPlayer().setLoadCapacity(getPlayer().getLoadCapacity() + 5);
            }
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isMagicMuffin(Item item) {
        return item.getName().equals("muffin");
    }
}
