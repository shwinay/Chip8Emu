package com.ashwinkudva.chipeightemu.logic;

public class Decoder { 
	
	final private Executer executer;
	
	public Decoder() {
		executer = new Executer();
	}
	
	public int decode(short opcode) { //return 1 for valid, 0 for invalid
		System.out.println("decoding: " + opcode);
		int highestNibble = extract(opcode, 15, 12);
		int secondHighestNibble = extract(opcode, 11, 8);
		int secondLowestNibble = extract(opcode, 7, 4);
		int lowestNibble = extract(opcode, 3, 0);
		
		
		if (opcode == 0x00E0) { //00E0 - CLS
			//System.out.println("cls");
			executer.cls();
			return 1;
		}
		else if (opcode == 0x00EE) { //00EE - RET
			//System.out.println("ret");
			executer.ret();
			return 1;
		}
		else if (highestNibble == 0x1) { //1nnn - JPP addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.jumpAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0x2) { //2nnn - CALL addr
			//System.out.println("call");
			short nnn = (short) extract(opcode, 11, 0);
			executer.callAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0x3) { //3xkk - SE Vx, byte
			//System.out.println("se");
			byte x = (byte) extract(opcode, 11, 8);
			byte kk = (byte) extract(opcode, 7, 0);
			executer.seVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0x4) { //4xkk - SNE Vx, byte
			//System.out.println("sne");
			byte x = (byte) extract(opcode, 11, 8);
			byte kk = (byte) extract(opcode, 7, 0);
			executer.sneVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0x5 && lowestNibble == 0) { //5xy0 - SE Vx, Vy
			//System.out.println("sevxvy");
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.seVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x6) { //6xkk - LD Vx, byte
			//System.out.println("ldvx");
			byte x = (byte) extract(opcode, 11, 8);
			byte kk = (byte) extract(opcode, 7, 0);
			executer.ldVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0x7) { //7xkk - ADD Vx, byte
			byte x = (byte) extract(opcode, 11, 8);
			byte kk = (byte) extract(opcode, 7, 0);
			executer.addVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0) { //8xy0 - LD Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.ldVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x1) { //8xy1 - OR Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.orVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x2) { //8xy2 - AND Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.andVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x3) { //8xy3 - XOR Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.xorVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x4) { //8xy4 - ADD Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.addVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x5) { //8xy5 - SUB Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.subVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x6) { //8xy6 - SHR Vx, {, Vy}
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.shrVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0x7) { //8xy7 - SUBN Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.subNVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0x8 && lowestNibble == 0xE) { //8xyE - SHL Vx, {, Vy}
			byte x = (byte) extract(opcode, 11, 8);
			executer.shlVxVy(x);
			return 1;
		}
		else if (highestNibble == 0x9 && lowestNibble == 0) { //SNE Vx, Vy
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			executer.sneVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0xA) { //Annn - LD I, addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.ldIAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0xB) { ///Bnnn - JP V0, addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.jumpV0Addr(nnn);
			return 1;
		}
		else if (highestNibble == 0xC) { //Cxkk - RND Vx, byte
			byte x = (byte) extract(opcode, 11, 8);
			byte kk = (byte) extract(opcode, 7, 0);
			executer.rndVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0xD) { //Dxyn - DRW Vx, Vy, nibble
			byte x = (byte) extract(opcode, 11, 8);
			byte y = (byte) extract(opcode, 7, 4);
			byte n = (byte) extract(opcode, 3, 0);
			executer.drwVxVyNibble(x, y, n);
			return 1;
		}
		else if (highestNibble == 0xE && secondLowestNibble == 0x9 && lowestNibble == 0xE) { //Ex9E - SKP Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.skipVx(x);
			return 1;
		}
		else if (highestNibble == 0xE && secondLowestNibble == 0xA && lowestNibble == 0x1) { //ExA1 - SKNP Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.skipNotPressedVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x0 && lowestNibble == 0x7) { //Fx07 - LD Vx, DT
			byte x = (byte) extract(opcode, 11, 8);
			executer.ldVxDt(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x0 && lowestNibble == 0xA) { //Fx0A - LD Vx, K
			short x = (short)(extract(opcode, 11, 8) & 0xFF);
			executer.ldVxK(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x1 && lowestNibble == 0x5) { //Fx15 - LD DT, Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.ldDtVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x1 && lowestNibble == 0x8) { //Fx18 - LD ST, Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.ldStVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x1 && lowestNibble == 0xE) { //Fx1E - ADD I, Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.addIVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x2 && lowestNibble == 0x9) { //Fx29 - LD F, Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.LdFVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x3 && lowestNibble == 0x3) { //Fx33 - LD B, Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.LdBVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x5 && lowestNibble == 0x5) { //Fx55 - LD [I], Vx
			byte x = (byte) extract(opcode, 11, 8);
			executer.LdIVx(x);
			return 1;
		}
		else if (highestNibble == 0xF && secondLowestNibble == 0x6 && lowestNibble == 0x5) { //Fx65 - LD Vx, [I]
			byte x = (byte) extract(opcode, 11, 8);
			executer.LdVxI(x);
			return 1;
		}
		
		return 0;
	}
	
	public static int extract(int bits, int left, int right) {
		int shiftedRight = bits >> right;
		int onesMask = (1 << (left - right + 1)) - 1;
		return shiftedRight & onesMask;
	}
	
}
