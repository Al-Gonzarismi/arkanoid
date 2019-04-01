/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Dibujable;
import figuras.base.Eliminable;
import figuras.base.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import logic.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Ladrillo extends Sprite implements Eliminable {
    Rectangle ladrillo;
    Rectangle ladrilloLadoIzq;
    Rectangle ladrilloLadoDcha;
    Rectangle pelotita;
    GameLogic logic;
    int numeroSkin;
    int golpes;

    public Ladrillo(GameLogic logica, int x, int y, int numeroSkin) {
        super(new String[]{
            "assets/img/brick_blue.png",
            "assets/img/brick_cyan.png",
            "assets/img/brick_green.png",
            "assets/img/brick_magenta.png",
            "assets/img/brick_orange.png",
            "assets/img/brick_red.png",
            "assets/img/brick_yellow.png",
            "assets/img/hard.png"
        });
        setX(x);
        setY(y);
        if (numeroSkin == 7) {
            this.numeroSkin = 7;
            golpes = 0;
        }
        setSkin(numeroSkin);
        setWidth(60);
        setHeight(22);
        this.logic = logica;
    }
    
    
    
    @Override
    public boolean estaEliminado() {
        ladrillo = new Rectangle(getX(), getY(), getWidth(), getHeight());
        ladrilloLadoIzq = new Rectangle(getX(), getY() + 2, 1, getHeight() - 4);
        ladrilloLadoDcha = new Rectangle(getX() + getWidth(), getY() + 2, 1, getHeight() - 4);
        Pelota p = null;
        List<Dibujable> l = new LinkedList<>(logic.getListaObjetos());
        for (Dibujable d : l) {
            if (d instanceof Pelota) {
                p = (Pelota) d;
                break;
            }
        }
        pelotita = new Rectangle(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        if (pelotita.intersects(ladrillo)) {
            if (pelotita.intersects(ladrilloLadoDcha) || pelotita.intersects(ladrilloLadoIzq)) {
                p.setIncreX(-p.getIncreX());
            } else {
                p.setIncreY(-p.getIncreY());
            }
            if (numeroSkin == 7) {
                golpes++;
                if (golpes == 3) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    } 
}
