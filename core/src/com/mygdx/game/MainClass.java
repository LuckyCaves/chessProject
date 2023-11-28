package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.input.MyInputAdapter;
import com.mygdx.game.objects.*;
import com.mygdx.game.objects.pieces.*;


public class MainClass extends ApplicationAdapter {
	ShapeRenderer shape;
	Tablero tablero;
	Stage stage;
	Batch batch;
	InputAdapter inputProcessor;
	InputMultiplexer inputMultiplexer;
	Player jugadorBlanco;
	Player jugadorNegro;
	Player jugadorActual;

	@Override
	public void create () {
		stage = new Stage(new ScreenViewport());
		shape = new ShapeRenderer();
		tablero = Tablero.getInstance();
		tablero.setPieces(Color.BLACK, stage);
		tablero.setPieces(Color.WHITE, stage);
		inputProcessor = new MyInputAdapter(tablero, stage);
		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(inputProcessor);
		inputMultiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
//		Peon pieza = new Peon(Color.WHITE, new Casilla(1, 1, true), stage);
//		pieza.selectPiece(stage);


	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.25f, .25f, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		drawShapes();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

	}

	public void drawShapes()
	{
		shape.begin(ShapeRenderer.ShapeType.Filled);
		tablero.draw(shape);
		shape.end();
	}

	public void drawBatch()
	{
		batch.begin();
		batch.end();
	}

	@Override
	public void dispose()
	{
		super.dispose();
	}
}