package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;
import com.mygdx.game.objects.pieces.Pieza;

public class Casilla extends Actor
{
    private Color color;
    private int xBoard;
    private int yBoard;
    protected int x;
    protected int y;
    private static int size = 50;
    private Pieza pieza = null;

    public Casilla() {
        setColor(false);
        setxBoard(0);
        setyBoard(0);
        this.x = xBoard + (size * xBoard);
        this.y = yBoard + (size * yBoard);

    }

    public Casilla(int x, int y, boolean color)
    {
        setColor(color);
        setxBoard(x);
        setyBoard(y);
        this.x = (size * xBoard);
        this.y = (size * yBoard);

    }

    public void setColor(boolean color)
    {
        if(color)
            this.color = Color.LIGHT_GRAY;
        else
            this.color = Color.NAVY;
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
//        String colorLetra = this.color == Color.WHITE ? "BLANCO" : "NEGRO";
//        return " | " + (char) (this.xBoard + 64) + " " + this.yBoard + " " + colorLetra + " | " ;

        return " | " + (char) (this.xBoard + 64) + " " + this.yBoard + " " + this.hasPiece() + " | ";
    }

    public void setPiece(Pieza pieza)
    {
        this.pieza = pieza;
    }

    public void removePiece()
    {
        this.pieza = null;
    }

    public boolean hasPiece()
    {
        return this.pieza != null;
    }

    public Pieza getPiece()
    {
        return pieza;
    }



    public void update()
    {
    }

    public void draw(ShapeRenderer shape)
    {
        shape.rect(x, y, size, size, color, color, color, color);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Casilla))
            return false;

        Casilla c = (Casilla) o;

        return c.getyBoard() == this.yBoard && c.getxBoard() == this.xBoard;
    }

    private boolean checkNullTile()
    {
        return this.xBoard == 0 && this.yBoard == 0;
    }
}
