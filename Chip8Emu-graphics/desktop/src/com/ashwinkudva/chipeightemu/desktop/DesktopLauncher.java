package com.ashwinkudva.chipeightemu.desktop;

import com.ashwinkudva.chipeightemu.logic.Decoder;
import com.ashwinkudva.chipeightemu.logic.Emulator;
import com.ashwinkudva.chipeightemu.logic.GameScreen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.width = GameScreen.DISPLAY_WIDTH * GameScreen.DISPLAY_SCALE;
		config.height = GameScreen.DISPLAY_HEIGHT * GameScreen.DISPLAY_SCALE;
		config.title = "Chip8Emu";
		//config.backgroundFPS = 60;
		//config.foregroundFPS = 20;
		new LwjglApplication(new Emulator(), config);
	}
}
