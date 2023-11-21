package com.mygdx.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.pieces.Peon;
import com.mygdx.game.objects.pieces.Pieza;
import com.mygdx.game.objects.pieces.Queen;
import com.mygdx.game.processor.GameProcessor;

public class TestPieceMove
{

    public static void main (String[] arg)
    {

        testObstruction();

    }

    private static void pawnMovement()
    {
//        GameProcessor gameProcessor = new GameProcessor();
        Tablero tablero = Tablero.getInstance();
        Pieza peon = new Peon(Color.BLACK, tablero.getCasilla(1, 7));
        tablero.agregarPieza(peon, 0, 6);

//        gameProcessor.selectPiece(tablero.getCasilla(0, 6));
//        gameProcessor.movePiece(tablero.getCasilla(0, 7));
//        gameProcessor.selectPiece(tablero.getCasilla(0, 6));
//        gameProcessor.movePiece(tablero.getCasilla(0, 5));
    }

    private static void queenMovement()
    {
        GameProcessor gameProcessor = new GameProcessor();
        Tablero tablero = Tablero.getInstance();
        Pieza pieza = new Queen(Color.BLACK, tablero.getCasilla(1, 7));
        tablero.agregarPieza(pieza, 1, 7);

        gameProcessor.selectPiece(tablero.getCasilla(1, 7));
        gameProcessor.movePiece(tablero.getCasilla(4, 4));
    }

    private static void testObstruction()
    {
        GameProcessor gameProcessor = new GameProcessor();
        Tablero tablero = Tablero.getInstance();
        Pieza pieza = new Queen(Color.BLACK, tablero.getCasilla(1, 7));
//        Pieza pieza2 = new Peon(Color.BLACK, tablero.getCasilla(2, 6));
        tablero.agregarPieza(pieza, 1, 7);
//        tablero.agregarPieza(pieza2, 2, 6);
//        tablero.setPieces(Color.BLACK, stage);
        gameProcessor.selectPiece(tablero.getCasilla(1, 7));
        gameProcessor.movePiece(tablero.getCasilla(4, 4));

//        if(tablero.isPathEmpty(tablero.getCasilla(1, 7), tablero.getCasilla(2, 6)))
//            System.out.println("Esta libre el camino");
//        else
//            System.out.println("No esta libre el camino");
    }

}
