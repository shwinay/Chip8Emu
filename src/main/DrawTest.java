package main;

public class DrawTest {

	public int[] IMAGE = {0xF0, 0x90, 0x90, 0xF0};
	
	public void draw(int[] image) {
		for (int i = 0; i < image.length; i ++) {
			int row = image[i];
			for (int j = 7; j >= 0; j --) {
				int bit = Decoder.extract(row, j, j);
				if (bit == 1) {
					System.out.print("X");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
	
}
