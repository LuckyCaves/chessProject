package com.mygdx.test;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.PGN;
import com.mygdx.game.objects.pieces.Peon;

import java.awt.*;
import java.util.Collection;

public class TestPGNCreation
{

    public static void main(String[] arg)
    {

        PGN notacion = new PGN("assets/partidaAjedrez.txt");
        notacion.writeFirstData();
        Casilla casilla = new Casilla(1, 4, true);
        Casilla casilla2 = new Casilla(3, 4, true);
        Casilla casilla3 = new Casilla(8, 4, true);
        notacion.guardarJugada(casilla, new Peon(Color.WHITE, casilla));
        notacion.guardarJugada(casilla2, new Peon(Color.BLACK, casilla2));
        notacion.guardarJugada(casilla3, new Peon(Color.WHITE, casilla3));


    }

}
