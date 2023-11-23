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
        int segundaFila = color == Color.WHITE ? 1 : 6;
        int primeraFila = color == Color.WHITE ? 0 : 7;

        for(int i = 0; i < 8; i++)
        {
            piezas[i] = new Peon(color, tablero[i][segundaFila], stage);
            agregarPieza(piezas[i], i, segundaFila);
        }

        piezas[8] = new Torre(color, tablero[0][primeraFila], stage);
        piezas[9] = new Torre(color, tablero[7][primeraFila], stage);
        agregarPieza(piezas[8], 0, primeraFila);
        agregarPieza(piezas[9], 7, primeraFila);

        piezas[10] = new Knight(color, tablero[1][primeraFila], stage);
        piezas[11] = new Knight(color, tablero[6][primeraFila], stage);
        agregarPieza(piezas[10], 1, primeraFila);
        agregarPieza(piezas[11], 6, primeraFila);

        piezas[12] = new Bishop(color, tablero[2][primeraFila], stage);
        piezas[13] = new Bishop(color, tablero[5][primeraFila], stage);
        agregarPieza(piezas[12], 2, primeraFila);
        agregarPieza(piezas[13], 5, primeraFila);

        piezas[14] = new Queen(color, tablero[3][primeraFila], stage);
        piezas[15] = new King(color, tablero[4][primeraFila], stage);
        agregarPieza(piezas[14], 3, primeraFila);
        agregarPieza(piezas[15], 4, primeraFila);


    }

    public static Tablero getInstance()
    {
        if(instance == null)
            instance = new Tablero();

        return instance;
    }

    public Casilla getCasilla(int x, int y) {
        if(x == 0)
            x = 1;
        if(x == 9)
            x = 8;
        if(y == 0)
            y = 1;
        if(y == 9)
            y = 8;

        return this.tablero[x - 1][y - 1];
    }

    public Casilla[][] getTablero()
    {
        return this.tablero;
    }

    public void agregarPieza(Pieza pieza, int boardX, int boardY)
    {
        tablero[boardX][boardY].setPiece(pieza);
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
        return (int) (8 - (y - 31) / 50);
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

        if(destino.hasPiece() && destino.getPiece().getColor().equals(inicio.getPiece().getColor()))
        {
            System.out.println(destino.getPiece().getColor());
            return false;
        }

        if(inicio.getPiece() instanceof Knight || !(destino.getPiece() instanceof King))
            return true;
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


    public Pieza lookForPiece(Casilla inicio, Casilla destino)
    {

        double slope = Vector2d.calculateSlope(inicio.getxBoard(), inicio.getyBoard(), destino.getxBoard(), destino.getyBoard());
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
        }


        while(x != destino.getxBoard() - diferenciaX|| y != destino.getyBoard() - diferenciaY)
        {
            x += diferenciaX;
            y += diferenciaY;
            System.out.println("Casilla " + (x) + " " + (y));
            if(tablero[x - 1][y - 1].hasPiece())
            {
                if(tablero[x - 1][y - 1].getPiece().getColor().equals(inicio.getPiece().getColor()))
                {
                    return null;
                }

                System.out.println("Hay una pieza en " + (x) + " " + (y));
                return tablero[x - 1][y - 1].getPiece();
            }
        }

        if(destino.hasPiece() && !destino.getPiece().getColor().equals(inicio.getPiece().getColor()))
        {
            return destino.getPiece();
        }

        return null;
    }

}
