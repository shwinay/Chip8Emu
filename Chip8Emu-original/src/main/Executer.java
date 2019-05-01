package main;

import java.util.Random;

public class Executer {
	
	//INSTANCE VARIABLES
	
	//CONSTRUCTOR
	public Executer() {
	}
	
	//METHODS
	public void cls() {
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
		System.out.println("calladdr.. pc set to " + (nnn & 0x0FFF));
		Memory.pc += 2;
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
		Memory.registers[x] = kk;
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
		short result = (short) (Memory.registers[x] + Memory.registers[y]);
		if (result > 255) {
			Memory.registers[15] = 1;
			result = (short) Decoder.extract(result, 7, 0);
		}
		else {
			Memory.registers[15] = 0;
		}
		Memory.registers[x] = (short) result;
		Memory.pc += 2;
	}
	
	public void subVxVy(short x, short y) {
		Memory.registers[15] = (short) ((Memory.registers[x] & 0xFF) > (Memory.registers[x] & 0xFF) ? 1 : 0); //set flag
		Memory.registers[x] = (short) ((Memory.registers[x] & 0xFF) - (Memory.registers[y] & 0xFF) & 0xFF);
		Memory.pc += 2;
	}
	
	public void shrVxVy(short x, short y) { //what to do with y?
		short vx = (short)(Memory.registers[x] & 0xFF);
		Memory.registers[15] = (short) Decoder.extract(vx, 0, 0);
		Memory.registers[x] = (short) ((vx >> 1) & 0xFF);
		Memory.pc += 2;
	}
	
	public void subNVxVy(short x, short y) {
		Memory.registers[15] = (short) ((Memory.registers[y] & 0xFF) > (Memory.registers[x] & 0xFF) ? 1 : 0);
		Memory.registers[x] = (short) (((Memory.registers[y] & 0xFF) - (Memory.registers[x] & 0xFF)) & 0xFF);
		Memory.pc += 2;
	}
	
	public void shlVxVy(short x) {
		Memory.registers[15] = (short) Decoder.extract(Memory.registers[x], 7, 7);
		Memory.registers[x] = (short) ((Memory.registers[x] << 1) & 0xFF);
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
		Memory.pc += 2;
	}
	
	public void skipVx(short x) {
		Memory.pc += 2;
	}
	
	public void skipNotPressedVx(short x) {
		Memory.pc += 2;
	}
	
	public void ldVxDt(short x) {
		Memory.registers[x] = Memory.delayRegister;
		Memory.pc += 2;
	}
	
	public void ldVxK() {
		Memory.pc += 2;
	}
	
	public void ldDtVx(short x) {
		Memory.delayRegister = Memory.registers[x];
		Memory.pc += 2;
	}
	
	public void ldStVx(short x) {
		Memory.soundRegister = Memory.registers[x];
		Memory.pc += 2;
	}
	
	public void addIVx(short x) {
		Memory.iRegister += Memory.registers[x];
		Memory.pc += 2;
	}
	
	public void LdFVx(short x) {
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
		Memory.pc += 2;
	}
	
	public void LdVxI(short x) {
		Memory.pc += 2;
	}
	
}
