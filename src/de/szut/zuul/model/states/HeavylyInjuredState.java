package de.szut.zuul.model.states;

import de.szut.zuul.model.Player;

public class HeavylyInjuredState implements State{

    private Player player;

    public HeavylyInjuredState(Player player) {
        this.player = player;
    }

    @Override
    public void heal() {
        player.changeState(new InjuredState(player));
        System.out.println("Player is now in injured state");
    }

    @Override
    public void injure() {
        System.out.println("Player is already heavily injured");
    }

    @Override
    public String toString() {
        return "Heavily injured state";
    }
}
