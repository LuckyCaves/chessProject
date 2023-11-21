package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Vector2d;

public class Peon extends Pieza
{

    private boolean firstMove = true;

    public Peon(Color color, Casilla casilla, Stage stage)
    {
        String imagePath = color == Color.WHITE ? "WhitePawn.png" : "BlackPawn.png";

        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;

        stage.addActor(this);
    }

    public Peon(Color color, Casilla casilla)
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
        int moveDirection = color == Color.WHITE ? 1 : -1;
        double distancia = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), c.getyBoard());
        double slope = Math.pow(Vector2d.calculateSlope(c.getxBoard(), c.getyBoard(), casilla.getxBoard(), casilla.getyBoard()), 2);

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

        if(slope == 1 && distancia < 2 && c.hasPiece())
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
