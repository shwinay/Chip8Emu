package main;

public class Main {
	public static String unixFilePath = "./c8games/PONG2";
	
	public static void main(String[] args) {
		Memory.loadFile(unixFilePath);
		Memory.printMemory();
	}
	
}
