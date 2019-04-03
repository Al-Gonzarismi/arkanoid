/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Animable;
import figuras.base.Dibujable;
import figuras.base.Sprite;
import java.awt.Graphics;
import logic.GameLogic;
import java.awt.Rectangle;
import java.util.*;

/**
 *
 * @author Usuario
 */
public class Pelota extends Sprite implements Animable {

    Rectangle nave;
    Rectangle pelotita;
    GameLogic logic;
    List<Dibujable> l;
    int incre;
    private int increX;
    private int increY;

    public Pelota(GameLogic logic) {
        super(new String[]{
            "assets/img/ball.png"
        });
        setX(330);
        setY(560);
        increX = 5;
        increY = 5;
        this.logic = logic;
        l = new ArrayList<>(logic.getListaObjetos());
    }

    @Override
    public void mover() {
        setX(getX() + increX);
        setY(getY() - increY);
        Breakout b = null;
        if (getY() >= 560 && logic.isEspacio()) {
            for (Dibujable d : l) {
                if (d instanceof Breakout) {
                    b = (Breakout) d;
                    break;
                }
            }
            pelotita = new Rectangle(getX(), getY(), getWidth(), getHeight());
            nave = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            if (nave.intersects(pelotita)) {
                increY = -increY;
            }
        }
        if (getX() < 20 || getX() > 480) {
            increX = -increX;
        } else if (getY() < 68) {
            increY = -increY;
        } else if (getY() > 650) {
            logic.setVidas(logic.getVidas() - 1);
            logic.reset();
        }
    }

    public void moverIzquierda() {
        Breakout b = null;
        for (Dibujable d : l) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
                break;
            }
        }
        if (getX() - b.getWidth() / 2 > 0) {
            setX(getX() - b.getIncre());
        }
    }

    public void moverDerecha() {
        Breakout b = null;
        for (Dibujable d : l) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
                break;
            }
        }
        if (getX() + b.getWidth() / 2 < 520) {
            setX(getX() + b.getIncre());
        }
        
    }

    public int getIncreX() {
        return increX;
    }

    public void setIncreX(int increX) {
        this.increX = increX;
    }

    public int getIncreY() {
        return increY;
    }

    public void setIncreY(int increY) {
        this.increY = increY;
    }

}
