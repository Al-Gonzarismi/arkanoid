/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Dibujable;
import figuras.base.Eliminable;
import figuras.base.Sprite;
import java.awt.Rectangle;
import logic.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Ladrillo extends Sprite implements Eliminable {
    Rectangle ladrillo;
    Rectangle pelotita;
    GameLogic logic;

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
        setSkin(numeroSkin);
        setWidth(60);
        this.logic = logica;       
    }

    @Override
    public boolean estaEliminado() {
        
        ladrillo = new Rectangle(getX(), getY(), getWidth(), getHeight());
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
            p.setIncreY(-p.getIncreY());
            return true;
        }
        return false;
    }
      
}
