package com.zapantha.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.zapantha.game.Misc.Love;
import com.zapantha.game.Sprites.Megan;
import com.zapantha.game.Text.MyTextInputListener;

import java.awt.TextArea;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by sams on 12/17/2017.
 */

public class ChurchState extends State {
    private Megan megan;
    private Megan john;
    private Texture background;
    private Megan dog;
    private Love love;
    private BitmapFont font;
    private Scanner scan;
    private int counter;
    private String dialog;
    private Random rand;
    private static final int WINDOWHEIGHT = 750;
    private static final int WINDOWWIDTH = 480;
    public ChurchState(GameStateManager gsm, Love love) {
        super(gsm,love);
        this.love = love;
        rand = new Random();
        counter = 0;
        dialog = "";
        scan = new Scanner("");
        try {
            if(love.get() > 2)
                scan = new Scanner(new File("EndStoryGood.txt"));
            else
                scan = new Scanner(new File("EndStoryBad.txt"));
        } catch(Exception e){}
        background = new Texture("Church.png");
        megan = new Megan("megan.png");
        john = new Megan("John.png");
        dog = new Megan("dog.png");
        if(love.get() <= 2) {
            john = new Megan("JohnBad.png");
            background = new Texture("ChurchBad.png");
        }
        john.move('r');
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        if(counter % 3 == 0)
            dialog += scan.nextLine()+'\n';
        if(megan.getPosition().x > WINDOWWIDTH || megan.getPosition().x < 0 ||
           megan.getPosition().y > WINDOWHEIGHT || megan.getPosition().y < 0) {
            gsm.getLastState();
            dispose();
        }
        int moves = 0;
        if (love.get() > 2) {
            moves = rand.nextInt(6);
            if (moves == 0 || moves == 2)
                dog.move('l');
            else if (moves == 1 || moves == 3)
                dog.move('r');
            else if (moves == 4)
                dog.move('u');
            else
                dog.move('d');
        }
        try{Thread.sleep(500);}catch(Exception e){}
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0,background.getWidth(),background.getHeight());
        if(love.get() > 2)
            sb.draw(dog.getTexture(),dog.getPosition().x,dog.getPosition().y,dog.getTexture().getWidth()*3/4,dog.getTexture().getHeight()*3/4);
        sb.draw(megan.getTexture(),megan.getPosition().x,megan.getPosition().y,megan.getTexture().getWidth(),megan.getTexture().getHeight());
        sb.draw(john.getTexture(),john.getPosition().x,john.getPosition().y,john.getTexture().getWidth(),john.getTexture().getHeight());
        font.draw(sb,dialog,50,background.getHeight()*3/4,400, Align.center,true);
        sb.end();
        counter++;
    }

    @Override
    public void dispose() {
        megan.getTexture().dispose();
        john.getTexture().dispose();
        background.dispose();
        dog.getTexture().dispose();
    }
}
