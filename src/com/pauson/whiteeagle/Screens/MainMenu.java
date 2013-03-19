package com.pauson.whiteeagle.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.pauson.whiteeagle.WhiteEagle;

public class MainMenu implements Screen {
	
	WhiteEagle game;
	Stage stage;
	BitmapFont font_black;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton button;
    Label label;
	
	
	public MainMenu(WhiteEagle game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);

        batch.begin();
        stage.draw();
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if (stage == null)
            stage = new Stage(width, height, true);
	    stage.clear();
	
	    Gdx.input.setInputProcessor(stage);
	
	    TextButtonStyle style = new TextButtonStyle();
	    style.font = font_black;
	    
	    button = new TextButton("Play!", style);
        button.setWidth(400);
        button.setHeight(100);
        button.setX(Gdx.graphics.getWidth() / 2 - button.getWidth() / 2);
        button.setY(Gdx.graphics.getHeight() / 2 - button.getHeight() / 2);
        
        LabelStyle ls = new LabelStyle(font_black, Color.BLACK);
        label = new Label("White Eagle", ls);
        label.setX(0);
        label.setY(Gdx.graphics.getHeight() / 2 + 100);
        label.setWidth(width);
        label.setAlignment(Align.center);
        
        button.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y,
                            int pointer, int button) {
                    return true;
            }

            public void touchUp(InputEvent event, float x, float y,
                            int pointer, int button) {
                    game.setScreen(new GameScreen(game));
            }
        });
        
        stage.addActor(label);
        stage.addActor(button);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
        font_black = new BitmapFont(Gdx.files.internal("data/font_black.fnt"), false);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		font_black.dispose();
		atlas.dispose();
		skin.dispose();
		batch.dispose();
	}

}
