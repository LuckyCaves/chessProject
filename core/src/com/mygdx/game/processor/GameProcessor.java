package com.mygdx.game.processor;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.Tablero;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private Casilla casillaSelected = null;

    private final Tablero tablero = Tablero.getInstance();;

    public GameProcessor()
    {
        cleanCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {

        if(!isSelected && !isGrabbed)
            return false;

        if(casillaSelected.equals(c) || !casillaSelected.getPiece().movePiece(c))
            return false;

        if(c.hasPiece())
            c.removePiece();

        c.setPiece(casillaSelected.getPiece());
        casillaSelected.unSelectTile();
        casillaSelected.removePiece();

        cleanCasillaSelected();
        isSelected = false;
        isGrabbed = false;


        return true;
    }

    public void selectPiece(Casilla c)
    {
        if(!c.hasPiece())
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
