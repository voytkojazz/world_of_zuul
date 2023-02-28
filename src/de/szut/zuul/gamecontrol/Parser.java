package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.Command;
import de.szut.zuul.commands.UnknownCommand;
import de.szut.zuul.exception.CommandNotFoundException;

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
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser(CommandWords commands)
    {
        this.commands = commands;
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() throws CommandNotFoundException {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        if(commands.isCommand(word1)) {
            Command command = commands.getCommand(word1);
            command.setSecondWord(word2);
            return command;
        }
        else {
            throw new CommandNotFoundException("Command " + word1 + " is not found!!!!");
        }
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public String showCommands() {
        return commands.showAll();
    }

}
