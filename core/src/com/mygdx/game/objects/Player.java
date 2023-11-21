package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.pieces.King;
import com.mygdx.game.objects.pieces.Pieza;

public class Player
{

    private Color color;
    private Pieza king;

    public Player(Color color, Pieza king)
    {

        this.color = color;
        this.king = king;

    }

    public Color getColor()
    {
        return color;
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
