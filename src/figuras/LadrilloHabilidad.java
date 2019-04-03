/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Animable;
import figuras.base.Dibujable;
import figuras.base.Eliminable;
import figuras.base.Sprite;
import java.awt.Rectangle;
import java.util.*;
import logic.GameLogic;

/**
 *
 * @author 1_web9_20
 */
public class LadrilloHabilidad extends Sprite implements Animable, Eliminable {
    boolean habilitar;
    int cantidadGolpes = 0;
    GameLogic logic;
    Rectangle ladrilloHabilidad;
    Rectangle nave;
    Rectangle pelotita;
    List<Dibujable> l;

    public LadrilloHabilidad(GameLogic logic, int x, int y, int numeroSkin) {
        super(new String[]{
            "assets/img/special_blue.png", //cambiar skin nave
            "assets/img/special_magenta.png",//dopar pelota o relentizar
            "assets/img/special_red.png", //vida o relentizar nave y dopar pelota
            "assets/img/special_orange.png", //dopar la nave o relentizar
        });
        setX(x);
        setY(y);
        setSkin(numeroSkin);
        setWidth(60);
        setHeight(22);
        this.logic = logic;
        l = new ArrayList<>(logic.getListaObjetos());

    }

    @Override
    public void mover() {
        setY(getY() + 2);
    }

    public void habilidad(int i) {
        Breakout b = null;
        Pelota p = null;
        Random r = new Random();
        int ale = r.nextInt(4);
        for (Dibujable d : l) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
            }
            if (d instanceof Pelota) {
                p = (Pelota) d;
            }
        }
        if (i == 0) {
            if (ale == 0) {
                b.setSkin(0);
            } else {
                b.setSkin(1);
            }
        } else if (i == 1) {
            if (ale == 0) {
                p.setIncreY(p.getIncreY() * 2);
                p.setIncreX(p.getIncreX() * 2);
            } else {
                p.setIncreY(p.getIncreY() / 2);
                p.setIncreX(p.getIncreX() / 2);
            }
        } else if (i == 2) {
            if (ale == 0) {
                p.setIncreY(p.getIncreY() * 2);
                p.setIncreX(p.getIncreX() * 2);
                if (b.getIncre() > 2) {
                    b.setIncre(b.getIncre() / 2);
                }
            } else {
                logic.setVidas(logic.getVidas() + 1);
            }
        } else if (i == 3) {
            if (ale == 0) {
                if (b.getIncre() > 2) {
                    b.setIncre(b.getIncre() / 2);
                }
            } else {
                b.setIncre(b.getIncre() * 2);
            }
        }
    }

    @Override
    public boolean estaEliminado() {
        Breakout b = null;
        Pelota p = null;
        for (Dibujable d : l) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
            }
            if (d instanceof Pelota) {
                p = (Pelota) d;
            }
        }
        nave = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
        ladrilloHabilidad = new Rectangle(getX(), getY(), getWidth(), getHeight());
        if (nave.intersects(ladrilloHabilidad)) {
            habilidad(getSelectedSkin());
            return true;
        }
        return false;
    }

}
