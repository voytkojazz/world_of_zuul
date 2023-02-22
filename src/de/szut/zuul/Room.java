package de.szut.zuul;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;

    private Map<String, Room> exits;

    private Map<String, Item> items;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
    }


    /**
     * a method to add an exit to the room
     * @param direction - direction String
     * @param neighbours - instance of Room class, to associate with this direction
     */
    public void setExit(String direction, Room neighbours) {
        exits.put(direction, neighbours);
    }


    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }


    public Room getExit(String direction) {
        return exits.getOrDefault(direction, null);
    }

    public String exitsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String direction : exits.keySet()) {
            stringBuilder.append(direction).append(" ");
        }
        return stringBuilder.toString();
    }

    public String getLongDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You are ").append(this.getDescription()).append("\n")
                .append("Exits: ").append(this.exitsToString()).append("\n")
                .append("Items in this room: ").append("\n");
        for(Map.Entry<String, Item> itemEntry : items.entrySet()) {
            stringBuilder.append(" - ")
                    .append(itemEntry.getKey()).append(", ")
                    .append(itemEntry.getValue().getDescription()).append(", ")
                    .append(itemEntry.getValue().getWeight()).append(" kg")
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    public void addItem(Item newItem) {
        items.put(newItem.getName(), newItem);
    }

    public Item removeItem(String name) {
        Item removedItem = items.getOrDefault(name, null);
        if(removedItem != null) {
            items.remove(name);
        }
        return removedItem;
    }

}
