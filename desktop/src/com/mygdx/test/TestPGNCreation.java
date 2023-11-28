package com.mygdx.test;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.PGN;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.pieces.Bishop;
import com.mygdx.game.objects.pieces.King;
import com.mygdx.game.objects.pieces.Peon;
import com.mygdx.game.objects.pieces.Pieza;

import java.awt.*;
import java.util.Collection;

public class TestPGNCreation
{

    public static void main(String[] arg)
    {
        Pieza reyBlanco = new King(Color.WHITE, new Casilla(5, 1, true));
        Pieza reyNegro = new King(Color.BLACK, new Casilla(5, 8, true));

        Player player1 = new Player(Color.WHITE, reyBlanco);
        Player player2 = new Player(Color.BLACK, reyNegro);

        PGN notacion = new PGN("assets/partidaAjedrez.txt", player1, player2);
        notacion.writeFirstData();
        Pieza pieza = new Bishop(Color.WHITE, new Casilla(1, 2, true));
        Pieza pieza2 = new Peon(Color.BLACK, new Casilla(2, 7, true));
        Casilla casilla = new Casilla(3, 4, true);
        Casilla casilla2 = new Casilla(2, 5, true);
        Casilla casilla3 = new Casilla(2, 5, true);
        pieza.movePiece(casilla, false);
        notacion.guardarJugada(pieza);
        pieza2.movePiece(casilla2, false);
        notacion.guardarJugada(pieza2);
        casilla3.setPiece(pieza2);
        pieza.movePiece(casilla3, true);
        notacion.guardarJugada(pieza);


    }

}
