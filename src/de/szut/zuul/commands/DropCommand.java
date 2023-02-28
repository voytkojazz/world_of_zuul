package de.szut.zuul.commands;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;

public class DropCommand extends Command{

    public DropCommand(String firstWord, Player player) {
        super(firstWord, player);
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
            System.out.println(getPlayer().showStatus());
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());;
        }
    }
}
