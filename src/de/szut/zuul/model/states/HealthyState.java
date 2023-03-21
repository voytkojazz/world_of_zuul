package de.szut.zuul.model.states;

public class HealthyState implements State{

    private static State instance = null;

    private HealthyState() {}

    public static State getInstance() {
        if(instance == null) {
            instance = new HealthyState();
        }
        return instance;
    }

    @Override
    public State heal() {
        return HealthyState.getInstance();
    }

    @Override
    public State injure() {
        return InjuredState.getInstance();
    }

    @Override
    public String toString() {
        return "Healthy State";
    }
}
