package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Game;
import com.udacity.gamedev.gigagal.GameplayScreen;

public class GigaGalGame extends Game {
	@Override
	public void create() {
		setScreen(new GameplayScreen());
	}
}
