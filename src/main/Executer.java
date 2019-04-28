package main;

import java.util.Random;

public class Executer {
	
	//INSTANCE VARIABLES
	private int opcode;
	
	//CONSTRUCTOR
	public Executer(int opcode) {
		this.opcode = opcode;
	}
	
	//METHODS
	public void cls() {
		
	}
	
	public void ret() {
		Memory.pc = Memory.stack[Memory.stackPointer];
		Memory.stackPointer --;
	}
	
	public void jumpAddr(short nnn) {
		Memory.pc = nnn;
	}
	
	public void callAddr(short nnn) {
		Memory.stackPointer ++;
		Memory.stack[Memory.stackPointer] = Memory.pc;
		Memory.pc = nnn;
	}
	
	public void seVxByte(short x, short kk) {
		if (Memory.registers[x] == kk) {
			Memory.pc += 2;
		}
	}
	
	public void sneVxByte(short x, short kk) {
		if (Memory.registers[x] != kk) {
			Memory.pc += 2;
		}
	}
	
	public void seVxVy(short x, short y) {
		if (Memory.registers[x] == Memory.registers[y]) {
			Memory.pc += 2;
		}
	}
	
	public void ldVxByte(short x, short kk) {
		Memory.registers[x] = kk;
	}
	
	public void addVxByte(short x, short kk) {
		Memory.registers[x] += kk;
	}
	
	public void ldVxVy(short x, short y) {
		Memory.registers[x] = Memory.registers[y];
	}
	
	public void orVxVy(short x, short y) {
		Memory.registers[x] = (short) (Memory.registers[x] | Memory.registers[y]);
	}
	
	public void andVxVy(short x, short y) {
		Memory.registers[x] = (short) (Memory.registers[x] & Memory.registers[y]);
	}
	
	public void xorVxVy(short x, short y) {
		Memory.registers[x] = (short) (Memory.registers[x] ^ Memory.registers[y]);
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
		Memory.registers[x] = result;

	}
	
	public void subVxVy(short x, short y) {
		Memory.registers[15] = (short) (Memory.registers[x] > Memory.registers[y] ? 1 : 0);
		Memory.registers[x] -= Memory.registers[y];
	}
	
	public void shrVxVy(short x, short y) { //what to do with y?
		short vx = Memory.registers[x];
		Memory.registers[15] = (short) Decoder.extract(vx, 0, 0);
		Memory.registers[x] = (short) (vx >> 1);
	}
	
	public void subNVxVy(short x, short y) {
		Memory.registers[15] = (short) (Memory.registers[y] > Memory.registers[x] ? 1 : 0);
		Memory.registers[x] = (short) (Memory.registers[y] - Memory.registers[x]);
	}
	
	public void shlVxVy(short x) {
		Memory.registers[15] = (short) Decoder.extract(Memory.registers[x], 7, 7);
		Memory.registers[x] <<= 1;
	}
	
	public void sneVxVy(short x, short y) {
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
	
	public void rndVxByte(short x, short kk) {
		Random rand = new Random();
		short randomNumber = (short) rand.nextInt(256);
		Memory.registers[x] = (short) (randomNumber & kk);
	}
	
	public void drwVxVyNibble(short x, short y, short n) {
		
	}
	
	public void skipVx(short x) {
		
	}
	
	public void skipNotPressedVx(short x) {
		
	}
	
	public void ldVxDt(short x) {
		Memory.registers[x] = Memory.delayRegister;
	}
	
	public void ldVxK() {
		
	}
	
	public void ldDtVx(short x) {
		Memory.delayRegister = Memory.registers[x];
	}
	
	public void ldStVx(short x) {
		Memory.soundRegister = Memory.registers[x];
	}
	
	public void addIVx(short x) {
		Memory.iRegister += Memory.registers[x];
	}
	
	public void LdFVx(short x) {

	}
	
	public void LdBVx(short x) {
		byte hundredsDigit = (byte) ((Memory.registers[x] / 100) % 10);
		byte tensDigit = (byte) ((Memory.registers[x] / 10) % 10);
		byte onesDigit = (byte) (Memory.registers[x] % 10);
		Memory.memory[Memory.iRegister] = hundredsDigit;
		Memory.memory[Memory.iRegister + 1] = tensDigit;
		Memory.memory[Memory.iRegister + 2] = onesDigit;		
	}
	
	public void LdIVx(short x) {
		
	}
	
	public void LdVxI(short x) {
		
	}
	
}
