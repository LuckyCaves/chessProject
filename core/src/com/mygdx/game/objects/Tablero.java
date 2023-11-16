package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.pieces.*;


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

    public void setPieces(Color color, Stage stage)
    {
        Pieza[] piezas = new Pieza[16];
        int posicion = color == Color.WHITE ? 2 : 7;
        int posicion2 = color == Color.WHITE ? 1 : 8;

        for(int i = 0; i < 8; i++)
        {
            piezas[i] = new Peon(color, tablero[i][posicion - 1], stage);
            agregarPieza(piezas[i], i + 1, posicion);
        }

        piezas[8] = new Torre(color, tablero[0][posicion2 - 1], stage);
        piezas[9] = new Torre(color, tablero[7][posicion2 - 1], stage);
        agregarPieza(piezas[8], 1, posicion2);
        agregarPieza(piezas[9], 8, posicion2);

        piezas[10] = new Knight(color, tablero[1][posicion2 - 1], stage);
        piezas[11] = new Knight(color, tablero[6][posicion2 - 1], stage);
        agregarPieza(piezas[10], 2, posicion2);
        agregarPieza(piezas[11], 7, posicion2);

        piezas[12] = new Bishop(color, tablero[2][posicion2 - 1], stage);
        piezas[13] = new Bishop(color, tablero[5][posicion2 - 1], stage);
        agregarPieza(piezas[12], 3, posicion2);
        agregarPieza(piezas[13], 6, posicion2);

        piezas[14] = new Queen(color, tablero[4][posicion2 - 1], stage);
        piezas[15] = new King(color, tablero[3][posicion2 - 1], stage);
        agregarPieza(piezas[14], 5, posicion2);
        agregarPieza(piezas[15], 4, posicion2);


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
        for(int i = 7; i >= 0; i--)
            for(int j = 7; j >= 0; j--)
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

        if(inicio.getPiece() instanceof Knight)
            return true;


        if(destino.hasPiece() && destino.getPiece().getColor().equals(inicio.getPiece().getColor()))
        {
            System.out.println(destino.getPiece().getColor());
            return false;
        }

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
