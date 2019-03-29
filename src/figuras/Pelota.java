/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Animable;
import figuras.base.Dibujable;
import figuras.base.Sprite;
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
    
    private int increX;
    private int increY;
    public Pelota(GameLogic logic) {
        super(new String[] {
            "assets/img/ball.png"
        });
        setX(330);
        setY(560);
        increX = 5;
        increY = 5;
        this.logic = logic;
    }

    @Override
    public void mover() {
        setX(getX() + increX);
        setY(getY() - increY);
        if (getY() == 565) {
            Breakout b = null;
            List<Dibujable> l = new ArrayList<>(logic.getListaObjetos());
            for (Dibujable d : l) {
                if (d instanceof Breakout) {
                    b = (Breakout) d;
                    break;
                }
            }
            nave = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            pelotita = new Rectangle (getX(), getY(), getWidth(), getHeight());
            if (nave.intersects(pelotita)) {
                increY = -increY;
            }
        }
        if (getX() < 20 || getX() > 480) {
            increX = -increX;
        } else if (getY() < 50) {
            increY = -increY;
        }
    }
    
    public void moverIzquierda() {
        setX(getX() - 5);
    }
    
    public void moverDerecha() {
        setX(getX() + 5);
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
