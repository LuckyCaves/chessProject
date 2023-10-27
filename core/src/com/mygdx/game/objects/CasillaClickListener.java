package com.mygdx.game.objects;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class CasillaClickListener extends ClickListener
{

    private final Casilla casilla;

    public CasillaClickListener(Casilla casilla)
    {
        this.casilla = casilla;
    }

    @Override
    public void clicked(InputEvent event, float x, float y)
    {
        System.out.println(this.casilla.toString() + "has been clicked");
    }

}
