package com.mygdx.alienswarm;

import com.badlogic.gdx.Game;

public class alienSwarm extends Game {
	public static final String TITLE = "Alien Invasion";
	public static final int WIDTH = 988, HEIGHT = 677; // used later to set
														// window size

	@Override
	public void create() {
		setScreen(new MenuScreen());
	}
}