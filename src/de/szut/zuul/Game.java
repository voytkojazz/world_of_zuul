package de.szut.zuul;

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
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();

    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
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
        tavern.addItem(new Item("food", "a plate of hearty meat and corn porridge", 0.5));
        basement.addItem(new Item("jewerly", "a very pretty headdress", 1));
        Item magicMuffin = new Item("muffin", "a magic muffin", 0.5);
        magicMuffin.setFood(true);
        secretPassage.addItem(magicMuffin);
        player.goTo(marketsquare);  // start game on market square
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
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
        printRoomInformation();
    }

    private void printRoomInformation() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
            case "look":
                look();
                break;
            case "take":
                takeItem(command);
                break;
            case "drop":
                dropItem(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the jungle. At once there is a glade. On it there a buildings...");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.goTo(nextRoom);
            printRoomInformation();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void look() {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private void takeItem(Command command) {
        String itemName = command.getSecondWord();
        if(itemName == null) {
            System.out.println("Take what?");
            return;
        }
        Item item = player.getCurrentRoom().removeItem(itemName);
        if(item == null) {
            System.out.println("The item " + itemName + " is not found in the room");
            return;
        }
        boolean itemTaken = player.takeItem(item);
        if(!itemTaken) {
            System.out.println("The item is not taken, full capacity");
        }
        System.out.println(player.showStatus());
    }

    private void dropItem(Command command) {
        String itemName = command.getSecondWord();
        if(itemName == null) {
            System.out.println("Drop what?");
            return;
        }
        Item item = player.dropItem(itemName);
        if(item == null) {
            System.out.println("The item was not found in your inventory");
            return;
        }
        player.getCurrentRoom().addItem(item);
        System.out.println(player.showStatus());
    }

    private Item eat(Command command) {
        String itemName = command.getSecondWord();
        Item item = player.eat(itemName);
        if(item == null) {
            System.out.println("The item is not found in player's inventory or is not a food...");
            return null;
        }
        return item;
    }
}
