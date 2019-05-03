package com.ashwinkudva.chipeightemu.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Emulator extends Game {
	
	public static String romFilePath = "./c8games/KALEID";
	public static String testFilePath = "keyboardtest.hex";
	public GameScreen gameScreen;
	
	@Override
	public void create() {
		initializeROM();
		MenuScreen menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
		//setScreen(gameScreen);
	}
	
	private void initializeROM() {
		Memory.loadFonts();
		Memory.loadFile(romFilePath);
		//Memory.printMemory();
		Memory.pc = 512;
	}
	
}
