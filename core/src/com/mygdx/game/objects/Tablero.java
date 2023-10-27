package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Tablero extends Actor
{

    private final Casilla[][] tablero = new Casilla[8][8];
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
}
