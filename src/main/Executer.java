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
	}
	
	public void jumpAddr(short nnn) {
		Memory.pc = nnn;
	}
	
	public void callAddr(short nnn) {
		Memory.stackPointer++;
		Memory.stack[Memory.stackPointer] = Memory.pc;
		Memory.pc = nnn;
	}
	
	public void seVxByte(byte x, byte kk) {
		if (Memory.registers[x] == kk) {
			Memory.pc += 2;
		}
	}
	
	public void sneVxByte(byte x, byte kk) {
		if (Memory.registers[x] != kk) {
			Memory.pc += 2;
		}
	}
	
	public void seVxVy(byte x, byte y) {
		if (Memory.registers[x] == Memory.registers[y]) {
			Memory.pc += 2;
		}
	}
	
	public void ldVxByte(byte x, byte kk) {
		Memory.registers[x] = kk;
	}
	
	public void addVxByte(byte x, byte kk) {
		Memory.registers[x] += kk;
	}
	
	public void ldVxVy(byte x, byte y) {
		Memory.registers[x] = Memory.registers[y];
	}
	
	public void orVxVy(byte x, byte y) {
		Memory.registers[x] = (byte) (Memory.registers[x] | Memory.registers[y]);
	}
	
	public void andVxVy(byte x, byte y) {
		Memory.registers[x] = (byte) (Memory.registers[x] & Memory.registers[y]);
	}
	
	public void xorVxVy(byte x, byte y) {
		Memory.registers[x] = (byte) (Memory.registers[x] ^ Memory.registers[y]);
	}
	
	public void addVxVy(byte x, byte y) {
		byte result = (byte) (Memory.registers[x] + Memory.registers[y]);
		if (result > 255) {
			Memory.registers[15] = 1;
			result = (byte) Decoder.extract(result, 7, 0);
		}
		else {
			Memory.registers[15] = 0;
		}
		Memory.registers[x] = (byte) result;

	}
	
	public void subVxVy(byte x, byte y) {
		Memory.registers[15] = (byte) ((Memory.registers[x]&0xFF) > (Memory.registers[x]&0xFF) ? 1 : 0); //set flag
		Memory.registers[x] = (byte) ((Memory.registers[x]&0xFF) - (Memory.registers[y]&0xFF));
	}
	
	public void shrVxVy(byte x, byte y) { //what to do with y?
		short vx = (short)(((short)Memory.registers[x]) & 0x00FF);
		Memory.registers[15] = (byte) Decoder.extract(vx, 0, 0);
		Memory.registers[x] = (byte) (vx >> 1);
	}
	
	public void subNVxVy(byte x, byte y) {
		Memory.registers[15] = (byte) (Memory.registers[y] > Memory.registers[x] ? 1 : 0);
		Memory.registers[x] = (byte) (Memory.registers[y] - Memory.registers[x]);
	}
	
	public void shlVxVy(byte x) {
		Memory.registers[15] = (byte) Decoder.extract(Memory.registers[x], 7, 7);
		Memory.registers[x] <<= 1;
	}
	
	public void sneVxVy(byte x, byte y) {
		if (Memory.registers[x] != Memory.registers[y]) {
			Memory.pc += 2;
		}
	}
	
	public void ldIAddr(short nnn) {
		Memory.iRegister = nnn;
	}
	
	public void jumpV0Addr(short nnn) {
		Memory.pc = nnn + Memory.registers[0];
	}
	
	public void rndVxByte(byte x, byte kk) {
		Random rand = new Random();
		byte randomNumber = (byte) rand.nextInt(256);
		Memory.registers[x] = (byte) (randomNumber & kk);
	}
	
	public void drwVxVyNibble(byte x, byte y, byte n) {
		
	}
	
	public void skipVx(byte x) {
		
	}
	
	public void skipNotPressedVx(byte x) {
		
	}
	
	public void ldVxDt(byte x) {
		Memory.registers[x] = Memory.delayRegister;
	}
	
	public void ldVxK() {
		
	}
	
	public void ldDtVx(byte x) {
		Memory.delayRegister = Memory.registers[x];
	}
	
	public void ldStVx(byte x) {
		Memory.soundRegister = Memory.registers[x];
	}
	
	public void addIVx(byte x) {
		Memory.iRegister += Memory.registers[x];
	}
	
	public void LdFVx(byte x) {

	}
	
	public void LdBVx(byte x) {
		byte hundredsDigit = (byte) ((Memory.registers[x] / 100) % 10);
		byte tensDigit = (byte) ((Memory.registers[x] / 10) % 10);
		byte onesDigit = (byte) (Memory.registers[x] % 10);
		Memory.memory[Memory.iRegister] = hundredsDigit;
		Memory.memory[Memory.iRegister + 1] = tensDigit;
		Memory.memory[Memory.iRegister + 2] = onesDigit;		
	}
	
	public void LdIVx(byte x) {
		
	}
	
	public void LdVxI(byte x) {
		
	}
	
}
