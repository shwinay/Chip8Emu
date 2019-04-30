package main;

public class Main {
	public static String unixFilePath = "./c8games/PONG2";
	public static String windowsFilePath = "c8games//PONG2";
	
	
	public static void main(String[] args) {
		
//		Chip myChip = new Chip(unixFilePath, new graph1());
//		myChip.initialize(unixFilePath);
//		//myChip.run();
//		Memory.printMemory();
//		Memory.printRegisters();
		byte a = -1;
		byte b = (byte) Decoder.extract(a, 7, 0);
		System.out.println(b);
	}
	
}
