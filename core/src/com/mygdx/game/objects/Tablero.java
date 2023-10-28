package com.mygdx.game.objects;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


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

        this.bordes[0] = new Vector2d(this.tablero[0][0].x, this.tablero[0][0].y);
        this.bordes[1] = new Vector2d(this.tablero[7][7].x, this.tablero[7][7].y);

    }

    public Casilla[][] getTablero() {
        return tablero;
    }
    public void update()
    {
    }

    public void draw(ShapeRenderer shape)
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                tablero[i][j].draw(shape);
    }

    public static int translateCoordsX(int x)
    {
        return (x - 50) / 50;
    }

    public static int translateCoordsY(int y)
    {
        return (y - 20) / 50;
    }
}
