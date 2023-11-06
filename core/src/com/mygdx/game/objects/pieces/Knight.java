package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Vector2d;

public class Knight extends Pieza
{

    public Knight(String imagePath, Color color, int boardX, int boardY)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(boardX * 50, (9 - boardY) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.boardX = boardX;
        super.boardY = boardY;
    }

    public Knight(Color color, int boardX, int boardY)
    {
        super.color = color;
        super.boardX = boardX;
        super.boardY = boardY;
    }

    @Override
    public boolean movePiece(int boardX, int boardY)
    {
//      Falta agregar la lógica del movimiento de cada pieza para poder negar, por tanto esta podría estar en Pieza
        System.out.println("La pieza esta en " + super.boardX + " " + super.boardY);
        if(!isValidMove(boardX, boardY))
        {
            System.out.println("Movimiento ilegal");
            return false;
        }
        y = 9 - boardY;
        x = boardX;
        super.boardX = boardX;
        super.boardY = boardY;

//        TODO descomentar update
        update(x * SIZE, y * SIZE);
        System.out.println("La pieza pasa a " + super.boardX + " " + super.boardY);
        return true;

    }

    public boolean isValidMove(int boardX, int boardY)
    {

        double distanciaX = Vector2d.distance(super.boardX, super.boardY, boardX, super.boardY);
        double distanciaY = Vector2d.distance(super.boardX, super.boardY, super.boardX, boardY);

        if(distanciaX == 1 && distanciaY == 2)
        {
            return true;
        }
        else if(distanciaX == 2 && distanciaY == 1)
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
