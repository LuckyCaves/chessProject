package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;

public class Knight extends Pieza
{

    public Knight(Color color, Casilla casilla, Stage stage)
    {

        String imagePath = color == Color.WHITE ? "WhiteKnight.png" : "BlackKnight.png";
        nombre = "Night";
        sprite = new Sprite(new Texture(imagePath));
        sprite.setPosition(casilla.getxBoard() * 50, (casilla.getyBoard()) * 50 );
        sprite.setSize(super.SIZE, super.SIZE);

        stage.addActor(this);

        update(sprite.getX(), sprite.getY());
        super.color = color;
        super.casilla = casilla;
    }

    public Knight(Color color, Casilla casilla)
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

        return false;

    }

    public boolean isValidMove(Casilla c)
    {

        double distanciaX = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), casilla.getyBoard());
        double distanciaY = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), casilla.getxBoard(), c.getyBoard());

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

    @Override
    public void writeMove(Casilla inicio, Casilla destino, boolean eatedPiece)
    {

        char xInicio = Tablero.translateBoardCoordsX(inicio.getxBoard());
        int yInicio = inicio.getyBoard();
        char xDestino = Tablero.translateBoardCoordsX(destino.getxBoard());
        int yDestino = destino.getyBoard();

        if(eatedPiece)
            this.moveDescription = this.nombre.substring(0,1) + "x" + xDestino + yDestino;
        else
            this.moveDescription = this.nombre.substring(0,1) + xDestino + String.valueOf(yDestino);

    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

}
