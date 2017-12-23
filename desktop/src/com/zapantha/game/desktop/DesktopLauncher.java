package com.zapantha.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zapantha.game.HappilyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = HappilyGame.WIDTH;
		config.height = HappilyGame.HEIGHT;
		config.title = HappilyGame.TITLE;
		new LwjglApplication(new HappilyGame(), config);
	}
}
