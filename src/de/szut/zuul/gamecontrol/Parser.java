package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.Command;
import de.szut.zuul.exception.CommandNotFoundException;
import de.szut.zuul.exception.NoCommandsHistoryException;

import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser takes a set of known command words ({@link CommandManager}) in constructor. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * throws a {@link CommandNotFoundException}.
 *
 */

public class Parser 
{
    private final CommandManager commandManager;  // holds all valid command words
    private final Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser(CommandManager commandManager)
    {
        this.commandManager = commandManager;
        this.reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() throws CommandNotFoundException, NoCommandsHistoryException {
        String[] userInput = takeInput();
        String word1 = userInput[0];
        String word2 = userInput[1];

        if(commandManager.isCommand(word1)) {
            Command command = commandManager.getCommand(word1);
            command.setSecondWord(word2);
            return command;
        }
        else {
            throw new CommandNotFoundException("Command " + word1 + " is not found!!!!");
        }
    }


    /**
     * takes the user's input and parses first two words
     * @return String[] - where [0] is the first command word and [1] is the second word
     */
    private String[] takeInput() {
        String word1 = null;
        String word2 = null;
        String inputLine = null;

        System.out.print("> ");
        inputLine = reader.nextLine();
        try (Scanner tokenizer = new Scanner(inputLine)) {
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();
                if(tokenizer.hasNext()) {
                    word2 = tokenizer.next();
                }
            }
        }
        return new String[] {word1, word2};
    }

    public String showCommands() {
        return commandManager.getAllCommands();
    }
}
