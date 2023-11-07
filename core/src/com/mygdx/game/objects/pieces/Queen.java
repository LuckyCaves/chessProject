package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;

public class Queen extends Pieza
{

    public Queen(String imagePath, Color color, Casilla casilla)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (9 - casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Queen(Color color, Casilla casilla)
    {
        super.color = color;
        super.casilla = casilla;

    }

    @Override
    public boolean movePiece(Casilla c)
    {
//      Falta agregar la lógica del movimiento de cada pieza para poder negar, por tanto esta podría estar en Pieza
        System.out.println("La pieza esta en " + casilla.getxBoard() + " " + casilla.getyBoard());
        Tablero tablero = Tablero.getInstance();
        if(!isValidMove(c) || !tablero.isPathEmpty(casilla, c))
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

        double slope = Math.pow(Vector2d.calculateSlope(c.getxBoard(), c.getyBoard(), casilla.getxBoard(), casilla.getyBoard()), 2);


        if(c.getxBoard() == casilla.getxBoard() || c.getyBoard() == casilla.getyBoard())
        {
            return true;
        }
        else if(slope == 1)
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
