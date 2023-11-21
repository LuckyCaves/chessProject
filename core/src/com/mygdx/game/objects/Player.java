package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.pieces.King;
import com.mygdx.game.objects.pieces.Pieza;
import com.badlogic.gdx.scenes.scene2d.*;

public class Player
{

    private Color color;
    private Pieza king;
    private Pieza[] piezasComidas = new Pieza[16];
    private int size = 0;
    private Stage stage;

    public Player(Color color, Pieza king)
    {

        this.color = color;
        this.king = king;

    }

    public Color getColor()
    {
        return color;
    }

    public void addPiece(Pieza p)
    {
        piezasComidas[size] = p;
        this.stage = p.getStage();
        size++;
    }

    public Pieza removePiece()
    {
        if(size == 0)
            return null;

        size--;
        Pieza aux = piezasComidas[size];
        piezasComidas[size] = null;
        stage.addActor(aux);

        return aux;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof  Player))
            return false;

        Player p = (Player) o;

        if(p.getColor() != this.color)
            return false;

        return true;
    }

    public King getKing()
    {
        return (King) this.king;
    }


}
