package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;

public class Peon extends Pieza
{

    public Peon(String imagePath, int x, int y, int boardX, int boardY)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(x, y);
        sprite.setSize(super.SIZE, super.SIZE);
        update(sprite.getX(), sprite.getY());
        super.boardX = boardX;
        super.boardY = boardY;
    }

    @Override
    public boolean movePiece(int boardX, int boardY)
    {
//      Falta agregar la lógica del movimiento de cada pieza para poder negar, por tanto esta podría estar en Pieza
        super.boardX = boardX;
        super.boardY = boardY;
        boardY = 9 - boardY;
        update(boardX * SIZE, (boardY * SIZE));
        System.out.println("La pieza pasa a " + super.boardX + " " + super.boardY);
        return true;

    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
