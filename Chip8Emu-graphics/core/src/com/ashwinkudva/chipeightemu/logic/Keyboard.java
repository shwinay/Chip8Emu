package com.ashwinkudva.chipeightemu.logic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class Keyboard extends InputAdapter {

	//CONSTRUCTORS
	
	//CONSTRUCTOR
	public Keyboard() {
	}
	
	//METHODS
	@Override
	public boolean keyDown(int code) {
		int hex = getHexKey(code);
		if (hex > -1) {
			System.out.println("KEY PRESSED: " + hex);
			Memory.keys[hex] = true;
			Memory.keyPressed = hex;
		}
		return true;
	}
	
	@Override
	public boolean keyUp(int code) {
		int hex = getHexKey(code);
		if (hex > -1) {
			System.out.println("KEY RELEASED: " + hex);
			Memory.keys[hex] = false;
		}
		return true;
	}
	
	private int getHexKey(int code) {
		switch (code) {
		case Input.Keys.NUM_1:
			return 0x0;
		case Input.Keys.NUM_2:
			return 0x1;
		case Input.Keys.NUM_3:
			return 0x2;
		case Input.Keys.NUM_4:
			return 0x3;
		case Input.Keys.Q:
			return 0x4;
		case Input.Keys.W:
			return 0x5;
		case Input.Keys.E:
			return 0x6;
		case Input.Keys.R:
			return 0x7;
		case Input.Keys.A:
			return 0x8;
		case Input.Keys.S:
			return 0x9;
		case Input.Keys.D:
			return 0xA;
		case Input.Keys.F:
			return 0xB;
		case Input.Keys.Z:
			return 0xC;
		case Input.Keys.X:
			return 0xD;
		case Input.Keys.C:
			return 0xE;
		case Input.Keys.V:
			return 0xF;
		}
		
		return -1;
	}
	
}
