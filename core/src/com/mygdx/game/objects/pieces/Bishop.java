package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Vector2d;

public class Bishop extends Pieza
{

    public Bishop(String imagePath, Color color, Casilla casilla)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (9 - casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Bishop(Color color, Casilla casilla)
    {
        super.color = color;
        super.casilla = casilla;
    }

    @Override
    public boolean movePiece(Casilla c)
    {
        System.out.println("La pieza esta en " + casilla.getxBoard() + " " + casilla.getyBoard());
        if(!isValidMove(c))
        {
            System.out.println("Movimiento ilegal");
            return false;
        }
        y = 9 - c.getyBoard();
        x = c.getxBoard();
        super.casilla = c;

//        TODO descomentar update
//        update(x * SIZE, y * SIZE);
        System.out.println("La pieza pasa a " + casilla.getxBoard()+ " " + casilla.getyBoard());
        return true;

    }

    public boolean isValidMove(Casilla c)
    {
//        If para considerar si se puede enrocar
//        if(firstMove)
//        {
//            firstMove = false;
//            return true;
//        }

        double slope = Math.pow(Vector2d.calculateSlope(c.getxBoard(), c.getyBoard(), casilla.getxBoard(), casilla.getyBoard()), 2);

        if(slope == 1)
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
