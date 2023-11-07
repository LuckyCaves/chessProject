package com.mygdx.game.processor;

import com.mygdx.game.objects.Casilla;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private Casilla casillaSelected = null;

    public GameProcessor()
    {
        cleanCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {

        if(!isSelected && !isGrabbed)
            return false;

        if(!casillaSelected.equals(c)
                && casillaSelected.getPiece().movePiece(c))
        {
            c.setPiece(casillaSelected.getPiece());
            casillaSelected.removePiece();
        }

        cleanCasillaSelected();
        isSelected = false;
        isGrabbed = false;

        return true;
    }

    public void selectPiece(Casilla c)
    {
        if(!c.hasPiece())
            return;

        casillaSelected = c;
        isSelected = true;
    }

    public void grabPiece(Casilla c)
    {
        if(!c.hasPiece())
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
