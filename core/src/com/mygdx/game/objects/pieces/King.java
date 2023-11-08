package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;

public class King extends Pieza
{

    public King(String imagePath, Color color, Casilla casilla)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (9 - casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public King(Color color, Casilla casilla)
    {
        super.color = color;
        super.casilla = casilla;
    }

    @Override
    public boolean movePiece(Casilla c)
    {
        if(super.movePiece(c))
        {
            update(x * SIZE, y * SIZE);
            return true;
        }
//        TODO descomentar update

        return false;

    }

    public boolean isValidMove(Casilla c)
    {

        double distanciaX = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), casilla.getyBoard());
        double distanciaY = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), casilla.getxBoard(), c.getyBoard());


        if(distanciaX == 0 && distanciaY == 1)
        {
            return true;
        }
        else if(distanciaY == 0 && distanciaX == 1)
        {
            return true;
        }
        else if(distanciaX == 1 && distanciaY == 1)
        {
            return true;
        }

        return false;
    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

}
