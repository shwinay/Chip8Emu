package main;

public class Chip {

	final private Decoder decoder;
	private String romFile;
	
	public Chip(String romFile) {
		this.romFile = romFile;
		decoder = new Decoder();
	}
	
	public boolean initialize() {
		Memory.pc = 512;
		Memory.loadFile(romFile);
		return true;
	}
	
	public boolean run() {
		int isRunning = decoder.decode(getInstruction());
		while (isRunning != 0) {
			isRunning = decoder.decode(getInstruction());
		}
		System.out.println("stopped.. pc: " + Memory.pc);
		return true;
		
	}
	
	private short getInstruction() {
		short ins = (short) (((Memory.memory[Memory.pc] << 8)) | (Memory.memory[Memory.pc+1]));
		System.out.println("Instruction: " + String.format("0x%08X",ins));
		return ins;
	}

}
