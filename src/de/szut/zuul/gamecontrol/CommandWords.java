package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.Command;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    private Map<String, Command> commandsMap;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.commandsMap = new HashMap<>();
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String commandString)
    {
        return commandsMap.containsKey(commandString);
    }

    public Command getCommand(String commandString) {
        return commandsMap.get(commandString);
    }

    public void addCommand(Command command) {
        commandsMap.put(command.getCommandWord(), command);
    }

    public String showAll(){
        StringBuilder builder = new StringBuilder();
        for (String command : commandsMap.keySet()) {
            builder.append(command).append(" ");
        }
        return builder.toString();
    }
}
