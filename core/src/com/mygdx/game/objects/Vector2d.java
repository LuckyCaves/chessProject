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

    public static double distance(int x1, int y1, int x2, int y2)
    {
        double sum1 = Math.pow(x2 - x1, 2);
        double sum2 = Math.pow(y2 - y1, 2);

        return Math.sqrt(sum1 + sum2);
    }

    public static double calculateSlope(int x1, int y1, int x2, int y2)
    {
        double sum1 = x2 - x1;
        double sum2 = y2 - y1;

        return (sum1 / sum2);
    }
}
