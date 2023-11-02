package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.game.objects.Vector2d;

public class Peon extends Pieza
{

    private boolean firstMove = true;

    public Peon(String imagePath, Color color, int x, int y, int boardX, int boardY)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(x, y);
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.boardX = boardX;
        super.boardY = boardY;
    }

    public Peon(Color color, int x, int y, int boardX, int boardY)
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
//        update(x * SIZE, y * SIZE);
        System.out.println("La pieza pasa a " + super.boardX + " " + super.boardY);
        return true;

    }

    public boolean isValidMove(int boardX, int boardY)
    {
        int moveDirection = color == Color.WHITE ? 1 : -1;
        double distancia = Vector2d.distance(super.boardX, super.boardY, boardX, boardY);
        System.out.println("La distancia entre dos puntos es " + distancia);

        if((boardY * moveDirection) < (super.boardY * moveDirection))
            return false;
        else if(firstMove && distancia == 2)
        {
            firstMove = false;
            return true;
        }
        else if(distancia == 1)
            return true;

        return false;
    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
