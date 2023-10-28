package com.mygdx.game.objects;

public class Vector2d
{

    private int x;
    private int y;

    Vector2d(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isInBounds(Vector2d[] bordes, int x, int y)
    {
        return x >= bordes[0].x && x <= bordes[1].x &&
                y >= bordes[0].y && y <= bordes[1].y;
    }
}
