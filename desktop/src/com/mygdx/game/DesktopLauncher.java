package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] args) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Catch kittens");
		cfg.setWindowIcon("Person.jpg");
		cfg.setForegroundFPS(144);
		cfg.setIdleFPS(144);
		cfg.useVsync(true);
		cfg.setWindowedMode(1280,720);
		new Lwjgl3Application(new Game(), cfg);
	}
}
