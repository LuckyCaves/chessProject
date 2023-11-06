package com.mygdx.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.pieces.Peon;
import com.mygdx.game.objects.pieces.Pieza;
import com.mygdx.game.processor.GameProcessor;

public class TestPieceMove
{

    public static void main (String[] arg)
    {

        pawnMovement();

    }

    private static void pawnMovement()
    {
        GameProcessor gameProcessor = new GameProcessor();
        Pieza peon = new Peon(Color.BLACK, 1, 7);
        Tablero tablero = new Tablero();
        tablero.agregarPieza(peon, 0, 6);

        gameProcessor.selectPiece(tablero.getCasilla(0, 6));
        gameProcessor.movePiece(tablero.getCasilla(0, 7));
        gameProcessor.selectPiece(tablero.getCasilla(0, 6));
        gameProcessor.movePiece(tablero.getCasilla(0, 5));
    }

}
