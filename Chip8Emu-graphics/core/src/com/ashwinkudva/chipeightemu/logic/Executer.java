package com.ashwinkudva.chipeightemu.logic;

import java.util.Arrays;
import java.util.Random;

public class Executer {
	
	//INSTANCE VARIABLES
	
	//CONSTRUCTOR
	public Executer() {
	}
	
	//METHODS
	public void cls() {
		for (int i = 0; i < Memory.pixels.length; i ++) {
			for (int j = 0; j < Memory.pixels[i].length; j ++) {
				Memory.pixels[i][j] = 0;
			}
		}
		Memory.pc += 2;
	}
	
	public void ret() {
		Memory.pc = Memory.stack[Memory.stackPointer];
		Memory.stackPointer--;
		Memory.pc += 2;
	}
	
	public void jumpAddr(short nnn) {
		Memory.pc = (nnn & 0x0FFF);
	}
	
	public void callAddr(short nnn) {
		Memory.stackPointer++;
		Memory.stack[Memory.stackPointer] = Memory.pc;
		Memory.pc = (nnn & 0x0FFF);
		//System.out.println("calladdr.. pc set to " + (nnn & 0x0FFF));
	}
	
	public void seVxByte(short x, short kk) {
		if (Memory.registers[x] == kk) {
			Memory.pc += 2;
		}
		Memory.pc += 2;
	}
	
	public void sneVxByte(short x, short kk) {
		if (Memory.registers[x] != kk) {
			Memory.pc += 2;
		}
		Memory.pc += 2;
	}
	
	public void seVxVy(short x, short y) {
		if (Memory.registers[x] == Memory.registers[y]) {
			Memory.pc += 2;
		}
		Memory.pc += 2;
	}
	
	public void ldVxByte(short x, short kk) {
		Memory.registers[x] = (short) (kk & 0xFF);
		//System.out.println("register " + x + ": " + (kk & 0xFF));
		Memory.pc += 2;
	}
	
	public void addVxByte(short x, short kk) {
		short result = (short) (Memory.registers[x] + kk);
		Memory.registers[x] = (short) (result & 0xFF);
		Memory.pc += 2;
	}
	
	public void ldVxVy(short x, short y) {
		Memory.registers[x] = (short) (Memory.registers[y] & 0xFF);
		Memory.pc += 2;
	}
	
	public void orVxVy(short x, short y) {
		Memory.registers[x] = (short) ((Memory.registers[x] & 0xFF) | (Memory.registers[y] & 0xFF));
		Memory.pc += 2;
	}
	
	public void andVxVy(short x, short y) {
		Memory.registers[x] = (short) ((Memory.registers[x] & 0xFF) & (Memory.registers[y] & 0xFF));
		Memory.pc += 2;
	}
	
	public void xorVxVy(short x, short y) {
		Memory.registers[x] = (short) ((Memory.registers[x] & 0xFF) ^ (Memory.registers[y] & 0xFF));
		Memory.pc += 2;
	}
	
	public void addVxVy(short x, short y) {
		short result = (short) ((Memory.registers[x] & 0xFF) + (Memory.registers[y] & 0xFF));
		if (result > 255) {
			Memory.registers[15] = 1;
		}
		else {
			Memory.registers[15] = 0;
		}
		Memory.registers[x] = (short) (result & 0xFF);
		Memory.pc += 2;
	}
	
	public void subVxVy(short x, short y) {
		Memory.registers[15] = (short) ((Memory.registers[x] & 0xFF) > (Memory.registers[y] & 0xFF) ? 1 : 0); //set flag
		Memory.registers[x] = (short) ((Memory.registers[x] & 0xFF) - (Memory.registers[y] & 0xFF) & 0xFF);
		Memory.pc += 2;
	}
	
	public void shrVxVy(short x, short y) { //what to do with y?
		short vx = (short)(Memory.registers[x] & 0xFF);
		Memory.registers[15] = (short) Decoder.extract(vx, 0, 0);
		Memory.registers[x] = (short) ((vx >>> 1) & 0xFF);//TODO: Double check
		Memory.pc += 2;
	}
	
	public void subNVxVy(short x, short y) {
		Memory.registers[15] = (short) ((Memory.registers[y] & 0xFF) > (Memory.registers[x] & 0xFF) ? 1 : 0);
		Memory.registers[x] = (short) (((Memory.registers[y] & 0xFF) - (Memory.registers[x] & 0xFF)) & 0xFF);
		Memory.pc += 2;
	}
	
	public void shlVxVy(short x) {
		Memory.registers[15] = (short) Decoder.extract(Memory.registers[x], 7, 7);
		Memory.registers[x] = (short) (((Memory.registers[x] & 0xFF) << 1) & 0xFF);
		Memory.pc += 2;
	}
	
	public void sneVxVy(short x, short y) {
		if (Memory.registers[x] != Memory.registers[y]) {
			Memory.pc += 2;
		}
		Memory.pc += 2;
	}
	
	public void ldIAddr(short nnn) {
		Memory.iRegister = (short) (nnn & 0x0FFF);
		//System.out.println("i: " + Memory.iRegister);
		Memory.pc += 2;
	}
	
