package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.input.MyInputAdapter;
import com.mygdx.game.objects.*;

public class MainClass extends Game {
	ShapeRenderer shape;
	Tablero tablero;
	Stage stage;
	InputAdapter inputProcessor;
	InputMultiplexer inputMultiplexer;

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

	@Override
	public void dispose()
	{
		super.dispose();
	}
}