package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.BackCommand;
import de.szut.zuul.commands.Command;
import de.szut.zuul.exception.NoCommandsHistoryException;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds a Map of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 */

public class CommandManager
{
    private final Map<String, Command> commandsMap;
    private final CommandHistory history;

    /**
     * Constructor - create a new intance of CommandManager.
     * initializes CommandHistory and new HashMap for available commands
     */
    public CommandManager()
    {
        this.history = new CommandHistory();
        this.commandsMap = new HashMap<>();
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

    public Command getCommand(String commandString) throws NoCommandsHistoryException {
        Command toReturn = commandsMap.get(commandString);
        if (toReturn instanceof BackCommand) {
            BackCommand backCommand = (BackCommand) toReturn;
            backCommand.setCommand(history.pop());
        }
        return toReturn;
    }

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    public void addCommand(Command command) {
        commandsMap.put(command.getCommandWord(), command);
    }

    public String getAllCommands(){
        StringBuilder builder = new StringBuilder();
        for (String command : commandsMap.keySet()) {
            builder.append(command).append(" ");
        }
        return builder.toString();
    }

}
