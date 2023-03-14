package de.szut.zuul.model.states;

import de.szut.zuul.model.Player;

public class HeavylyInjuredState implements State{

    private static State instance = null;

    private HeavylyInjuredState() {}

    public static State getInstance() {
        if(instance == null) {
            instance = new HeavylyInjuredState();
        }
        return instance;
    }

    @Override
    public State heal() {
        return InjuredState.getInstance();
    }

    @Override
    public State injure() {
        return HeavylyInjuredState.getInstance();
    }

    @Override
    public String toString() {
        return "Heavily injured state";
    }
}
