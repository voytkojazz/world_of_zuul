package de.szut.zuul.exception;

/**
 * Exception informs that the Stack with executed Commands is empty
 */
public class NoCommandsHistoryException extends Exception{
    public NoCommandsHistoryException(String message) {
        super(message);
    }
}
