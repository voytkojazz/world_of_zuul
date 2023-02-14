package de.szut.zuul;

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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;

    private Room upstairs;
    private Room downstairs;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
    }

    public void setStairs(Room up, Room down) {
        if(up != null) {
            upstairs = up;
        }
        if(down != null) {
            downstairs = down;
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }


    public Room getExit(String direction) {
        Room exit = null;
        switch (direction) {
            case "north":
                exit = northExit;
                break;
            case "south":
                exit = southExit;
                break;
            case "east":
                exit = eastExit;
                break;
            case "west":
                exit = westExit;
                break;
            case "up":
                exit = upstairs;
                break;
            case "down":
                exit = downstairs;
                break;
        }
        return exit;
    }

    public String exitsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (northExit != null) {
            stringBuilder.append("north ");
        }
        if (southExit != null) {
            stringBuilder.append("south ");
        }
        if (eastExit != null) {
            stringBuilder.append("east ");
        }
        if (westExit != null) {
            stringBuilder.append("west ");
        }
        if (upstairs != null) {
            stringBuilder.append("up ");
        }
        if (downstairs != null) {
            stringBuilder.append("down ");
        }
        return stringBuilder.toString();
    }
}
