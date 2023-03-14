package de.szut.zuul.model.states;

import de.szut.zuul.model.Player;

public class HealthyState implements State{

    private Player player;

    public HealthyState(Player player) {
        this.player = player;
    }

    @Override
    public void heal() {
        System.out.println("The player is already very healthy");
        System.out.println(player.showStatus());
    }

    @Override
    public void injure() {
        player.changeState(new InjuredState(player));
        System.out.println("PLayer is now injured: ");
        System.out.println(player.showStatus());
    }

    @Override
    public String toString() {
        return "Healthy State";
    }
}
