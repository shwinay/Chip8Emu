package com.ashwinkudva.chipeightemu.logic;

import com.badlogic.gdx.Game;

public class Emulator extends Game {
	
	public static String romFilePath = "./c8games/PONG2";
	
	@Override
	public void create() {
		initializeROM();
		GameScreen gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	
	private void initializeROM() {
		Memory.loadFonts();
		Memory.loadFile(romFilePath);
		Memory.pc = 512;
	}
	
}
