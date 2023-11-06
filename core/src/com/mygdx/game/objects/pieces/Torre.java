package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.objects.Vector2d;

public class Torre extends Pieza
{


    private boolean firstMove = true;

    public Torre(String imagePath, Color color, int boardX, int boardY)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(boardX * 50, (9 - boardY) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.boardX = boardX;
        super.boardY = boardY;
    }

    public Torre(Color color, int boardX, int boardY)
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
//        If para considerar si se puede enrocar
//        if(firstMove)
//        {
//            firstMove = false;
//            return true;
//        }

        if(boardX == super.boardX || boardY == super.boardY)
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
