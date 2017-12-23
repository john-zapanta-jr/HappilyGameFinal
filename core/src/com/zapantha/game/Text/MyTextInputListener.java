package com.zapantha.game.Text;

import com.badlogic.gdx.Input;

/**
 * Created by sams on 12/19/2017.
 */

public class MyTextInputListener implements Input.TextInputListener {
    private String message;

    public MyTextInputListener(){
        message = "";
    }
    @Override
    public void input (String text) {
        message = text;
    }

    @Override
    public void canceled () {
    }

    public String returnText(){
        return message;
    }
    public void reset(){
        message = "";
    }
}
