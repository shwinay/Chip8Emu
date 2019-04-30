package main;

public class Decoder { 
	
	final private Executer executer;
	
	public Decoder(Graphics myGraphics) {
		executer = new Executer(myGraphics);
	}
	
	public int decode(short opcode) { //return 1 for valid, 0 for invalid
		int highestNibble = extract(opcode, 15, 12);;
		int secondHighestNibble = extract(opcode, 11, 8);
		int secondLowestNibble = extract(opcode, 7, 4);
		int lowestNibble = extract(opcode, 3, 0);
		
		// Set opcode
		executer.setOp(opcode);
		
		if (opcode == 0b0000000011100000) { //00E0 - CLS
			executer.cls();
			return 1;
		}
		else if (opcode == 0b0000000011101110) { //00EE - RET
			executer.ret();
			return 1;
		}
		else if (highestNibble == 0b0001) { //1nnn - JPP addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.jumpAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0b0010) { //2nnn - CALL addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.callAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0b0011) { //3xkk - SE Vx, byte
			short x = (short) extract(opcode, 11, 8);
			short kk = (short) extract(opcode, 7, 0);
			executer.seVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0b0100) { //4xkk - SNE Vx, byte
			short x = (short) extract(opcode, 11, 8);
			short kk = (short) extract(opcode, 7, 0);
			executer.sneVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0b0101 && lowestNibble == 0) { //5xy0 - SE Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.seVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b0110) { //6xkk - LD Vx, byte
			short x = (short) extract(opcode, 11, 8);
			short kk = (short) extract(opcode, 7, 0);
			executer.ldVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0b0111) { //7xkk - ADD Vx, byte
			short x = (short) extract(opcode, 11, 8);
			short kk = (short) extract(opcode, 7, 0);
			executer.addVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0) { //8xy0 - LD Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.ldVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0001) { //8xy1 - OR Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.orVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0010) { //8xy2 - AND Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.andVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0011) { //8xy3 - XOR Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.xorVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0100) { //8xy4 - ADD Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.addVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0101) { //8xy5 - SUB Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.subVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0110) { //8xy6 - SHR Vx, {, Vy}
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.shrVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0111) { //8xy7 - SUBN Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.subNVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b1110) { //8xyE - SHL Vx, {, Vy}
			short x = (short) extract(opcode, 11, 8);
			executer.shlVxVy(x);
			return 1;
		}
		else if (highestNibble == 0b1001 && lowestNibble == 0) { //SNE Vx, Vy
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			executer.sneVxVy(x, y);
			return 1;
		}
		else if (highestNibble == 0b1010) { //Annn - LD I, addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.ldIAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0b1011) { ///Bnnn - JP V0, addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.jumpV0Addr(nnn);
			return 1;
		}
		else if (highestNibble == 0b1100) { //Cxkk - RND Vx, byte
			short x = (short) extract(opcode, 11, 8);
			short kk = (short) extract(opcode, 7, 0);
			executer.rndVxByte(x, kk);
			return 1;
		}
		else if (highestNibble == 0b1101) { //Dxyn - DRW Vx, Vx, nibble
			short x = (short) extract(opcode, 11, 8);
			short y = (short) extract(opcode, 7, 4);
			short n = (short) extract(opcode, 3, 0);
			executer.drwVxVyNibble(x, y, n);
			return 1;
		}
		else if (highestNibble == 0b1110 && secondLowestNibble == 0b1001 && lowestNibble == 0b1110) { //Ex9E - SKP Vx
			short x = (short) extract(opcode, 11, 8);
			executer.skipVx(x);
			return 1;
		}
		else if (highestNibble == 0b1110 && secondLowestNibble == 0b1010 && lowestNibble == 0b0001) { //ExA1 - SKNP Vx
			short x = (short) extract(opcode, 11, 8);
			executer.skipNotPressedVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0 && lowestNibble == 0b0111) { //Fx07 - LD Vx, DT
			short x = (short) extract(opcode, 11, 8);
			executer.ldVxDt(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b0101) { //Fx15 - LD DT, Vx
			short x = (short) extract(opcode, 11, 8);
			executer.ldDtVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b1000) { //Fx18 - LD ST, Vx
			short x = (short) extract(opcode, 11, 8);
			executer.ldStVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b1110) { //Fx1E - ADD I, Vx
			short x = (short) extract(opcode, 11, 8);
			executer.addIVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0010 && lowestNibble == 0b1001) { //Fx29 - LD F, Vx
			short x = (short) extract(opcode, 11, 8);
			executer.LdFVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0011 && lowestNibble == 0b0011) { //Fx33 - LD B, Vx
			short x = (short) extract(opcode, 11, 8);
			executer.LdBVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0101 && lowestNibble == 0b0101) { //Fx55 - LD [I], Vx
			short x = (short) extract(opcode, 11, 8);
			executer.LdIVx(x);
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0110 && lowestNibble == 0b0101) { //Fx65 - LD Vx, [I]
			short x = (short) extract(opcode, 11, 8);
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
