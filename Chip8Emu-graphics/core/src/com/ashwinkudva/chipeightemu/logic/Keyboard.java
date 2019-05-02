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
			Memory.keys[hex] = 1;
		}
		return true;
	}
	
	@Override
	public boolean keyUp(int code) {
		int hex = getHexKey(code);
		if (hex > -1) {
			System.out.println("KEY RELEASED: " + hex);
			Memory.keys[hex] = 0;
		}
		return true;
	}
	
	private int getHexKey(int code) {
		
		if (code == Input.Keys.NUM_0) {
			return 0x0;
		}
		else if (code == Input.Keys.NUM_1) {
			return 0x1;
		}
		else if (code == Input.Keys.NUM_2) {
			return 0x2;
		}
		else if (code == Input.Keys.NUM_3) {
			return 0x3;
		}
		else if (code == Input.Keys.NUM_4) {
			return 0x4;
		}		
		else if (code == Input.Keys.NUM_5) {
			return 0x5;
		}
		else if (code == Input.Keys.NUM_6) {
			return 0x6;
		}
		else if (code == Input.Keys.NUM_7) {
			return 0x7;
		}
		else if (code == Input.Keys.NUM_8) {
			return 0x8;
		}
		else if (code == Input.Keys.NUM_9) {
			return 0x9;
		}
		else if (code == Input.Keys.A) {
			return 0xA;
		}
		else if (code == Input.Keys.S) {
			return 0xB;
		}
		else if (code == Input.Keys.D) {
			return 0xC;
		}
		else if (code == Input.Keys.F) {
			return 0xD;
		}
		else if (code == Input.Keys.G) {
			return 0xE;
		}
		else if (code == Input.Keys.H) {
			return 0xF;
		}
		
		return -1;
	}
	
}
