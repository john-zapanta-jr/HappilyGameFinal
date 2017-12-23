package com.zapantha.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.zapantha.game.HappilyGame;

/**
 * Created by sams on 12/18/2017.
 */

public class Megan {
    private Vector3 position;
    private Texture megan;
    public Megan(String character){
        megan = new Texture(character);
        position = new Vector3(HappilyGame.WIDTH/2 - megan.getWidth()/2, (HappilyGame.HEIGHT - 50)/2,0);
    }
    public Vector3 getPosition(){
        return position;
    }
    public Texture getTexture(){
        return megan;
    }
    public void setTexture(String character){
        megan.dispose();
        megan = new Texture(character);
    }

    public void move(char x){
        if(x == 'u')
            position.y += 50;
        else if(x == 'd')
            position.y -= 50;
        else if(x == 'l')
            position.x -= 50;
        else if(x == 'r')
            position.x += 50;
    }
}
