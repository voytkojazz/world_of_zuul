package de.szut.zuul.gamecontrol;

public class ZuulUI {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.createRooms();
        game.createCommands();
        game.play();
    }

}


