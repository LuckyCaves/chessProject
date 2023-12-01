package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;

public class Torre extends Pieza
{


    private boolean firstMove = true;

    public Torre(Color color, Casilla casilla, Stage stage)
    {
        String imagePath = color == Color.WHITE ? "WhiteRook.png" : "BlackRook.png";
        nombre = "Rook";
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        stage.addActor(this);

        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Torre(Color color, Casilla casilla)
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
        if(c.getxBoard() == casilla.getxBoard() || c.getyBoard() == casilla.getyBoard())
        {
            firstMove = false;
            return true;
        }

        return false;
    }

    @Override
    public void writeMove(Casilla inicio, Casilla destino, boolean eatedPiece)
    {

        char xInicio = Tablero.translateBoardCoordsX(inicio.getxBoard());
        int yInicio = inicio.getyBoard();
        char xDestino = Tablero.translateBoardCoordsX(destino.getxBoard());
        int yDestino = destino.getyBoard();

        if(eatedPiece)
            this.moveDescription = this.nombre.substring(0,1) + xInicio + "x" + xDestino + yDestino;
        else
            this.moveDescription = this.nombre.substring(0,1) + xInicio + xDestino + String.valueOf(yDestino);

    }

    public boolean isFirstMove()
    {
        return firstMove;
    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }
}