	public void jumpV0Addr(short nnn) {
		Memory.pc = (nnn & 0x0FFF) + (Memory.registers[0] & 0xFF);
	}
	
	public void rndVxByte(short x, short kk) {
		Random rand = new Random();
		short randomNumber = (short) rand.nextInt(256);
		Memory.registers[x] = (short) (randomNumber & (kk & 0xFF));
		Memory.pc += 2;
	}
	
	public void drwVxVyNibble(short x, short y, short n) {
		System.out.println("drawing");
		short coordX = (short) (Memory.registers[x] & 0xFF);
		short coordY = (short) (Memory.registers[y] & 0xFF);
		short[] sprite = new short[n];
		boolean hasCollided = false;
		for (int i = 0; i < n; i ++) {
			//System.out.println("At loc " + (Memory.iRegister + i) + ": " + Memory.memory[Memory.iRegister + i]);
			sprite[i] = Memory.memory[Memory.iRegister + i];
		}
		
		//System.out.println(Arrays.toString(sprite));
		
		for (int x_ = 0; x_ < 8; x_ ++) {
			for (int y_ = 0; y_ < n; y_ ++) {
				int beforePixel = Memory.pixels[(coordY + y_) % GameScreen.DISPLAY_HEIGHT][(coordX + x_) % GameScreen.DISPLAY_WIDTH];
				int extractPixel = Decoder.extract(sprite[y_], 8 - x_ - 1, 8 - x_ - 1);
				if ((beforePixel & extractPixel) == 1) {
					hasCollided = true;
				}
				//System.out.println("Before pixel at (" + (x_) + ", " + (y_) + "): " + Memory.pixels[coordY + y_][coordX + x_]);
				Memory.pixels[(coordY + y_) % GameScreen.DISPLAY_HEIGHT][(coordX + x_) % GameScreen.DISPLAY_WIDTH] = beforePixel ^ extractPixel;
				//System.out.println("After pixel at (" + (x_) + ", " + (y_) + "): " + Memory.pixels[coordY + y_][coordX + x_]);
			}
		}
		Memory.registers[15] = (short) (hasCollided ? 1 : 0);
		
		Memory.pc += 2;
	}
	
	public void skipVx(short x) {
		if (Memory.keys[Memory.registers[x]]) {
			System.out.println("hit: " + x);
			Memory.pc += 2;
		} else {
			System.out.println("miss: " + x);
		}
		Memory.pc += 2;
	}
	
	public void skipNotPressedVx(short x) {
		if (!Memory.keys[Memory.registers[x]]) {
			System.out.println("miss: " + x);
			Memory.pc += 2;
		} else {
			System.out.println("hit: " + x);
		}
		Memory.pc += 2;
	}
	
	public void ldVxDt(short x) {
		Memory.registers[x] = Memory.delayRegister;
		Memory.pc += 2;
	}
	
	public void ldVxK(short x) {
		x = (short) (x & 0xFF);
		if (Memory.ldVxkStart == false) {
			Memory.ldVxkStart = true;
			Memory.keyPressed = -1;
			System.out.println("waiting: " + x);
		}
		
		if (Memory.keyPressed != -1) {
			System.out.println("hit: " + Memory.keyPressed);
			System.out.println("Done waiting: " + x);
			Memory.registers[x] = (short) ((Memory.keyPressed) & 0xFF);
			Memory.pc += 2;
			Memory.ldVxkStart = false;
		}
		
	}
	
	public void ldDtVx(short x) {
		Memory.delayRegister = (short) (Memory.registers[x] & 0xFF);
		Memory.pc += 2;
	}
	
	public void ldStVx(short x) {
		Memory.soundRegister = (short) (Memory.registers[x] & 0xFF);
		Memory.soundEffect.play();
		Memory.pc += 2;
	}
	
	public void addIVx(short x) {
		Memory.iRegister = (Memory.iRegister + (Memory.registers[x] & 0xFF)) & 0xFFFF;
		Memory.pc += 2;
	}
	
	public void LdFVx(short x) {
		short fontByte = Memory.registers[x];
		Memory.iRegister = fontByte * 5;
		Memory.pc += 2;
	}
	
	public void LdBVx(short x) {
		short hundredsDigit = (short) ((Memory.registers[x] / 100) % 10);
		short tensDigit = (short) ((Memory.registers[x] / 10) % 10);
		short onesDigit = (short) (Memory.registers[x] % 10);
		Memory.memory[Memory.iRegister] = hundredsDigit;
		Memory.memory[Memory.iRegister + 1] = tensDigit;
		Memory.memory[Memory.iRegister + 2] = onesDigit;
		Memory.pc += 2;
	}
	
	public void LdIVx(short x) {
		
		for (int i = 0; i <= x; i ++) {
			Memory.memory[Memory.iRegister + i] = (short) (Memory.registers[i] & 0xFF);
		}
		
		Memory.pc += 2;
	}
	
	public void LdVxI(short x) {
		
		for (int i = 0; i <= x; i ++) {
			Memory.registers[i] = (short) (Memory.memory[Memory.iRegister + i] & 0xFF);
		}
		
		Memory.pc += 2;
	}
	
}
