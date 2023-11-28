package com.mygdx.game.objects;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.objects.pieces.Pieza;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class PGN
{


    int movimiento = 0;
    Player player1;
    Player player2;
    File file;

    public PGN(String fileName, Player player1, Player player2)
    {

        this.player1 = player1;
        this.player2 = player2;

        FileWriter fileWriter = null;

        try {
            file = new File(fileName);
            fileWriter = crearCursor(fileName, false);
            file.createNewFile();
            file.setWritable(true);

        } catch (IOException e) {
            System.out.println("Hubo un error");
        }

        cerrarCursor(fileWriter);
    }

    public void writeFirstData(){

        FileWriter fileWriter = crearCursor(file.getPath(), false);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        System.out.println(date.toString());

        if(fileWriter == null)
            return;

        try
        {
            fileWriter.write("[Event \"?\"]\n" +
                    "[Site\"?\"]\n" +
                    "[Date \" "+ cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH) + 1) + "." + cal.get(Calendar.DAY_OF_MONTH)  + "\"]\n" +
                    "[Round \"?\"]\n" +
                    "[White \"?\"]\n" +
                    "[Black \"?\"]\n" +
                    "[Result \"*\"]\n" +
                    "[WhiteELO \"?\"]\n" +
                    "[BlackELO \"?\"]\n\n");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        this.cerrarCursor(fileWriter);

    }

    public void guardarJugada(Pieza pieza) {
        FileWriter fileWriter = crearCursor(file.getPath(), true);

        if(pieza.getColor() == Color.WHITE)
        {
            movimiento++;
            try
            {
                fileWriter.write(movimiento + ". ");
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }

        }


        try {
            fileWriter.write(pieza.getMoveDescription());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Player play = null;

        if(pieza.getColor() == Color.WHITE)
            play = player2;
        else
            play = player1;

        if(play.getKing().isChecked())
        {
            try {
                fileWriter.write("+");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            fileWriter.write(" ");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        cerrarCursor(fileWriter);
    }

    private FileWriter crearCursor(String fileName, boolean b)
    {
        FileWriter fileWriter = null;

        try
        {
            fileWriter = new FileWriter(fileName, b);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return fileWriter;
    }

    private void cerrarCursor(FileWriter fileWriter)
    {

        if(fileWriter == null)
            return;
        try
        {
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
