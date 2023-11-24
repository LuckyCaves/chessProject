package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Vector2d;

public class Queen extends Pieza
{

    public Queen(Color color, Casilla casilla, Stage stage)
    {
        String imagePath = color == Color.WHITE ? "WhiteQueen.png" : "BlackQueen.png";
        nombre = "Queen";
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        stage.addActor(this);

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
    public boolean movePiece(Casilla c, boolean notCheck)
    {
        if(super.movePiece(c, notCheck))
        {
            update(x * SIZE, y * SIZE);
            return true;
        }
//        TODO descomentar update

        return false;
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
