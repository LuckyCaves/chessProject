package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Vector2d;

public class King extends Pieza
{

    private boolean firstMove = true;

    public King(Color color, Casilla casilla, Stage stage)
    {
//        super();
        String imagePath = color == Color.WHITE ? "WhiteKing.png" : "BlackKing.png";

        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        stage.addActor(this);

        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public King(Color color, Casilla casilla)
    {
        super.color = color;
        super.casilla = casilla;
    }

    @Override
    public boolean movePiece(Casilla c)
    {
        if(super.movePiece(c))
        {
            update(x * SIZE, y * SIZE);
            return true;
        }
//        TODO descomentar update

        return false;

    }

    public boolean isValidMove(Casilla c)
    {

        double distanciaX = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), casilla.getyBoard());
        double distanciaY = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), casilla.getxBoard(), c.getyBoard());

        if(distanciaX == 0 && distanciaY == 1)
        {
            firstMove = false;
            return true;
        }
        else if(distanciaY == 0 && distanciaX == 1)
        {
            firstMove = false;
            return true;
        }
        else if(distanciaX == 1 && distanciaY == 1)
        {
            firstMove = false;
            return true;
        }

        return false;
    }

    public boolean isChecked()
    {

        System.out.println("Revisamos");

        int x = casilla.getxBoard();
        int y = casilla.getyBoard();

        if(knightCheck(x + 2, y - 1))
            return true;
        if(knightCheck(x + 2, y + 1))
            return true;
        if(knightCheck(x - 2, y - 1))
            return true;
        if(knightCheck(x - 2, y + 1))
            return true;
        if(knightCheck(x + 1, y + 2))
            return true;
        if(knightCheck(x - 1, y + 2))
            return true;
        if(knightCheck(x + 1, y - 2))
            return true;
        if(knightCheck(x - 1, y - 2))
            return true;

        return false;
    }

    public boolean knightCheck(int x, int y)
    {

        if(x > 8 || x < 1)
            return false;

        if(y > 8 || y < 1)
            return false;

        if(tablero.getCasilla(x, y).hasPiece()
                && tablero.getCasilla(x, y).getPiece() instanceof Knight
                && !tablero.getCasilla(x, y).getPiece().getColor().equals(this.color))
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
