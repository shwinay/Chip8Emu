package com.ashwinkudva.chipeightemu.logic;
 
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
 
public class MenuScreen implements Screen
{
    Stage stage;
    Table table;
    TextButton playButton;
    TextButton helpButton;
    TextButton settingsButton;
    Label title;
    Label dimensions;
    Skin skin;
    BitmapFont font;
    BitmapFont scaledFont;
    TextureAtlas atlas;
    TextButtonStyle textButtonStyle;
    LabelStyle labelStyle;
    LabelStyle scaledLabelStyle;
    Vector3 touch;
    Emulator emulator;
     
    public MenuScreen(final Emulator emulator)
    {
         
        touch = new Vector3();
        this.emulator = emulator;
        stage = new Stage(new ExtendViewport(960,540));
        font = new BitmapFont(Gdx.files.internal("8bit.fnt"), false);
        scaledFont = new BitmapFont(Gdx.files.internal("8bit.fnt"), false);
        scaledFont.getData().setScale(2,2);
        atlas = new TextureAtlas("button.pack");
        skin = new Skin(atlas);
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
         
        textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button1");
        textButtonStyle.down = skin.getDrawable("button2");
        textButtonStyle.font = font;
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.fontColor = Color.BLACK;
         
        playButton = new TextButton("PLAY", textButtonStyle);
        playButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
                Memory.soundEffect.play();
                 
                stage.addAction(Actions.sequence(Actions.fadeOut((float) .75), Actions.run(new Runnable()
                {
                    @Override
                    public void run()
                    {
                    	emulator.setScreen(new GameScreen(emulator));
                    }
                })));
            }
        });
         
        helpButton = new TextButton("EXIT", textButtonStyle);
        helpButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
            	Memory.soundEffect.play();
                 
                stage.addAction(Actions.sequence(Actions.fadeOut((float) .5), Actions.run(new Runnable()
                {
                    @Override
                    public void run()
                    {
                    	System.exit(0);
                    }
                })));
            }
        });
 
        settingsButton = new TextButton("SETTINGS", textButtonStyle);
        settingsButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
            	Memory.soundEffect.play();
            	
//                stage.addAction(Actions.sequence(Actions.fadeOut((float) .50), Actions.run(new Runnable()
//                {
//                    @Override
//                    public void run()
//                    {
//                    	
//                    }
//                })));
            }
        });
         
        labelStyle = new LabelStyle(font, Color.WHITE);
        scaledLabelStyle = new LabelStyle(scaledFont, Color.WHITE);
         
        title = new Label("Chip8Emu", scaledLabelStyle);
        title.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) 
            {
                Memory.soundEffect.play();
                 
                stage.addAction(Actions.sequence(Actions.fadeOut((float) .50), Actions.run(new Runnable()
                {
                    @Override
                    public void run()
                    {
                    	
                    }
                })));
            }
        });
         
        table.align(Align.center);
         
        table.add(title);
        table.getCell(title).padBottom(50);
        table.getCell(title).align(Align.center);
         
        table.row();
        table.add(playButton);
        table.getCell(playButton).padBottom(50);
        table.getCell(playButton).size(225,75);
         
        table.row();
        table.add(settingsButton);
        table.getCell(settingsButton).padBottom(50);
        table.getCell(settingsButton).size(225,75);
 
        table.row();
        table.add(helpButton);
        table.getCell(helpButton).padBottom(50);
        table.getCell(helpButton).size(225,75);
         
        //table.debug();
        table.setFillParent(true);
        stage.addActor(table);
         
        stage.addAction(Actions.alpha(0));
        stage.addAction(Actions.fadeIn((float).50));
         
        Gdx.input.setInputProcessor(stage);
    }
 
    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act(delta);
        stage.draw();
         
         
    }
 
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
     
    @Override
    public void show() {
     
    }
 
    @Override
    public void hide() {
        dispose();
    }
 
    @Override
    public void pause() {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void resume() {
        // TODO Auto-generated method stub
         
    }
 
    @Override
    public void dispose()
    {
        stage.dispose();
    }
 
}