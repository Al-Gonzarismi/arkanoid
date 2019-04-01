/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Animable;
import figuras.base.Sprite;
import logic.GameLogic;

/**
 *
 * @author 1_web9_20
 */
public class LadrilloHabilidad extends Sprite implements Animable {
    GameLogic logic;
    public LadrilloHabilidad(GameLogic logic, int x, int y, int numeroSkin) {
        super(new String[] {
            "assets/img/special_blue.png",
            "assets/img/special_green.png",
            "assets/img/special_magenta.png",
            "assets/img/special_orange.png",
            "assets/img/special_red.png"
        });
        setX(x);
        setY(y);
        setSkin(numeroSkin);
        setWidth(60);
        setHeight(22);
        this.logic = logic;
    }

    @Override
    public void mover() {
        setY(getY() - 5);
    }
    
}
