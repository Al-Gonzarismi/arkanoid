/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Sprite;
import logic.GameLogic;

/**
 *
 * @author victor
 */
public class Breakout extends Sprite {
    
    private int incre;
    
    public Breakout(GameLogic logic) {
        super(new String[]{
            "assets/img/breakout.png",
            "assets/img/breakout_big.png",
        });
        // cambiar y terminar
        setX(300);
        setY(570);
        incre = 5;
    }
    
    public void moverIzquierda() {
        setX(getX() - incre);
    }
    
    public void moverDerecha() {
        setX(getX() + incre);
    }

    public int getIncre() {
        return incre;
    }

    public void setIncre(int incre) {
        this.incre = incre;
    }
    
}
