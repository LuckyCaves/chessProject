package com.mygdx.game.input;

import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;
import com.mygdx.game.processor.GameProcessor;

public class MyInputAdapter extends InputAdapter
{

    private GameProcessor gameProcessor;
    private Tablero tablero;

    public MyInputAdapter(Tablero tablero)
    {
        this.tablero = tablero;
        gameProcessor = new GameProcessor();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
        {
            gameProcessor.movePiece(tablero.getTablero()[Tablero.coordsX(screenX)][Tablero.coordsY(screenY)]);
//            System.out.println(Tablero.translateCoordsX(screenX) + " " + Tablero.translateCoordsY(screenY));
        }
        System.out.println("Tocamos");
//        tablero.update();
        return false;
    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("Arrastramos");

//        if(Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
//            gameProcessor.grabPiece(tablero.getTablero()[Tablero.coordsX(screenX)][Tablero.coordsY(screenY)]);
//
//        System.out.println(Tablero.translateCoordsX(screenX) + " " + Tablero.translateCoordsY(screenY) + "Arrastrando");
//        tablero.update();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
//        if(Vector2d.isInBounds(tablero.getBordes(), screenX, screenY))
//            gameProcessor.confirmMove(tablero.getTablero()[Tablero.coordsX(screenX)][Tablero.coordsY(screenY)]);

//        System.out.println(Tablero.translateCoordsX(screenX) + " " + Tablero.translateCoordsY(screenY) + "Levantaste click");
//        tablero.update();
        return false;
    }

}
