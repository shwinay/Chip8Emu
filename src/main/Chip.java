package main;

public class Chip {

	final private Decoder decoder;
	private String romFile;
	Graphics myGraphics;
	
	public Chip(String romFile, Graphics myGraphics) {
		this.romFile = romFile;
		this.myGraphics = myGraphics;
		decoder = new Decoder(myGraphics);
	}
	
	public boolean initialize(String unixFilePath) {
		Memory.pc=512;
		Memory.loadFile(unixFilePath);
		myGraphics.initialize();
		return true;
	}
	
	public boolean run() {
		int isRunning = decoder.decode(getInstruction());
		while (isRunning!=0) {
			isRunning = decoder.decode(getInstruction());
		}
		return true;
		
	}
	
	private short getInstruction() {

		System.out.println(String.format("0x%08X",(short) (((Memory.memory[Memory.pc]<<8)) | (Memory.memory[Memory.pc+1])&0xFF)));
		
		return (short) (((Memory.memory[Memory.pc]<<8)) | (Memory.memory[Memory.pc+1])&0xFF);
		
	}

}
