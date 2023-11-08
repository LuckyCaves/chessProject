package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.objects.pieces.Pieza;


public class Tablero
{

    private static Tablero instance = null;
    private final Casilla[][] tablero = new Casilla[8][8];
    private final Vector2d[] bordes = new Vector2d[2];

    private Tablero()
    {

        boolean color = false;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                this.tablero[i][j] = new Casilla(i + 1, j + 1, color);
                color = !color;
            }
            color = !color;
        }

        this.bordes[0] = new Vector2d(this.tablero[0][0].x, this.tablero[0][0].y - 20);
        this.bordes[1] = new Vector2d(this.tablero[7][7].x + 50, this.tablero[7][7].y + 30);
    }

    public static Tablero getInstance()
    {
        if(instance == null)
            instance = new Tablero();

        return instance;
    }

    public Casilla getCasilla(int x, int y) {
        return this.tablero[x - 1][y - 1];
    }

    public Casilla[][] getTablero()
    {
        return this.tablero;
    }

    public void agregarPieza(Pieza pieza, int boardX, int boardY)
    {
        tablero[boardX - 1][boardY - 1].setPiece(pieza);
    }

    public Vector2d[] getBordes()
    {
        return bordes;
    }

    public void update()
    {

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                System.out.print(tablero[i][j].toString());
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");

    }

    public void draw(ShapeRenderer shape)
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                tablero[i][j].draw(shape);
    }

    public static char translateCoordsX(int x)
    {
        return translateBoardCoordsX((int) ((x - 51) / 50));
    }

    public static int translateCoordsY(int y)
    {
        return translateBoardCoordsY((int) ((y - 31) / 50));
    }

    public static int coordsX(int x)
    {
        return (int) ((x - 51) / 50) + 1;
    }

    public static int coordsY(int y)
    {
        return (int) ((y - 31) / 50) + 1;
    }

    public static char translateBoardCoordsX(int x)
    {
        return (char) (x + 65);
    }

    public static int translateBoardCoordsY(int y)
    {
        return y + 1;
    }

    public boolean isPathEmpty(Casilla inicio, Casilla destino)
    {
        System.out.println("Revisar Camino");
        double slope = Vector2d.calculateSlope(inicio.getxBoard(), inicio.getyBoard(), destino.getxBoard(), destino.getyBoard());
        System.out.println("Pendiente: " + slope);
        int x = inicio.getxBoard();
        int y = inicio.getyBoard();

        int diferenciaX = 0;
        int diferenciaY = 0;

        if(inicio.getyBoard() == destino.getyBoard())
        {
            diferenciaX = inicio.getxBoard() < destino.getxBoard() ? 1 : -1;
        }
        else if(inicio.getxBoard() == destino.getxBoard())
        {
            diferenciaY = inicio.getyBoard() < destino.getyBoard() ? 1 : -1;
        }
        else if(slope == 1 || slope == -1)
        {
            diferenciaX = inicio.getxBoard() < destino.getxBoard() ? 1 : -1;
            diferenciaY = inicio.getyBoard() > destino.getyBoard() ? -1 : 1;
//            diferenciaY = (int) slope;
        }


        while(x != destino.getxBoard() - diferenciaX|| y != destino.getyBoard() - diferenciaY)
        {
            x += diferenciaX;
            y += diferenciaY;
            System.out.println("Casilla " + (x) + " " + (y));
            if(tablero[x - 1][y - 1].hasPiece())
            {
                System.out.println("Hay una pieza en " + (x) + " " + (y));
                return false;
            }
        }

        return true;
    }
}
