package com.zapantha.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zapantha.game.HappilyGame;
import com.zapantha.game.Misc.Love;
import com.zapantha.game.Sprites.Megan;
import com.zapantha.game.States.MovieState;

import java.io.FileNotFoundException;

import javax.xml.soap.Text;

import static com.badlogic.gdx.Gdx.input;

/**
 * Created by sams on 12/17/2017.
 */

public class PlayState extends State {
    private Texture background;
    private Megan megan;
    private Love love;
    private BitmapFont font;
    private static final int SPACING = 50;
    private boolean[] visited;

    public PlayState(GameStateManager gsm, Love love) {
        super(gsm,love);
        this.love = love;
        background = new Texture("Map.png");
        megan = new Megan("megan.png");
        visited = new boolean[]{false,false,false};
        font = new BitmapFont();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) ) {
            megan.move('d');
            megan.setTexture("megan.png");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            megan.move('u');
            megan.setTexture("MeganUp.png");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            megan.move('l');
            megan.setTexture("MeganLeft.png");
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            megan.move('r');
            megan.setTexture("MeganRight.png");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        if (visited[0] && (megan.getPosition().x >= 36 && megan.getPosition().x <= 136) && (megan.getPosition().y >= 400 && megan.getPosition().y <= 625)) {
            visited[1] = true;
            try{gsm.set(new MovieState(gsm,love));}catch(Exception e){}
            megan.move('r');
            megan.move('r');
        } else if (visited[2] && (megan.getPosition().x + SPACING >= 340 && megan.getPosition().x - SPACING <= 340) && (megan.getPosition().y + 25 >= 525 && megan.getPosition().y - SPACING <= 525)) {
            gsm.set(new ChurchState(gsm,love));
            megan.move('l');
            megan.move('l');
        } else if ((megan.getPosition().x + SPACING >= 40 && megan.getPosition().x - SPACING <= 40) && (megan.getPosition().y + SPACING >= 25 && megan.getPosition().y - 2*SPACING <= 25)) {
            visited[0] = true;
            try{gsm.set(new ParkState(gsm,love));} catch (Exception e){};
            megan.move('r');
            megan.move('r');
        } else if (visited[1] && (megan.getPosition().x + SPACING >= 340 && megan.getPosition().x - SPACING <= 340) && (megan.getPosition().y + SPACING >= 25 && megan.getPosition().y - SPACING <= 25)) {
            visited[2] = true;
            gsm.set(new CafeState(gsm,love));
            megan.move('l');
            megan.move('l');
        }
        System.out.println(love.get());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0,0,480,750);
        sb.draw(megan.getTexture(), megan.getPosition().x, megan.getPosition().y, megan.getTexture().getWidth(),megan.getTexture().getHeight());
        if(visited[0] == true && visited[1] == false)
            font.draw(sb, "Join john at the movies!",200,background.getHeight()*1/2);
        if(visited[0] == false)
            font.draw(sb, "Go to the park!",200,background.getHeight()*1/2);
        if(visited[1] == true && visited[2] == false)
            font.draw(sb, "Join john at the cafe!",200,background.getHeight()*2/4);
        if(visited[2] == true)
            font.draw(sb, "Take a look at your future!",200,background.getHeight()*2/4);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
