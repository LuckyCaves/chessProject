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
    private boolean castles = false;
    private Casilla c = null;


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

        double distanciaX = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), casilla.getyBoard());
        double distanciaY = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), casilla.getxBoard(), c.getyBoard());

        if(firstMove && ((distanciaX == 2 && distanciaY == 0) || (c.hasPiece() && c.getPiece() instanceof Torre)))
        {
            this.c = casilla;

            castles = true;
            return true;
        }

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

    public boolean castle()
    {

        double distanciaX = Vector2d.distance(casilla.getxBoard(), casilla.getyBoard(), c.getxBoard(), casilla.getyBoard());
        Pieza p = null;
        Casilla c1 = null;

        if(distanciaX == 2)
        {
            if(this.getCasilla().getxBoard() > c.getxBoard())
            {
                p = tablero.getCasilla(8, this.getCasilla().getyBoard()).getPiece();
            }
            else
            {
                p = tablero.getCasilla(1, this.getCasilla().getyBoard()).getPiece();
            }
        }

        if(p == null)
            return false;

        if(p.getCasilla().getxBoard() > this.getCasilla().getxBoard())
        {
            c1 = tablero.getCasilla(p.getCasilla().getxBoard() - 2, p.getCasilla().getyBoard());

        }
        else if(p.getCasilla().getxBoard() < this.getCasilla().getxBoard())
        {
            c1 = tablero.getCasilla(p.getCasilla().getxBoard() + 3, p.getCasilla().getyBoard());
        }

        if(c1 == null)
            return false;

        p.movePiece(c1, true);
        c1.setPiece(p);
        c.removePiece();

        castles = false;
        firstMove = false;

        return true;
    }

    public boolean isChecked()
    {

        System.out.println("Revisamos");

        int x = casilla.getxBoard();
        int y = casilla.getyBoard();

        if(diagonalCheck(x ,y, 1, 1))
            return true;
        if(diagonalCheck(x ,y, -1, 1))
            return true;
        if(diagonalCheck(x ,y, -1, -1))
            return true;
        if(diagonalCheck(x ,y, 1, -1))
            return true;

        if(diagonalCheck(x ,y, 1, 0))
            return true;
        if(diagonalCheck(x ,y, -1, 0))
            return true;
        if(diagonalCheck(x ,y, 0, 1))
            return true;
        if(diagonalCheck(x ,y, 0, -1))
            return true;

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

        if(castles)
            castle();

        return false;
    }
    public boolean diagonalCheck(int x, int y, int difx, int dify)
    {
        Casilla inicio = tablero.getCasilla(x, y);
        Casilla destino = null;


        do
        {
            x += difx;
            y += dify;
            destino = tablero.getCasilla(x, y);
        }while((x >= 1 && x <= 8) && (y >= 1 && y <= 8) && !destino.hasPiece());

        Pieza p = tablero.lookForPiece(inicio, destino);

        if(p == null)
            return false;

        double slope = Math.pow(Vector2d.calculateSlope(inicio.getxBoard(), inicio.getyBoard(), destino.getxBoard(), destino.getyBoard()), 2);


        if(!(p instanceof Bishop) && !(p instanceof Queen))
            return false;
        else if(!(p instanceof Torre) && !(p instanceof Queen) && slope != 1)
            return false;


        return true;
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

    public Casilla castleTile(Casilla c)
    {

        if(this.getCasilla().getxBoard() > c.getxBoard())
        {
            c = tablero.getCasilla(this.getCasilla().getxBoard() - 2, this.getCasilla().getyBoard());

        }
        else
        {
            c = tablero.getCasilla(this.getCasilla().getxBoard() + 2, this.getCasilla().getyBoard());
        }

        return c;

    }

    public void update(float x, float y)
    {
        sprite.setPosition(x, y);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public boolean getCastles()
    {
        return this.castles;
    }

}
