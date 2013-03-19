package com.pauson.whiteeagle;

import com.badlogic.gdx.Game;
import com.pauson.whiteeagle.Screens.GameScreen;
import com.pauson.whiteeagle.Screens.SplashScreen;

public class WhiteEagle extends Game {
	
	public static final String VERSION = "0.0.01 Pre-Alpha";
	public static final String LOG = "White Eagle";
	
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
