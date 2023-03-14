package de.szut.zuul.model.states;

public interface State {
    State heal();
    State injure();
}
