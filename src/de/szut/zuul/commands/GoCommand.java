package de.szut.zuul.commands;

import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;

public class GoCommand extends Command{

    public GoCommand(String firstWord, Player player) {
        super(firstWord, player);
    }

    @Override
    public void execute() {
        if(!this.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }
        String direction = getSecondWord();
        // Try to leave current room.
        Room nextRoom = getPlayer().getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            getPlayer().goTo(nextRoom);
            System.out.println(getPlayer().getCurrentRoom().getLongDescription());
        }
    }
}
