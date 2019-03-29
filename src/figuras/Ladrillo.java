/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figuras;

import figuras.base.Eliminable;
import figuras.base.Sprite;
import logic.*;
import java.util.*;
/**
 *
 * @author Usuario
 */
public class Ladrillo extends Sprite implements Eliminable {
    private List<Ladrillo> listaLadrillos;
    public Ladrillo(GameLogic logica) {
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
        int cantidad = 0;
        int filas = 0;
        String[][] m = MapaNivel.mapa;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                String f = m[i][j];
                for (int k = 0; k < f.length(); k++) {
                    char x = f.charAt(k);
                    switch (x) {
                        case 'b':
                            setSkin(0);
                            break;
                        case 'c':
                            setSkin(1);
                            break;
                        case 'g':
                            setSkin(2);
                            break;
                        case 'm':
                            setSkin(3);
                            break;
                        case 'o':
                            setSkin(4);
                            break;
                        case 'r':
                            setSkin(5);
                            break;
                        case 'y':
                            setSkin(6);
                            break;
                        case 'h':
                            setSkin(7);
                            break;
                        default:
                            break;
                    }
                    if (cantidad > 11) {
                        cantidad = 0;
                        filas++;
                    }
                    setX(20 +(44 * cantidad));
                    setY(20 +(22 * filas));
                    cantidad++;
                    listaLadrillos.add(this);
                }
            }
        }
    }

    @Override
    public boolean estaEliminado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Ladrillo> getListaLadrillos() {
        return listaLadrillos;
    }

    public void setListaLadrillos(List<Ladrillo> listaLadrillos) {
        this.listaLadrillos = listaLadrillos;
    }
    
    
}
