package de.szut.zuul.gamecontrol;

import de.szut.zuul.commands.*;
import de.szut.zuul.exception.CommandNotFoundException;
import de.szut.zuul.exception.NoCommandsHistoryException;
import de.szut.zuul.model.Herb;
import de.szut.zuul.model.Item;
import de.szut.zuul.model.Player;
import de.szut.zuul.model.Room;

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
    private static final String NORTH = "north";
    private static final String SOUTH = "south";
    private static final String EAST = "east";
    private static final String WEST = "west";
    private static final String DOWN = "down";
    private static final String UP = "up";
    private final Parser parser;
    private final Player player;
    private final CommandManager commandManager;
    /**
     * Create the game and initialize dependencies: Player, Commands and Parser
     */
    public Game() 
    {
        player = new Player();
        commandManager = new CommandManager();
        parser = new Parser(commandManager);
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
        marketsquare.setExit(NORTH, tavern);
        marketsquare.setExit(EAST, templePyramid);
        marketsquare.setExit(WEST, sacrificialSite);

        templePyramid.setExit(NORTH, hut);
        templePyramid.setExit(WEST, marketsquare);

        tavern.setExit(EAST, hut);
        tavern.setExit(SOUTH, marketsquare);

        sacrificialSite.setExit(EAST, marketsquare);

        hut.setExit(EAST, jungle);
        hut.setExit(SOUTH, templePyramid);
        hut.setExit(WEST, tavern);

        jungle.setExit(WEST, hut);

        secretPassage.setExit(EAST, basement);
        secretPassage.setExit(WEST, cave);

        cave.setExit(EAST, secretPassage);
        cave.setExit(SOUTH, beach);

        beach.setExit(NORTH, cave);


        basement.setExit(WEST, secretPassage);

        wizardsRoom.setExit(DOWN, templePyramid);

        templePyramid.setExit(UP, wizardsRoom);
        templePyramid.setExit(DOWN, basement);

        basement.setExit(UP, templePyramid);

        sacrificialSite.setExit(DOWN, cave);
        cave.setExit(UP, sacrificialSite);

        marketsquare.addItem(new Item("bow", "a bow made of wood", 0.5));
        cave.addItem(new Item("treasure", "a small treasure chest with coins", 7.5));
        wizardsRoom.addItem(new Item("arrows", "a bag with various arrows", 1));
        jungle.addItem(new Herb("plant", "medical plant", 0.5));
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
        Command goCommand = new GoCommand(player);
        Command dropCommand = new DropCommand(player);
        Command eatCommand = new EatCommand(player);
        Command helpCommand = new HelpCommand(player, commandManager);
        Command lookCommand = new LookCommand(player);
        Command takeCommand = new TakeCommand(player);
        Command quitCommand = new QuitCommand(player);
        Command backCommand = new BackCommand(player);
        commandManager.addCommand(goCommand);
        commandManager.addCommand(dropCommand);
        commandManager.addCommand(eatCommand);
        commandManager.addCommand(helpCommand);
        commandManager.addCommand(lookCommand);
        commandManager.addCommand(takeCommand);
        commandManager.addCommand(quitCommand);
        commandManager.addCommand(backCommand);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        while (player.isActive()) {
            try {
                Command command = parser.getCommand();
                commandManager.executeCommand(command);
            } catch (CommandNotFoundException | NoCommandsHistoryException e) {
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
