package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.input.MyInputProcessor;
import com.mygdx.game.objects.Tablero;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape;
	Tablero tablero;
	MyInputProcessor inputProcessor;

	@Override
	public void create () {
		shape = new ShapeRenderer();
		tablero = new Tablero();


	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		tablero.draw(shape);
		shape.end();
	}
}