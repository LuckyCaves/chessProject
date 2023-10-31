package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Pieza extends Actor
{

    protected int boardX;
    protected int boardY;
    protected int x;
    protected int y;
    protected boolean isSelected;
    protected Sprite sprite;
    protected Color color;
    public final int SIZE = 50;

    public abstract boolean movePiece(int boardX, int boardY);

    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    public void setIsSelected(boolean b)
    {
        isSelected = b;
    }

    public void select()
    {
        isSelected = true;
    }


}
