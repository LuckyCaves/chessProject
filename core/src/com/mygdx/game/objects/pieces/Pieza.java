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

    public Pieza()
    {
        tablero = Tablero.getInstance();
        System.out.println("ejecutado");
    }

    public boolean movePiece(Casilla c, boolean notCheck)
    {


        System.out.println("La pieza esta en " + casilla.getxBoard() + " " + casilla.getyBoard());

        if(!notCheck && (!isValidMove(c) || !tablero.isPathEmpty(casilla, c)))
        {
            System.out.println("Movimiento ilegal");
            return false;
        }

        y = c.getyBoard();
        x = c.getxBoard();
        casilla = c;
        System.out.println("La pieza pasa a " + casilla.getxBoard()+ " " + casilla.getyBoard());

        return true;
    }
    public abstract boolean isValidMove(Casilla c);

    public void deletePiece()
    {
        this.remove();
    }

    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    public Casilla getCasilla()
    {
        return this.casilla;
    }

    public Color getColor()
    {
        return this.color;
    }


}
