package main;

public class Main {
	public static String unixFilePath = "./c8games/PONG2";
	public static String windowsFilePath = "c8games//PONG2";
	
	
	public static void main(String[] args) {		
		Chip chip = new Chip(unixFilePath);
		chip.initialize();
		chip.run();
//		DrawTest dt = new DrawTest();
//		dt.draw(dt.IMAGE);
	}
	
}
