package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.input.MyInputProcessor;
import com.mygdx.game.objects.*;
import com.mygdx.game.tiledmapstest.TableroTiled;

import java.util.EventListener;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape;
	TableroTiled tablero;
//	Tablero tablero;
//	MyInputProcessor inputProcessor;

//	@Override
//	public void create () {
//		shape = new ShapeRenderer();
//		tablero = new Tablero();
//		inputProcessor = new MyInputProcessor();
//		Gdx.input.setInputProcessor(inputProcessor);
//	}

//	@Override
//	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		shape.begin(ShapeRenderer.ShapeType.Filled);
//		tablero.draw(shape);
//		shape.end();
//	}

	@Override
	public void create()
	{
		tablero = new TableroTiled();
		System.out.println(tablero.getProperties());
	}

	@Override
	public void render()
	{
	}
}