package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Casilla extends Actor
{
    private Color color;
    private int xBoard;
    private int yBoard;
    protected int x;
    protected int y;
    private static int size = 50;

    public Casilla() {
        setColor(false);
        setxBoard(1);
        setyBoard(1);
        this.x = xBoard + (size * xBoard);
        this.y = yBoard + (size * yBoard);

    }

    public Casilla(int x, int y, boolean color)
    {
        setColor(color);
        setxBoard(x);
        setyBoard(y);
        this.x = xBoard + (size * xBoard);
        this.y = yBoard + (size * yBoard);
        System.out.println(this.x + " " + this.y);

    }

    public void setColor(boolean color)
    {
        if(color)
            this.color = Color.WHITE;
        else
            this.color = Color.BLACK;
    }

    public void setxBoard(int xBoard)
    {
        if(xBoard >= 1 && xBoard <= 8)
            this.xBoard = xBoard;
    }

    public void setyBoard(int yBoard)
    {
        if(yBoard >= 1 && yBoard <= 8)
            this.yBoard = yBoard;
    }

    public Color getColor()
    {
        return color;
    }

    public int getxBoard()
    {
        return xBoard;
    }

    public int getyBoard()
    {
        return yBoard;
    }

    @Override
    public String toString()
    {
        String colorLetra = this.color == Color.WHITE ? "BLANCO" : "NEGRO";
        return " | " + (char) (this.xBoard + 64) + " " + this.yBoard + " " + colorLetra + " | ";
    }

    public void update()
    {
    }

    public void draw(ShapeRenderer shape)
    {
        shape.rect(x, y, size, size, color, color, color, color);
    }
}
