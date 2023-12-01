package com.mygdx.game.processor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.objects.Casilla;
import com.mygdx.game.objects.PGN;
import com.mygdx.game.objects.Player;
import com.mygdx.game.objects.Tablero;
import com.mygdx.game.objects.pieces.Peon;
import com.mygdx.game.objects.pieces.Pieza;

public class GameProcessor
{

    private boolean isSelected = false;
    private boolean isGrabbed = false;
    private boolean transformPiece = false;
    private Casilla casillaSelected = null;
    private Casilla transformTile = null;
    private Player jugador;
    private PGN notacion;
    private Peon ultimoPeon = null;
    Group buttons = new Group();

    private Player jugadorBlanco;
    private Player jugadorNegro;
    private Stage stage;


    private final Tablero tablero = Tablero.getInstance();


    public GameProcessor(Stage stage)
    {
        jugadorBlanco = new Player(Color.WHITE, tablero.getCasilla(5, 1).getPiece());
        jugadorNegro = new Player(Color.BLACK, tablero.getCasilla(5, 8).getPiece());
        notacion = new PGN("assets/partidaAjedrez.txt", jugadorBlanco, jugadorNegro);;
        notacion.writeFirstData();
        this.jugador = jugadorBlanco;
        this.stage = stage;
        cleanCasillaSelected();
    }

    public boolean movePiece(Casilla c)
    {

        if(!isSelected && !isGrabbed && !transformPiece)
            return false;

        if(casillaSelected.equals(c))
        {
            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(!casillaSelected.getPiece().movePiece(c, false))
        {

            if(casillaSelected.getPiece() instanceof Peon && casillaSelected.getPiece().getTransform())
            {
                transformTile = c;
                upgradePiece((Peon) casillaSelected.getPiece(), stage);
                transformPiece = true;
            }

            isGrabbed = false;
            isSelected = false;
            casillaSelected.unSelectTile();
            return false;
        }

        if(c.hasPiece())
        {
            jugador.addPiece(c.getPiece());
            c.deletePiece();
        }
        if(Peon.enPassa)
        {
            jugador.addPiece(tablero.getCasilla(c.getxBoard(), Peon.letter).getPiece());
            tablero.getCasilla(c.getxBoard(), Peon.letter).getPiece().deletePiece();
            Peon.enPassa = false;
        }

        c.setPiece(casillaSelected.getPiece());
        casillaSelected.unSelectTile();
        casillaSelected.removePiece();

        isSelected = false;
        isGrabbed = false;

        if(jugador.getKing().isChecked())
        {
            undoMove(c);
            cleanCasillaSelected();
            return false;
        }

        this.jugador = this.jugador == jugadorBlanco ? jugadorNegro : jugadorBlanco;

        if(!(c.getPiece() instanceof Peon))
            Peon.letter = 0;

        notacion.guardarJugada(c.getPiece());
        cleanCasillaSelected();

        transformPiece = false;

        return true;
    }

    public void undoMove(Casilla c)
    {
        c.getPiece().movePiece(casillaSelected, true);
        casillaSelected.setPiece(c.getPiece());
        c.removePiece();
        jugador.removePiece(c);

    }

    public void selectPiece(Casilla c)
    {

        if(!c.hasPiece() || !c.getPiece().getColor().equals(jugador.getColor()))
            return;

        c.selectTile(Color.CLEAR);
        casillaSelected = c;
        isSelected = true;
        removeActors();
    }

    public void grabPiece(Casilla c)
    {
        removeActors();

        if(!c.hasPiece() || isGrabbed)
            return;

        casillaSelected = c;
        isGrabbed = true;

    }

    public void dropPiece(Casilla c)
    {

        if(!isGrabbed)
            return;

        this.movePiece(c);

    }

    private void cleanCasillaSelected()
    {
        casillaSelected = new Casilla();
    }

    public void upgradePiece(final Peon p, final Stage stage)
    {

        Drawable drawRook = new TextureRegionDrawable(new TextureRegion(new Texture("WhiteRook.png")));
        Drawable drawQueen = new TextureRegionDrawable(new TextureRegion(new Texture("WhiteQueen.png")));
        Drawable drawBishop = new TextureRegionDrawable(new TextureRegion(new Texture("WhiteBishop.png")));
        Drawable drawKnight = new TextureRegionDrawable(new TextureRegion(new Texture("WhiteKnight.png")));
        ImageButton rookButton = new ImageButton(drawRook);
        ImageButton queenButton = new ImageButton(drawQueen);
        ImageButton bishopButton = new ImageButton(drawBishop);
        ImageButton knightButton = new ImageButton(drawKnight);
        rookButton.setBounds(450, 300, 100, 100);
        rookButton.getImage().setFillParent(true);
        queenButton.setBounds(450, 200, 100, 100);
        queenButton.getImage().setFillParent(true);
        bishopButton.setBounds(450, 100, 100, 100);
        bishopButton.getImage().setFillParent(true);
        knightButton.setBounds(450, 0, 100, 100);
        knightButton.getImage().setFillParent(true);
        //create your button
//        TextButton button = new TextButton("Button1", skin);

        //add it to your stage
        stage.addActor(buttons);
        buttons.addActor(rookButton);
        buttons.addActor(queenButton);
        buttons.addActor(bishopButton);
        buttons.addActor(knightButton);


        // add a listener to your buttons so it does something when clicked
        rookButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                p.setNewPiece("Rook");
                movePiece(transformTile);
                p.setTransform(false);
                p.morph("Rook", stage);
                removeActors();
            }
        });

        queenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                p.setNewPiece("Queen");
                movePiece(transformTile);
                p.setTransform(false);
                p.morph("Queen", stage);
                removeActors();
            }
        });

        bishopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                p.setNewPiece("Bishop");
                movePiece(transformTile);
                p.setTransform(false);
                p.morph("Bishop", stage);
                removeActors();
            }
        });

        knightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                p.setNewPiece("Knight");
                movePiece(transformTile);
                p.setTransform(false);
                p.morph("Knight", stage);
                removeActors();
            }
        });

    }

    public void removeActors()
    {
        this.buttons.remove();

    }

}
