package com.mygdx.game.processor;

import com.mygdx.game.objects.Casilla;

public class GameProcessor
{

    private boolean isSelected = false;
    private Casilla casillaSelected = null;


    public void movePiece(Casilla c)
    {
        if(!isSelected)
            casillaSelected = c;

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

}
