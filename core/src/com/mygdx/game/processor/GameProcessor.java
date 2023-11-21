package com.mygdx.game.processor;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Tablero;

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
        this.jugador = jugadorNegro;
        cleanCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {

        if(!isSelected && !isGrabbed)
            return false;

        if(casillaSelected.equals(c) || jugador.getKing().isChecked())
        {
            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(!casillaSelected.getPiece().movePiece(c))
        {
            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(c.hasPiece())
            c.deletePiece();

        c.setPiece(casillaSelected.getPiece());
        casillaSelected.unSelectTile();
        casillaSelected.removePiece();

        cleanCasillaSelected();
        isSelected = false;
        isGrabbed = false;

        this.jugador = this.jugador == jugadorBlanco ? jugadorNegro : jugadorBlanco;

        return true;
    }

    public void selectPiece(Casilla c)
    {
//        if(!c.hasPiece())
        if(!c.hasPiece() || c.getPiece().getColor().equals(jugador.getColor()))
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
