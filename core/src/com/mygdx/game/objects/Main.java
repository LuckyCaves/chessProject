package com.mygdx.game.objects;

import com.mygdx.game.objects.Tablero;

public class Main {
    public static void main(String[] args)
    {

        Tablero t = new Tablero();
//        imprimirTablero(t);

    }


    public static void imprimirTablero(Tablero t)
    {
        for (Casilla[] filas : t.getTablero())
        {
            for(Casilla c : filas)
            {
                System.out.print(c + " ");
            }
            System.out.println();
        }

    }

}