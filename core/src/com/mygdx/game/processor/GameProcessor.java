package com.mygdx.game.processor;

import com.mygdx.game.objects.Casilla;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private Casilla casillaSelected = null;


    public void movePiece(Casilla c)
    {
        if(!isSelected)
            casillaSelected = c;
        else
        {
            if(casillaSelected.equals(c))
            {
                casillaSelected.getPiece().setIsSelected(false);
                casillaSelected = null;
                isSelected = false;
                return;
            }
        }


        if(casillaSelected.hasPiece() || isSelected)
        {
            if(casillaSelected.move(c.getxBoard(), c.getyBoard()))
            {
                c.setPiece(casillaSelected.getPiece());
                casillaSelected.removePiece();
            }
            isSelected = !isSelected;
        }
    }

    public void grabPiece(Casilla c)
    {
        if(!isGrabbed)
        {
            casillaSelected = c;
            isGrabbed = true;
        }


        if(casillaSelected.hasPiece())
        {
            casillaSelected.move(c.getxBoard(), c.getyBoard());
        }
    }

    public void confirmMove(Casilla c)
    {
        if(casillaSelected.move(c.getxBoard(), c.getyBoard()) && isGrabbed)
        {
            c.setPiece(casillaSelected.getPiece());
            casillaSelected.removePiece();
            isGrabbed = false;
        }

    }

}
