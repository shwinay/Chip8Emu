package main;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Memory {
	
	//INSTANCE VARIABLES
	public static final int NUM_REGISTERS = 8;
	public static final int MEMORY_SIZE = 4096;
	public static final int STACK_SIZE = 16;
	public static final int SCREEN_WIDTH = 64;
	public static final int SCREEN_HEIGHT = 32;
	public static final int SCREEN_SCALE = 10;
	
	public static short[] registers = new short[NUM_REGISTERS];
	public static short iRegister = 0;
	public static short delayRegister = 0;
	public static short soundRegister = 0;
	public static int[] stack = new int[STACK_SIZE];
	public static short[] memory = new short[MEMORY_SIZE];
	public static int pc = 0x000;
	public static int stackPointer = 0x000;
	
	public static String filePath = "C:\\Users\\Ashwin\\Downloads\\pong2.c8";
	
	//METHODS
	public static void printRegisters() {
		for (int i = 0; i < NUM_REGISTERS; i ++) {
			System.out.println("Register " + i + ": " + registers[i]);
		}
	}
	
	public static void printMemory() {
		for (int i = 0; i < MEMORY_SIZE; i ++) {
			System.out.println("Memory " + i + ": " + memory[i]);
		}
	}
	
	public static void loadFile() {
		DataInputStream inputStream = null;
		
		try {
			inputStream = new DataInputStream(new FileInputStream(new File(filePath)));
			int index = 0;
			
			while (inputStream.available() > 0) {
				int inputByte = inputStream.readByte() & 0xFF;
				memory[index] = (short) inputByte;
				System.out.println(inputStream.readByte() & 0xFF);
				index ++;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
