package com.ashwinkudva.chipeightemu.desktop;

import com.ashwinkudva.chipeightemu.logic.Decoder;
import com.ashwinkudva.chipeightemu.logic.Emulator;
import com.ashwinkudva.chipeightemu.logic.Executer;
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
		if (arg.length == 0 || arg[0].equals("0")) {
			Emulator.romFilePath = "./c8games/TETRIS";
		} else if (arg[0].equals("1")) {
			Emulator.romFilePath = "./newhoney.hex";
		} else if (arg[0].equals("2")) {
			Emulator.romFilePath = "./c8games/TICTAC";
		} else if (arg[0].equals("3")) {
			Emulator.romFilePath = "./c8games/HIDDEN";
		} else if (arg[0].equals("4")) {
			Emulator.romFilePath = "./c8games/CONNECT4";
		}  else {
			Emulator.romFilePath = "./c8games/TETRIS";
		}
		
		new LwjglApplication(new Emulator(), config);
	}
}
