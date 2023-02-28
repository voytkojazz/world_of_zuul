package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

public class TakeCommand extends Command{

    public TakeCommand(String firstWord, Player player) {
        super(firstWord, player);
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
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException | ItemTooHeavyException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
