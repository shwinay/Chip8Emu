package com.ashwinkudva.chipeightemu.logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Emulator extends Game {
	
	public static String romFilePath = "./c8games/INVADERS";
	public static String testFilePath = "./test_opcode.ch8";
	
	@Override
	public void create() {
		initializeROM();
		GameScreen gameScreen = new GameScreen(this);
		Gdx.input.setInputProcessor(gameScreen.keyboard);
		setScreen(gameScreen);
	}
	
	private void initializeROM() {
		Memory.loadFonts();
		Memory.loadFile(romFilePath);
		//Memory.printMemory();
		Memory.pc = 512;
	}
	
}
