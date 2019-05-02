package com.ashwinkudva.chipeightemu.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {
	
	//INSTANCE VARIABLES
	public static final int DISPLAY_WIDTH = 64;
	public static final int DISPLAY_HEIGHT = 32;
	public static final int DISPLAY_SCALE = 10;
	public static final int REFRESH_RATE = 1000 / 500; //refresh rate in milliseconds - 500 Hz
	
	private Emulator emulator;
	private Decoder decoder;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Keyboard keyboard;
	
	//CONSTRUCTOR
	public GameScreen(Emulator emulator) {
		this.emulator = emulator;
		
		//set up graphics stuff
		camera = new OrthographicCamera();
		camera.setToOrtho(true, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		shapeRenderer = new ShapeRenderer();
		
		//set up emulator stuff
		decoder = new Decoder();
		keyboard = new Keyboard();
		Gdx.input.setInputProcessor(keyboard);
	}
	
	//METHODS
	@Override
	public void render(float delta) {
		//decode and execute instructions
		int shouldRun = decoder.decode(getInstruction());
//		if (shouldRun == 0) {
//			System.exit(0);
//		}
		//update graphics
		showDisplay();
	}
	
	public void showDisplay() {
		Gdx.gl.glClearColor(0, 0, 0, 1); //clear to black
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);
		for (int y = 0; y < Memory.pixels.length; y ++) {
			for (int x = 0; x < Memory.pixels[y].length; x ++) {
				if (Memory.pixels[y][x] != 0) { //should color white
					shapeRenderer.setColor(new Color(1f, 1f, 1f, 0f)); //set color to white
				}
				else {
					shapeRenderer.setColor(new Color(0f, 0f, 0f, 0f)); //set color to black
				}
				shapeRenderer.rect(x, y, 1f, 1f);
			}
		}
		shapeRenderer.end();
	}
	
	private short getInstruction() {
		short ins = (short) (((Memory.memory[Memory.pc] << 8)) | (Memory.memory[Memory.pc + 1]));
		//System.out.println("Instruction: " + String.format("0x%08X",ins));
		return ins;
	}
	
	//unused implemented methods
	@Override
	public void show() {}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}
	
}
