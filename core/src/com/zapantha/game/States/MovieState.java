package com.zapantha.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.zapantha.game.Misc.Love;
import com.zapantha.game.Misc.Tree;
import com.zapantha.game.Sprites.Megan;
import com.zapantha.game.Story.ParkStory;
import com.zapantha.game.Text.MyTextInputListener;

import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by sams on 12/17/2017.
 */

public class MovieState extends State {
    private Megan megan;
    private Megan john;
    private Texture background;
    private TextArea text;
    private BitmapFont font;
    private String dialog;
    private MyTextInputListener listener;
    private ParkStory story;
    private Love love;
    private Tree responseTree;
    private Tree questionTree;
    private String response;
    private int responseSize;
    private boolean tally;
    private static final int WINDOWHEIGHT = 750;
    private static final int WINDOWWIDTH = 480;
    public MovieState(GameStateManager gsm, Love love) throws FileNotFoundException{
        super(gsm,love);
        this.love = love;
        response = "";
        responseSize = 0;
        responseTree = new Tree();
        responseTree = responseTree.createTree(new Scanner(new File("movieresponses.txt")));
        questionTree = new Tree();
        ArrayList<String> list = new ArrayList<String>();
        Scanner scan = new Scanner(new File("moviestory.txt")); //don't fill question spots for level 3
        while(scan.hasNextLine()){
            list.add(scan.nextLine()+'\n'+scan.nextLine()+'\n'+scan.nextLine()+'\n');
        }
        questionTree = questionTree.createTree(list);
        listener = new MyTextInputListener();
        dialog = "";
        text = new TextArea();
        font = new BitmapFont();
        background = new Texture("Movie.png");
        megan = new Megan("megan.png");
        john = new Megan("John.png");
        for (int i = 0; i < 3; i++){
            megan.move('l');
        }
        megan.move('d');
        megan.move('d');
        john.move('d');
        john.move('d');
        if(love.get() <= 2)
            dialog += "Hey I know it didn't go so well last time, but let's start over." + '\n';
        dialog += questionTree.get();
        Gdx.input.getTextInput(listener, "Megan says:", "", "enter response here");
        tally = false;
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            megan.move('d');
        else if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            megan.move('u');
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            megan.move('l');
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            megan.move('r');
        response += listener.returnText();
        if(response.length() > responseSize){
            dialog = responseTree.get(response);
            if(response.length() < 3) {
                dialog += '\n' + questionTree.get(response);
                Gdx.input.getTextInput(listener, "Megan says:", "", "enter response here");
            }
            listener.reset();
            responseSize++;
        }
        if(responseSize >= 3 && !tally){
            for(int j = 0; j < response.length(); j++){
                if(response.charAt(j) == 'A' || response.charAt(j) == 'a')
                    love.addLove();
                else if(response.charAt(j) == 'B' || response.charAt(j) == 'b')
                    love.minusLove();
            }
            tally = true;
        }
        System.out.println(dialog);
    }

    @Override
    public void update(float dt) {
        handleInput();
        if(megan.getPosition().x > WINDOWWIDTH || megan.getPosition().x < 0 ||
                megan.getPosition().y > WINDOWHEIGHT || megan.getPosition().y < 0) {
            gsm.getLastState();
            dispose();
        }
        System.out.println(love.get());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0,background.getWidth(),background.getHeight());
        sb.draw(megan.getTexture(),megan.getPosition().x,megan.getPosition().y,megan.getTexture().getWidth()*3/2,megan.getTexture().getHeight()*3/2);
        sb.draw(john.getTexture(),john.getPosition().x,john.getPosition().y,john.getTexture().getWidth()*3/2,john.getTexture().getHeight()*3/2);
        font.draw(sb,dialog,50,background.getHeight()*3/4,400, Align.center,true);
        text.setVisible(true);
        sb.end();
    }

    @Override
    public void dispose() {
        megan.getTexture().dispose();
        john.getTexture().dispose();
        background.dispose();
    }
}
