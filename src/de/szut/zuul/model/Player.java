package de.szut.zuul.model;

import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Player {
    private Room currentRoom;
    private double loadCapacity;
    private List<Item> items;

    public Player() {
        this.loadCapacity = 10;
        this.items = new LinkedList<>();
    }

    public String showStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append("> Status of the player:\n");
        builder.append("loadCapacity: ").append(loadCapacity).append("kg\n");
        builder.append("taken items: ");
        for(Item item : items) {
            builder.append(item.getName()).append(", ").append(item.getWeight()).append("kg; ");
        }
        builder.append("\n");
        builder.append("absorbed weight: ").append(calculateWeight()).append("kg\n");
        builder.append(getCurrentRoom().getLongDescription());
        return builder.toString();
    }

    public void takeItem(Item item) throws ItemTooHeavyException {
        if(isTakePossible(item)) {
            items.add(item);
        } else {
            throw new ItemTooHeavyException("Can not take the item " + item.getName() + ", full capacity");
        }
    }

    public Item dropItem(String name) throws ItemNotFoundException {
        Iterator<Item> itemsIterator = items.iterator();
        while(itemsIterator.hasNext()) {
            Item current = itemsIterator.next();
            if(current.getName().equals(name)) {
                itemsIterator.remove();
                return current;
            }
        }
        throw new ItemNotFoundException("You do not own " + name + "item!");
    }

    public Item eat(String name) throws ItemNotFoundException {
        return removeFoodItem(name);
    }

    private Item removeFoodItem(String name) throws ItemNotFoundException {
        Item foundedItem = items.stream()
                .filter(item -> item.getName().equals(name) && item.isFood())
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
        double totalWeight = 0;
        for(Item i : items) {
            totalWeight += i.getWeight();
        }
        return totalWeight;
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
}
