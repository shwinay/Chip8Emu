package main;

public class Chip {

	final private Executer executer;
	private String romFile;
	Graphics myGraphics;
	
	public Chip(String romFile, Graphics myGraphics) {
		this.romFile = romFile;
		this.myGraphics = myGraphics;
		executer = new Executer(myGraphics);
	}
	
	public boolean initialize(String unixFilePath) {
		Memory.loadFile(unixFilePath);
		myGraphics.initialize();
		return true;
	}
	
	public boolean run() {
		
		return false;
		
	}

}
