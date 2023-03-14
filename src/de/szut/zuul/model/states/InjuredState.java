package de.szut.zuul.model.states;

import de.szut.zuul.model.Player;

public class InjuredState implements State{

    private static State instance = null;

    private InjuredState() {}

    public static State getInstance() {
        if(instance == null) {
            instance = new InjuredState();
        }
        return instance;
    }

    @Override
    public State heal() {
        return HealthyState.getInstance();
    }

    @Override
    public State injure() {
        return HeavylyInjuredState.getInstance();
    }

    @Override
    public String toString() {
        return "Injured state";
    }
}
