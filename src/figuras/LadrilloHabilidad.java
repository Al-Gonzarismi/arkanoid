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

    GameLogic logic;
    Rectangle ladrilloHabilidad;
    Rectangle nave;
    List<Dibujable> l;

    public LadrilloHabilidad(GameLogic logic, int x, int y, int numeroSkin) {
        super(new String[]{
            "assets/img/special_blue.png",
            "assets/img/special_magenta.png",
            "assets/img/special_red.png",
            "assets/img/special_orange.png", //dopar la nave
            "assets/img/special_orange.png", // relentizar la nave
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
        setY(getY() + 5);
    }

    public void habilidad(int i) {
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
        if (i == 0) {
            b.setSkin(1);
        } else if (i == 1) {
            p.setIncreY(p.getIncreY() * 2);
            p.setIncreX(p.getIncreX() * 2);

        } else if (i == 2) {
            logic.setVidas(logic.getVidas() + 1);
        }
    }

    @Override
    public boolean estaEliminado() {
        Breakout b = null;
        for (Dibujable d : l) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
                break;
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
