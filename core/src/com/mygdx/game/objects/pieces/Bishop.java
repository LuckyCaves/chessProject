package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;

public class Bishop extends Pieza
{

    public Bishop(Color color, Casilla casilla, Stage stage)
    {
        String imagePath = color == Color.WHITE ? "WhiteBishop.png" : "BlackBishop.png";

        nombre = "Bishop";

        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        stage.addActor(this);

        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Bishop(Color color, Casilla casilla)
    {
        nombre = "Bishop";
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
//        If para considerar si se puede enrocar
//        if(firstMove)
//        {
//            firstMove = false;
//            return true;
//        }

        double slope = Math.pow(Vector2d.calculateSlope(c.getxBoard(), c.getyBoard(), casilla.getxBoard(), casilla.getyBoard()), 2);

        if(slope == 1)
        {
            return true;
        }

        return false;
    }

    @Override
    public void writeMove(Casilla inicio, Casilla destino, boolean eatedPiece)
    {

        char xInicio = Tablero.translateBoardCoordsX(inicio.getxBoard());
        char xDestino = Tablero.translateBoardCoordsX(destino.getxBoard());
        int yDestino = destino.getyBoard();

        if(eatedPiece)
            this.moveDescription = xInicio + this.nombre.substring(0,1) + xInicio + "x" + xDestino + yDestino;
        else
            this.moveDescription = this.nombre.substring(0,1) +  xInicio + xDestino + String.valueOf(yDestino);

    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

}
