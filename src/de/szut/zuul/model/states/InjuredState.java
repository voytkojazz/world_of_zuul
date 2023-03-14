package de.szut.zuul.model.states;

import de.szut.zuul.model.Player;

public class InjuredState implements State{

    private Player player;

    public InjuredState(Player player) {
        this.player = player;
    }

    @Override
    public void heal() {
        player.changeState(new HealthyState(player));
        System.out.println("Player is now healed: " + player.getState());
    }

    @Override
    public void injure() {
        player.changeState(new HeavylyInjuredState(player));
        System.out.println("Player is now heavily injured: " + player.getState());
    }

    @Override
    public String toString() {
        return "Injured state";
    }
}
