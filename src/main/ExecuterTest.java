package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExecuterTest {
	
	Executer executer = new Executer();
	
	@Test
	void testAndVxVy() {
		Memory.registers[12] = 5;
		Memory.registers[14] = 2;
		executer.andVxVy((byte)12, (byte)14);
		assert(Memory.registers[12] == 0);
	}
	
	@Test
	void testOrVxVy() {
		Memory.registers[12] = 5;
		Memory.registers[14] = 2;
		executer.orVxVy((byte)12, (byte)14);
		assert(Memory.registers[12] == 7);
	}
	
	@Test
	void testAddByte() {
		Memory.registers[10] = 10;
		executer.addVxByte((byte)10, (byte)100);
		assert(Memory.registers[10] == 110);
	}
	
	@Test
	void testSubVxVy() {
		Memory.registers[10] = -1;
		Memory.registers[9] = -1;
		executer.subVxVy((byte)10, (byte)9);
		assert(Memory.registers[10] == 0);
	}
	
	@Test
	void testSeByte() {
		
	}

}
