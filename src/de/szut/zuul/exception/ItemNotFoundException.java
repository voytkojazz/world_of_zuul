package de.szut.zuul.exception;

/**
 * Exception occurs when the Item is not found in Player's inventory or in Room
 */
public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
