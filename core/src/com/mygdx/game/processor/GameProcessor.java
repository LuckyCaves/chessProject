package com.mygdx.game.processor;

import com.mygdx.game.objects.Casilla;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private Casilla casillaSelected = null;

    public GameProcessor()
    {
        setCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {
        if(!isSelected || !isGrabbed)
            return false;

        if(!casillaSelected.equals(c) && casillaSelected.getPiece().movePiece(c.getxBoard(), c.getyBoard()))
        {
            c.setPiece(casillaSelected.getPiece());
            casillaSelected.removePiece();
        }
        setCasillaSelected();
        isSelected = false;
        isGrabbed = false;

        return true;
    }

    public void selectPiece(Casilla c)
    {
        if(c.hasPiece())
        {
            casillaSelected = c;
            isSelected = true;
        }
    }

    public void grabPiece(Casilla c)
    {
        if(c.hasPiece())
        {
            casillaSelected = c;
            isGrabbed = true;
        }
    }

    private void setCasillaSelected()
    {
        casillaSelected = new Casilla();
    }

}
