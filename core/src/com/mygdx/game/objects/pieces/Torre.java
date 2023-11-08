package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;

public class Torre extends Pieza
{


    private boolean firstMove = true;

    public Torre(String imagePath, Color color, Casilla casilla)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (9 - casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Torre(Color color, Casilla casilla)
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
//        If para considerar si se puede enrocar
//        if(firstMove)
//        {
//            firstMove = false;
//            return true;
//        }

        if(c.getxBoard() == casilla.getxBoard() || c.getyBoard() == casilla.getyBoard())
        {
            firstMove = false;
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
