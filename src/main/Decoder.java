package main;

public class Decoder {
	
	private Executer executer;
	private int opcode;
	
	public Decoder() {
		executer = new Executer(opcode);
	}
	
	public int decode(int opcode) { //return 1 for valid, 0 for invalid
		int highestNibble = extract(opcode, 15, 12);;
		int secondHighestNibble = extract(opcode, 11, 8);
		int secondLowestNibble = extract(opcode, 7, 4);
		int lowestNibble = extract(opcode, 3, 0);
		
		if (opcode == 0b0000000011100000) { //00E0 - CLS
			executer.cls();
			return 1;
		}
		else if (opcode == 0b0000000011101110) { //00EE - RET
			return 1;
		}
		else if (highestNibble == 0b0001) { //1nnn - JPP addr
			short nnn = (short) extract(opcode, 11, 0);
			executer.jumpAddr(nnn);
			return 1;
		}
		else if (highestNibble == 0b0010) { //2nnn - CALL addr
			return 1;
		}
		else if (highestNibble == 0b0011) { //3xkk - SE Vx, byte
			return 1;
		}
		else if (highestNibble == 0b0100) { //4xkk - SNE Vx, byte
			return 1;
		}
		else if (highestNibble == 0b0101 && lowestNibble == 0) { //5xy0 - SE Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b0110) { //6xkk - LD Vx, byte
			return 1;
		}
		else if (highestNibble == 0b0111) { //7xkk - ADD Vx, byte
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0) { //8xy0 - LD Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0001) { //8xy1 - OR Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0010) { //8xy2 - AND Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0011) { //8xy3 - XOR Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0100) { //8xy4 - ADD Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0101) { //8xy5 - SUB Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0110) { //8xy6 - SHR Vx, {, Vy}
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b0111) { //8xy7 - SUBN Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1000 && lowestNibble == 0b1110) { //8xyE - SHL Vx, {, Vy}
			return 1;
		}
		else if (highestNibble == 0b1001 && lowestNibble == 0) { //SNE Vx, Vy
			return 1;
		}
		else if (highestNibble == 0b1010) { //Annn - LD I, addr
			return 1;
		}
		else if (highestNibble == 0b1011) { ///Bnnn - JP V0, addr
			return 1;
		}
		else if (highestNibble == 0b1100) { //Cxkk - RND Vx, byte
			return 1;
		}
		else if (highestNibble == 0b1101) { //Dxyn - DRW Vx, Vx, nibble
			return 1;
		}
		else if (highestNibble == 0b1110 && secondLowestNibble == 0b1001 && lowestNibble == 0b1110) { //Ex9E - SKP Vx
			return 1;
		}
		else if (highestNibble == 0b1110 && secondLowestNibble == 0b1010 && lowestNibble == 0b0001) { //ExA1 - SKNP Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0 && lowestNibble == 0b0111) { //Fx07 - LD Vx, DT
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b0101) { //Fx15 - LD DT, Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b1000) { //Fx18 - LD ST, Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0001 && lowestNibble == 0b1110) { //Fx1E - ADD I, Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0010 && lowestNibble == 0b1001) { //Fx29 - LD F, Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0011 && lowestNibble == 0b0011) { //Fx33 - LD B, Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0101 && lowestNibble == 0b0101) { //Fx55 - LD [I], Vx
			return 1;
		}
		else if (highestNibble == 0b1111 && secondLowestNibble == 0b0110 && lowestNibble == 0b0101) { //Fx65 - LD Vx, [I]
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
