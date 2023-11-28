package com.mygdx.game.objects.pieces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.Vector2d;

public class Peon extends Pieza
{

    private boolean firstMove = true;
    private String newPiece;

    public Peon(Color color, Casilla casilla, Stage stage)
    {
        String imagePath = color == Color.WHITE ? "WhitePawn.png" : "BlackPawn.png";
        nombre = "Pawn";
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
        nombre = "Pawn";
        super.color = color;
        super.casilla = casilla;
    }

    @Override
    public boolean movePiece(Casilla c, boolean notCheck)
    {
        int limit = this.color.equals(Color.WHITE) ? 8 : 1;

        if(!transform && limit == c.getyBoard() && isAble(c))
        {
            transform = true;
            return false;
        }

        if(!super.movePiece(c, notCheck))
            return false;

        update(x * SIZE, y * SIZE);
        return true;
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

        if(distancia == 1 && c.getxBoard() == casilla.getxBoard() && !c.hasPiece())
        {
            firstMove = false;
            return true;
        }

        if(slope == 1 && distancia > 1 && distancia < 2 && c.hasPiece())
        {
            firstMove = false;
            return true;
        }

        return false;
    }

    public void writeMove(Casilla inicio, Casilla destino, boolean eatedPiece)
    {
        char xInicio = Tablero.translateBoardCoordsX(inicio.getxBoard());
        int yInicio = inicio.getyBoard();
        char xDestino = Tablero.translateBoardCoordsX(destino.getxBoard());
        int yDestino = destino.getyBoard();

        if(eatedPiece)
            this.moveDescription = xInicio + "x" + xDestino + yDestino;
        else
            this.moveDescription = xDestino + String.valueOf(yDestino);

        if(transform)
            this.moveDescription += "=" + newPiece;
    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void morph(String pieza, Stage stage)
    {
        Pieza p = null;

        switch(pieza)
        {
            case "Knight":
                p = new Knight(this.color, this.casilla, stage);
                break;
            case "Queen":
                p = new Queen(this.color, this.casilla, stage);
                break;
            case "Bishop":
                p = new Bishop(this.color, this.casilla, stage);
                break;
            case "Rook":
                p = new Torre(this.color, this.casilla, stage);
                break;
        }
        deletePiece();
    }

    public void setNewPiece(String s)
    {
        newPiece = s.substring(0,1);
    }
}
