package com.mygdx.alienswarm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.alienswarm.alienSwarm;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = alienSwarm.WIDTH; // sets window width
        config.height = alienSwarm.HEIGHT;  // sets window height
        new LwjglApplication(new alienSwarm(), config);
    }
}

//comment test