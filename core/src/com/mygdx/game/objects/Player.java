package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.pieces.King;
import com.mygdx.game.objects.pieces.Pieza;
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.*;

=======
>>>>>>> e24a3a0bb0d5b1bbb4dce76ca14f1e39b43775f8

public class Player
{

    private Color color;
    private Pieza king;
<<<<<<< HEAD
    private Pieza[] piezasComidas = new Pieza[16];
    private int size = 0;
    private Stage stage;
=======
>>>>>>> e24a3a0bb0d5b1bbb4dce76ca14f1e39b43775f8

    public Player(Color color, Pieza king)
    {

        this.color = color;
        this.king = king;

    }

    public Color getColor()
    {
        return color;
    }

<<<<<<< HEAD
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

=======
>>>>>>> e24a3a0bb0d5b1bbb4dce76ca14f1e39b43775f8
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
