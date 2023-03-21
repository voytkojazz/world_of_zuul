package de.szut.zuul.model;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.states.InjuredState;
import de.szut.zuul.model.states.State;
import java.util.LinkedList;

public class Player {
    private State state; // the Health State of Player
    private Room currentRoom; // Room where player is located
    private double loadCapacity; // player's items have weight and loadCapacity is the maximum weight that player can take
    private final LinkedList<Item> items; // collection of player's items
    private boolean active; // boolean to track if player wants to play the game

    /**
     * Constract PLayer object and initialize his starting state,
     * loading capacity, items collection and active boolean variable
     */
    public Player() {
        this.state = InjuredState.getInstance();
        this.loadCapacity = 10;
        this.items = new LinkedList<>();
        this.active = true;
    }

    /**
     * takes an Item object and add it to this.items collection
     * @param item - Item - item object to take
     * @throws ItemTooHeavyException - if the take is not possible
     */
    public void takeItem(Item item) throws ItemTooHeavyException {
        if (isTakePossible(item)) {
            items.add(item);
        } else {
            throw new ItemTooHeavyException("Can not take the item " + item.getName() + ", full capacity");
        }
    }

    /**
     * calls the .injure() method on current state of player
     */
    public void injure() {
        this.setState(state.injure());
        this.showStatus();
    }

    /**
     * calls the .heal method on current state of player
     */
    public void heal() {
        this.setState(state.heal());
        this.showStatus();
    }

    /**
     * setter for State
     * @param state - State object
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Remove item from items collection
     * @param name - String - item's name
     * @return - Item -Dropped Item
     * @throws ItemNotFoundException - if Item is not found
     */
    public Item dropItem(String name) throws ItemNotFoundException {
        Item toDrop = items.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item with name " + name + " is not found"));
        items.remove(toDrop);
        return toDrop;
    }

    /**
     * eats an Item
     * @param name - String - name of Item
     * @return eated item - Item
     * @throws ItemNotFoundException - if item is not found
     */
    public Item eat(String name) throws ItemNotFoundException {
        return removeFoodItem(name);
    }

    /**
     * filters user's items collection to find the item that is a food and has the requested name
     * removes it from items collection and returns it
     * @param name String - item's name
     * @return Item - item from items collection that has a requested a name and is food
     * @throws ItemNotFoundException - if item is not found
     */
    private Item removeFoodItem(String name) throws ItemNotFoundException {
        Item foundedItem = items.stream()
                .filter(item -> item.isFood() && item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Food item with name " + name + " is not found or is not a food's item"));
        items.remove(foundedItem);
        return foundedItem;
    }

    /**
     * verifies that taking an item to player's collection is possible
     * @param item - some Item object
     * @return - boolean - true if take is possible
     */
    private boolean isTakePossible(Item item) {
        double totalWeight = calculateWeight();
        return (totalWeight + item.getWeight()) >= 0;
    }

    /**
     * calculates a total weight of all items in user's collection
     * @return - double - total weight of all items
     */
    private double calculateWeight() {
        return items.stream()
                .map(Item::getWeight).mapToDouble(Double::doubleValue).sum();
    }

    /**
     * decrements player's total capacity so that player can take less items
     * @param capacity - int - value to decrement
     */
    public void decrementCapacity(int capacity) {
        this.loadCapacity -= capacity;
    }

    /**
     * increments a player's capacity so that player can take more items!
     * @param capacity - int - value to increment
     */
    public void incrementCapacity(int capacity) {
        this.loadCapacity += capacity;
    }

    /**
     * prepares a String of all the important and needed info about a Player
     * @return - String - status of Player
     */
    public String showStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append("> Status of the player:\n");
        builder.append("State: ").append(this.getState()).append("\n");
        builder.append("loadCapacity: ").append(loadCapacity).append("kg\n");
        builder.append("taken items: ");
        for (Item item : items) {
            builder.append(item.getName()).append(", ").append(item.getWeight()).append("kg; ");
        }
        builder.append("\n");
        builder.append("absorbed weight: ").append(calculateWeight()).append("kg\n");
        builder.append(getCurrentRoom().getLongDescription());
        return builder.toString();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * go to specific room
     * @param nextRoom - Room - room to go
     */
    public void goTo(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public State getState() {
        return state;
    }

}
