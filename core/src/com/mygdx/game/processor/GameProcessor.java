package com.mygdx.game.processor;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.pieces.King;
import com.mygdx.game.objects.pieces.Pieza;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private Casilla casillaSelected = null;
    private Player jugador;

    private Player jugadorBlanco;
    private Player jugadorNegro;


    private final Tablero tablero = Tablero.getInstance();


    public GameProcessor()
    {
        jugadorBlanco = new Player(Color.WHITE, tablero.getCasilla(5, 1).getPiece());
        jugadorNegro = new Player(Color.BLACK, tablero.getCasilla(5, 8).getPiece());
        this.jugador = jugadorBlanco;
        cleanCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {

        if(!isSelected && !isGrabbed)
            return false;

        if(casillaSelected.equals(c))
        {
            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(!casillaSelected.getPiece().movePiece(c, false))
        {
            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(c.hasPiece())
        {
            jugador.addPiece(c.getPiece());
            c.deletePiece();
        }

        c.setPiece(casillaSelected.getPiece());
        casillaSelected.unSelectTile();
        casillaSelected.removePiece();

        isSelected = false;
        isGrabbed = false;

        if(jugador.getKing().isChecked())
        {
            undoMove(c);
            cleanCasillaSelected();
            return false;
        }

        this.jugador = this.jugador == jugadorBlanco ? jugadorNegro : jugadorBlanco;
        cleanCasillaSelected();

        return true;
    }

    public void undoMove(Casilla c)
    {
        System.out.println("regresamos");
        c.getPiece().movePiece(casillaSelected, true);
        casillaSelected.setPiece(c.getPiece());
        c.removePiece();
        Pieza p = jugador.removePiece();
        if(p != null && p.getCasilla().equals(c))
            c.setPiece(p);
    }

    public void selectPiece(Casilla c)
    {
//        if(!c.hasPiece())
        if(!c.hasPiece() || !c.getPiece().getColor().equals(jugador.getColor()))
            return;

        c.selectTile(Color.CLEAR);
        casillaSelected = c;
        isSelected = true;
    }

    public void grabPiece(Casilla c)
    {
        if(!c.hasPiece() || isGrabbed)
            return;

        casillaSelected = c;
        isGrabbed = true;

    }

    public void dropPiece(Casilla c)
    {

        if(!isGrabbed)
            return;

        this.movePiece(c);

    }

    private void cleanCasillaSelected()
    {
        casillaSelected = new Casilla();
    }

}
