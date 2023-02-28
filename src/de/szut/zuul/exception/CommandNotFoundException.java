package de.szut.zuul.exception;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String message) {
        super(message);
    }
}
