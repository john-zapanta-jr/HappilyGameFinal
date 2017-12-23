package com.zapantha.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zapantha.game.HappilyGame;
import com.zapantha.game.Misc.Love;

/**
 * Created by sams on 12/17/2017.
 */

public class MenuState extends State {
    private Texture background;
    private Texture startButton;
    private Love love;

    public MenuState(GameStateManager gsm, Love love){
        super(gsm,love);
        this.love = love;
        background = new Texture("background.png");
        startButton = new Texture("StartGame.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            gsm.set(new PlayState(gsm,love));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, HappilyGame.WIDTH, HappilyGame.HEIGHT-50);
        sb.draw(startButton, HappilyGame.WIDTH/2 - startButton.getWidth()/2 +30,HappilyGame.HEIGHT/4+50, 200,100);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        startButton.dispose();
    }
}
