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
    protected boolean transform = false;
    String nombre;
    protected int x;
    protected int y;
    protected Sprite sprite;
    protected Color color;
    public final int SIZE = 50;
    protected static Tablero tablero;
    protected String moveDescription;

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

        boolean isEatedPiece = c.hasPiece();

        this.writeMove(casilla, c, isEatedPiece);
        y = c.getyBoard();
        x = c.getxBoard();
        casilla = c;

        return true;
    }

    public boolean isAble(Casilla c)
    {
        if(isValidMove(c) && tablero.isPathEmpty(casilla, c))
        {
            return true;
        }

        return false;
    }
    public abstract boolean isValidMove(Casilla c);

    public abstract void writeMove(Casilla inicio, Casilla destino, boolean eatedPiece);

    public String getMoveDescription()
    {
        return this.moveDescription;
    }

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

    public String getNombre()
    {
        return this.nombre;
    }

    public boolean getTransform()
    {
        return transform;
    }

    public void setTransform(boolean b) {
        transform = b;
    }
}
