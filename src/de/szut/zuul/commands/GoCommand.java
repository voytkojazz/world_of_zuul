package de.szut.zuul.commands;

import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;

import java.util.LinkedList;

public class GoCommand extends Command{

    private LinkedList<Room> roomsHistory;

    public GoCommand(String firstWord, Player player) {
        super(firstWord, player);
        this.roomsHistory = new LinkedList<>();
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
            roomsHistory.push(getPlayer().getCurrentRoom());
            getPlayer().goTo(nextRoom);
            System.out.println(getPlayer().getCurrentRoom().getLongDescription());
        }
    }

    @Override
    public void back() {
        getPlayer().goTo(roomsHistory.pop());
        System.out.println(getPlayer().getCurrentRoom().getLongDescription());
    }
}
