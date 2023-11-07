package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;

public abstract class Pieza extends Actor
{

    protected Casilla casilla;
    protected int x;
    protected int y;
    protected Sprite sprite;
    protected Color color;
    public final int SIZE = 50;
    protected static Tablero tablero;

    public abstract boolean movePiece(Casilla c);
    public abstract boolean isValidMove(Casilla c);

    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }


}
