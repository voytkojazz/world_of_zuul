package de.szut.zuul.commands;

import de.szut.zuul.model.Player;


/**
 * This abtract class is part of World of Zuul application.
 * It represents an "Adventure" command that can be executed
 * Commands must contain command word as a String and a Player instance that created a command.
 * First words always represents a command word, for instance "go", "eat" etc.
 * Second word of command can be null.
 * Second word represents something that the action will be performed on.
 * Concrete Commands would extend and implement methods of this abstract class
 */
public abstract class Command
{
    private final String commandWord;
    private String secondWord;
    private final Player player;

    /**
     * @param firstWord - String - a command word. Represents some action (go, eat, drop etc)
     * @param player - Player - a Player instance. Creator of the command, for whom the command would be executed
     */
    public Command(String firstWord, Player player)
    {
        this.commandWord = firstWord;
        this.player = player;
    }

    /**
     * Return the command word (the first word) of this command.
     * @return The command word.
     */
    public  String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return The second word of this command. Returns null if there was no
     * second word.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * sets a second word (word that tells the programm a participant of the action,
     * for example "go east". East is secondWord).
     * Method is used for changing the participant of action
     * @param secondWord
     */
    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }

    /**
     * implementations of this method must contains a "business logic" of concrete commands
     */
    public abstract void execute();


    /**
     * implementations of this method must contain logic to revert the consequences of command's execution
     */
    public abstract void back();

}

