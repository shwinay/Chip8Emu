package com.ashwinkudva.chipeightemu.logic;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.badlogic.gdx.Input;

public class Memory {
	
	//INSTANCE VARIABLES
	public static final int NUM_REGISTERS = 16;
	public static final int MEMORY_SIZE = 4096; // In bytes
	public static final int STACK_SIZE = 16;
	public static final int SCREEN_WIDTH = 64;
	public static final int SCREEN_HEIGHT = 32;
	public static final int SCREEN_SCALE = 10;
	public static final int NUM_KEYS = 16;
	
	public static short[] registers = new short[NUM_REGISTERS];
	public static int iRegister = 0x000;
	public static short delayRegister = 0;
	public static short soundRegister = 0;
	public static int[] stack = new int[STACK_SIZE];
	public static short[] memory = new short[MEMORY_SIZE];
	public static int pc = 0x000;
	public static int stackPointer = 0x000;
	
	public static int keyPressed = -1;
	public static boolean ldVxkStart = false;
	
	public static boolean[] keys = new boolean[NUM_KEYS];
	public static int[][] pixels = new int[GameScreen.DISPLAY_HEIGHT][GameScreen.DISPLAY_WIDTH];
	
	// This is for Unix systems
	public static String unixFontFilePath = "./font.hex";
	
	
	//METHODS
	public static void printRegisters() {
		for (int i = 0; i < NUM_REGISTERS; i ++) {
			System.out.println("Register " + i + ": " + registers[i]);
		}
	}
	
	public static void printMemory() {
		for (int i = 0; i < MEMORY_SIZE; i ++) {
			System.out.println("Memory " + i + ": " + String.format("0x%02X", memory[i]));
		}
	}
	
	public static void loadFonts() {
		try(DataInputStream inputStream = new DataInputStream(new FileInputStream(new File(unixFontFilePath)))) {
			int index = 0;
			while (inputStream.available() > 0) {
				byte inputByte = inputStream.readByte();
				memory[index] = inputByte;
				index++;
			}
		} catch (IOException e) {
			System.err.println("Error reading ROM file ");
			e.printStackTrace();
		};
	}
	
	public static void loadFile(String romFile){
		loadFonts();
		try(DataInputStream inputStream = new DataInputStream(new FileInputStream(new File(romFile)))) {
			int index = 512;
			while (inputStream.available() > 0) {
				byte inputByte = inputStream.readByte();
				memory[index] = (short) (inputByte & 0xFF);
				index++;
			}
		} catch (IOException e) {
			System.err.println("Error reading ROM file");
			e.printStackTrace();
		};
	}
}
