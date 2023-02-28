package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.*;
import de.szut.zuul.exception.CommandNotFoundException;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;
import de.szut.zuul.exception.ItemNotFoundException;
import de.szut.zuul.exception.ItemTooHeavyException;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Game 
{
    private Parser parser;

    private Player player;

    private CommandWords commands;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        commands = new CommandWords();
        parser = new Parser(commands);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    public void createRooms()
    {
        Room marketsquare, templePyramid, tavern, sacrificialSite, hut, jungle, secretPassage, cave, beach, basement, wizardsRoom;
      
        // create the rooms
        marketsquare = new Room("on the market square");
        templePyramid = new Room("in a temple pyramid");
        tavern = new Room("in the tavern at the market square");
        sacrificialSite = new Room("at a sacrificial site");
        hut = new Room("in a hut");
        jungle = new Room("in the jungle");
        secretPassage = new Room("in a secret passage");
        cave = new Room("in a cave");
        beach = new Room("on the beach");
        basement = new Room("in a basement");
        wizardsRoom = new Room("in a wizard's room");

        // initialise room exits
        marketsquare.setExit("north", tavern);
        marketsquare.setExit("east", templePyramid);
        marketsquare.setExit("west", sacrificialSite);

        templePyramid.setExit("north", hut);
        templePyramid.setExit("west", marketsquare);

        tavern.setExit("east", hut);
        tavern.setExit("south", marketsquare);

        sacrificialSite.setExit("east", marketsquare);

        hut.setExit("east", jungle);
        hut.setExit("south", templePyramid);
        hut.setExit("west", tavern);

        jungle.setExit("west", hut);

        secretPassage.setExit("east", basement);
        secretPassage.setExit("west", cave);

        cave.setExit("east", secretPassage);
        cave.setExit("south", beach);

        beach.setExit("north", cave);


        basement.setExit("west", secretPassage);

        wizardsRoom.setExit("down", templePyramid);

        templePyramid.setExit("up", wizardsRoom);
        templePyramid.setExit("down", basement);

        basement.setExit("up", templePyramid);

        sacrificialSite.setExit("down", cave);
        cave.setExit("up", sacrificialSite);

        marketsquare.addItem(new Item("bow", "a bow made of wood", 0.5));
        cave.addItem(new Item("treasure", "a small treasure chest with coins", 7.5));
        wizardsRoom.addItem(new Item("arrows", "a bag with various arrows", 1));
        jungle.addItem(new Item("plant", "medical plant", 0.5));
        jungle.addItem(new Item("cacao", "a small cacao tree", 5));
        sacrificialSite.addItem(new Item("knife", "a spear with accompanying slingshot", 5));
        Item food = new Item("food", "a plate of hearty meat and corn porridge", 0.5);
        food.setFood(true);
        tavern.addItem(food);
        basement.addItem(new Item("jewerly", "a very pretty headdress", 1));
        Item magicMuffin = new Item("muffin", "a magic muffin", 0.5);
        magicMuffin.setFood(true);
        secretPassage.addItem(magicMuffin);
        player.goTo(marketsquare);  // start game on market square
    }

    public void createCommands() {
        Command goCommand = new GoCommand("go", player);
        Command dropCommand = new DropCommand("drop", player);
        Command eatCommand = new EatCommand("eat", player);
        Command helpCommand = new HelpCommand("help", player, commands);
        Command lookCommand = new LookCommand("look", player);
        Command takeCommand = new TakeCommand("take", player);
        Command quitCommand = new QuitCommand("quit", player);
        commands.addCommand(goCommand);
        commands.addCommand(dropCommand);
        commands.addCommand(eatCommand);
        commands.addCommand(helpCommand);
        commands.addCommand(lookCommand);
        commands.addCommand(takeCommand);
        commands.addCommand(quitCommand);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        while (!player.isActive()) {
            try {
                Command command = parser.getCommand();
                parser.executeCommand(command);
            } catch (CommandNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }
}
