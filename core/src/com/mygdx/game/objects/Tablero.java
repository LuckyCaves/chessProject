package com.mygdx.game.objects;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.input.MyInputAdapter;
import com.mygdx.game.objects.pieces.Pieza;
import com.mygdx.game.processor.GameProcessor;


public class Tablero
{

    private final Casilla[][] tablero = new Casilla[8][8];
    private final Vector2d[] bordes = new Vector2d[2];

    public Tablero()
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

    public Casilla[][] getTablero() {
        return tablero;
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
        return (int) ((x - 51) / 50);
    }

    public static int coordsY(int y)
    {
        return (int) ((y - 31) / 50);
    }

    public static char translateBoardCoordsX(int x)
    {
        return (char) (x + 65);
    }

    public static int translateBoardCoordsY(int y)
    {
        return y + 1;
    }
}
