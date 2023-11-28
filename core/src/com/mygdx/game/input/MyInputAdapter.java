package com.mygdx.game.input;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.objects.Casilla;
import com.badlogic.gdx.scenes.scene2d.*;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;
import com.mygdx.game.processor.GameProcessor;

import java.sql.SQLOutput;

public class MyInputAdapter extends InputAdapter
{

    private GameProcessor gameProcessor;
    private Tablero tablero;
    private Casilla clickedTile;

    public MyInputAdapter(Tablero tablero, Stage stage)
    {
        this.tablero = tablero;
        gameProcessor = new GameProcessor(stage);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
            return false;

        clickedTile = tablero.getCasilla(Tablero.coordsX(screenX), Tablero.coordsY(screenY));

        System.out.println(Tablero.coordsX(screenX) + " " + Tablero.coordsY(screenY));

        if(!gameProcessor.movePiece(clickedTile))
            gameProcessor.selectPiece(clickedTile);

        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        if(!Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
            return false;

        clickedTile = tablero.getCasilla( Tablero.coordsX(screenX), Tablero.coordsY(screenY));
        gameProcessor.grabPiece(clickedTile);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(!Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
            return false;

        clickedTile = tablero.getCasilla(Tablero.coordsX(screenX), Tablero.coordsY(screenY));
        gameProcessor.dropPiece(clickedTile);

        return false;
    }

}
