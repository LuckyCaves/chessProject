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

    public boolean movePiece(Casilla c)
    {
        System.out.println("La pieza esta en " + casilla.getxBoard() + " " + casilla.getyBoard());

        Tablero tablero = Tablero.getInstance();
        if(!isValidMove(c) || !tablero.isPathEmpty(casilla, c))
        {
            System.out.println("Movimiento ilegal");
            return false;
        }

        y = 9 - c.getyBoard();
        x = c.getxBoard();
        casilla = c;
        System.out.println("La pieza pasa a " + casilla.getxBoard()+ " " + casilla.getyBoard());

        return true;
    }
    public abstract boolean isValidMove(Casilla c);

    public void deletePiece()
    {
        sprite = null;
        this.remove();
        casilla = null;
        tablero = null;
    }

    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    public Color getColor()
    {
        return this.color;
    }


}
