package com.mygdx.game.objects;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.EventListener;

public class BallClickListener extends ClickListener implements EventListener {

    private Ball ball;

    public BallClickListener(Ball ball)
    {
        this.ball = ball;
    }

    @Override
    public void clicked(InputEvent event, float x, float y)
    {
        System.out.println("Presionaste una pelota");
    }
}
