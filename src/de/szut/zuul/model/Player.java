package de.szut.zuul.model;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;
import de.szut.zuul.model.states.HealthyState;
import de.szut.zuul.model.states.InjuredState;
import de.szut.zuul.model.states.State;
import java.util.LinkedList;

public class Player {
    private State state;
    private Room currentRoom;
    private double loadCapacity;
    private LinkedList<Item> items;

    private boolean active;

    public Player() {
        this.state = InjuredState.getInstance();
        this.loadCapacity = 10;
        this.items = new LinkedList<>();
        this.active = false;
    }

    public void takeItem(Item item) throws ItemTooHeavyException {
        if (isTakePossible(item)) {
            items.add(item);
        } else {
            throw new ItemTooHeavyException("Can not take the item " + item.getName() + ", full capacity");
        }
    }

    public void injure() {
        this.setState(state.injure());
        this.showStatus();
    }

    public void heal() {
        this.setState(state.heal());
        this.showStatus();
    }

    public void setState(State state) {
        this.state = state;
    }

    public Item dropItem(String name) throws ItemNotFoundException {
        Item toDrop = items.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Item with name " + name + " is not found"));
        items.remove(toDrop);
        return toDrop;
    }

    public Item eat(String name) throws ItemNotFoundException {
        return removeFoodItem(name);
    }

    private Item removeFoodItem(String name) throws ItemNotFoundException {
        Item foundedItem = items.stream()
                .filter(item -> item.isFood() && item.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Food item with name " + name + " is not found or is not a food's item"));
        items.remove(foundedItem);
        return foundedItem;
    }


    private boolean isTakePossible(Item item) {
        double totalWeight = calculateWeight();
        return (totalWeight + item.getWeight()) >= 0;
    }

    private double calculateWeight() {
        return items.stream()
                .map(Item::getWeight).mapToDouble(Double::doubleValue).sum();
    }

    public void decrementCapacity(int capacity) {
        this.loadCapacity -= capacity;
    }

    public void incrementCapacity(int capacity) {
        this.loadCapacity += capacity;
    }

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

    public void goTo(Room nextRoom) {
        this.currentRoom = nextRoom;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
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
