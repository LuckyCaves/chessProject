package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Vector2d;

public class Peon extends Pieza
{

    private boolean firstMove = true;

    public Peon(String imagePath, Color color, Casilla casilla)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (9 - casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Peon(Color color, Casilla casilla)
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
        int moveDirection = color == Color.WHITE ? 1 : -1;
        double distancia = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), c.getyBoard());

        System.out.println("La distancia entre dos puntos es " + distancia);

        if((c.getyBoard() * moveDirection) < (casilla.getyBoard() * moveDirection))
        {
            return false;
        }

        if(firstMove && distancia == 2)
        {
            firstMove = false;
            return true;
        }

        if(distancia == 1 && c.getxBoard() == casilla.getxBoard())
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
