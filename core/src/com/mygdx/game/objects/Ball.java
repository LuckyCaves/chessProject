package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Ball extends Actor
{

    private int x, y;
    private int size;
    private int xSpeed, ySpeed;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed)
    {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update()
    {
//        x += xSpeed;
//        y += ySpeed;
//
//        if(x < 0 || x > Gdx.graphics.getWidth())
//            xSpeed = -xSpeed;
//
//        if(y < 0 || y > Gdx.graphics.getHeight())
//            ySpeed = -ySpeed;
    }

    public void draw(ShapeRenderer shape)
    {
        shape.circle(x, y, size);
    }
}
