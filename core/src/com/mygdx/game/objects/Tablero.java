package com.mygdx.game.objects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.objects.pieces.Pieza;


public class Tablero
{

    private final Casilla[][] tablero = new Casilla[8][8];
    private final Vector2d[] bordes = new Vector2d[2];
    InputAdapter inputProcessor;
    private boolean isSelected = false;
    private Casilla casillaSelected;

    public Tablero()
    {

        boolean color = false;

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                this.tablero[i][j] = new Casilla(i + 1, j + 1, color);
                color = !color;
            }
            color = !color;
        }

        this.bordes[0] = new Vector2d(this.tablero[0][0].x, this.tablero[0][0].y - 20);
        this.bordes[1] = new Vector2d(this.tablero[7][7].x + 50, this.tablero[7][7].y + 30);

        inputProcessor = new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if(Vector2d.isInBounds(bordes, screenX, screenY))
                {
                    if(!isSelected)
                        casillaSelected = (Casilla) tablero[coordsX(screenX)][coordsY(screenY)];

                    if(casillaSelected.hasPiece() || isSelected)
                    {
                        if(casillaSelected.move(coordsX(screenX) + 1, coordsY(screenY) + 1))
                        {
                            tablero[coordsX(screenX)][coordsY(screenY)].setPiece(casillaSelected.getPiece());
                            casillaSelected.removePiece();
                        }
                        isSelected = !isSelected;
                        System.out.println(isSelected);
                    }
                    System.out.println(Tablero.translateCoordsX(screenX) + " " + Tablero.translateCoordsY(screenY));
                }
                return false;
            }
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if(Vector2d.isInBounds(bordes, screenX, screenY))
                    System.out.println(Tablero.translateCoordsX(screenX) + " " + Tablero.translateCoordsY(screenY));
                return false;
            }
        };
        Gdx.input.setInputProcessor(inputProcessor);
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public void agregarPieza(Pieza pieza, int boardX, int boardY)
    {
        tablero[boardX][boardY].setPiece(pieza);
    }
    public void update()
    {
    }

    public void draw(ShapeRenderer shape)
    {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                tablero[i][j].draw(shape);
    }



    public static char translateCoordsX(int x)
    {
        return translateBoardCoordsX((int) ((x - 51) / 50));
    }

    public static int translateCoordsY(int y)
    {
        return translateBoardCoordsY((int) ((y - 31) / 50));
    }

    public static int coordsX(int x)
    {
        return (int) ((x - 51) / 50);
    }

    public static int coordsY(int y)
    {
        return (int) ((y - 31) / 50);
    }

    public static char translateBoardCoordsX(int x)
    {
        return (char) (x + 65);
    }

    public static int translateBoardCoordsY(int y)
    {
        return y + 1;
    }
}
