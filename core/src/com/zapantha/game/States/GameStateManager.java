package com.zapantha.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;
/**
 * Created by sams on 12/17/2017.
 */

public class GameStateManager {
    private Stack<State> states;
    private State lastState;
    public GameStateManager() {
        states = new Stack<State>();
        lastState = null;
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop() {
        states.pop();
    }

    public void set(State state) {
        lastState = states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public void getLastState(){
        states.pop();
        states.push(lastState);
    }
}
