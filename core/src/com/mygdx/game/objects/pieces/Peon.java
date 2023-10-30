package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;

public class Peon extends Pieza
{

    public Peon(String imagePath, int x, int y)
    {
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(x, y);
        sprite.setSize(super.SIZE, super.SIZE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        isSelected = false;
    }

    @Override
    public boolean movePiece(int boardX, int boardY) {
        if(!isSelected)
        {
            isSelected = true;
            return false;
        }

        boardY = 9 - boardY;

        update(boardX * SIZE, (boardY * SIZE));

        System.out.println(boardX * SIZE + " " + ((boardY * SIZE) - 20));
        isSelected = false;
        return true;

    }

    public void update(int x, int y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
