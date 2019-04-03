/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import audio.StdSound;
import figuras.Breakout;
import figuras.Fondo;
import figuras.Ladrillo;
import figuras.LadrilloHabilidad;
import figuras.Marcador;
import figuras.Pelota;
import figuras.base.Animable;
import figuras.base.Dibujable;
import figuras.base.Eliminable;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author victor
 */
public class GameLogic {

    // CONSTANTES DEL JUEGO
    public static final int NUM_VIDAS = 3;
    // añade las constantes que estimes oportuno.

    private int vidas;
    private int puntos;

    /**
     * Lista de todos los objetos del juego, para dibujar, automover y eliminar
     */
    private List<Dibujable> listaObjetosDibujables;
    List<Dibujable> listaLadrillos;
    // --- Los objetos de los que quieras tener una referencia
    Breakout breakout;
    Pelota pelota;
    Ladrillo ladrillo;
    LadrilloHabilidad ladrilloHabilidad;

    // TODO Añadir la pelota, una colección con los ladrillos, etc..
    private boolean espacio = false;
    private int highscore = 0;
    private int level = 0;
    //Variable de apoyo
    int puntosAdi = 0;
    

    public GameLogic() {
        listaObjetosDibujables = new LinkedList<>();
        listaLadrillos = new LinkedList<>();
        empezar();
    }

    /**
     * Invocado en cada fotograma desde el frame
     *
     * @param g
     */
    public void dibujarYActualizarTodo(Graphics g) {
        int ejeX = 0;
        int ejeY = 0;
        Iterator<Dibujable> iter = listaObjetosDibujables.iterator();
        while (true) {
            if (!iter.hasNext()) { // Si no hay siguiente, salir del bucle
                break;
            }
            Dibujable objetoDelJuego = iter.next(); // Acceder al objeto
            if (objetoDelJuego instanceof Eliminable) { // Si está eliminado lo quitamos
                if (((Eliminable) objetoDelJuego).estaEliminado()) {
                    if (objetoDelJuego instanceof Ladrillo) {
                        listaLadrillos.remove(objetoDelJuego);
                        puntos += 10;
                        puntosAdi += 10;
                        if (puntosAdi >= 100) {
                            ladrillo = (Ladrillo) objetoDelJuego;
                            ejeX = ladrillo.getX();
                            ejeY = ladrillo.getY();
                        }
                    }
                    iter.remove();
                    continue;
                }
            }
            objetoDelJuego.dibujar(g); // lo dibujamos
            if (objetoDelJuego instanceof Animable) { // Y si está auto-animado, lo movemos
                if (objetoDelJuego instanceof Pelota) {
                    if (espacio) {
                        ((Animable) objetoDelJuego).mover();
                    }
                } else {
                    ((Animable) objetoDelJuego).mover();
                }
            }
        }
        if (puntos > highscore) {
            highscore = puntos;
        }
        if (listaLadrillos.isEmpty()) {
            level++;
            inicializarNivel(level);
        }
        if (getVidas() == 0) {
            empezar();
        }
        if (puntosAdi >= 100) {
            Random miRandom = new Random();
            int habilidadAleatoria = miRandom.nextInt(4);
            puntosAdi = 0;
            ladrilloHabilidad = new LadrilloHabilidad(this, ejeX, ejeY, habilidadAleatoria);
            listaObjetosDibujables.add(ladrilloHabilidad);
        }
    }

    /**
     * Invocado en cada fotograma desde el frame
     *
     * @param teclas
     */
    public void gestionarTeclas(Set<Integer> teclas) {
        if (teclas.contains(KeyEvent.VK_LEFT)) {
            breakout.moverIzquierda();
            if (!espacio) {
                pelota.moverIzquierda();
            }

        } else if (teclas.contains(KeyEvent.VK_RIGHT)) {
            breakout.moverDerecha();
            if (!espacio) {
                pelota.moverDerecha();
            }
        } else if (teclas.contains(KeyEvent.VK_SPACE)) {
            espacio = true;
            pelota.mover();
        }

    }

    public List<Dibujable> getListaObjetos() {
        return listaObjetosDibujables;
    }

    public void empezar() {
        // TO-DO Inicia el juego!
        listaObjetosDibujables.clear();
        listaLadrillos.clear();
        inicializarNivel(0);
    }

    public void inicializarNivel(int nivel) {
        // TO-DO
        if (nivel == 0) {
            StdSound.playMidi(("assets/audio/start_level.mid"));
            vidas = NUM_VIDAS;
            puntos = 0;
            // TODO
            listaObjetosDibujables.add(new Fondo("assets/img/fondo00.jpg"));
            listaObjetosDibujables.add(new Marcador(this)); // inyección de dependencias
            breakout = new Breakout(this); // inyección de dependencias
            listaObjetosDibujables.add(breakout);
            pelota = new Pelota(this);
            listaObjetosDibujables.add(pelota);// inyección de dependencias

            // TODO 
        } else {
            reset();
        }
        int x;
        int y;
        int numeroSkin = 0;
        int cantidad = 0;
        int filas = 0;
        String[][] m = MapaNivel.mapa;
        for (int j = 0; j < m[nivel].length; j++) {
            String f = m[nivel][j];
            for (int k = 0; k < f.length(); k++) {
                char c = f.charAt(k);
                switch (c) {
                    case 'b':
                        numeroSkin = 0;
                        break;
                    case 'c':
                        numeroSkin = 1;
                        break;
                    case 'g':
                        numeroSkin = 2;
                        break;
                    case 'm':
                        numeroSkin = 3;
                        break;
                    case 'o':
                        numeroSkin = 4;
                        break;
                    case 'r':
                        numeroSkin = 5;
                        break;
                    case 'y':
                        numeroSkin = 6;
                        break;
                    case 'h':
                        numeroSkin = 7;
                        break;
                    case ' ':
                        numeroSkin = -1;
                    default:
                        break;
                }
                if (cantidad > 7) {
                    cantidad = 0;
                    filas++;
                }
                x = (20 + (60 * cantidad));
                y = (60 + (22 * filas));
                cantidad++;
                if (numeroSkin >= 0) {
                    ladrillo = new Ladrillo(this, x, y, numeroSkin);
                    listaLadrillos.add(ladrillo);
                }
            }
        }
        listaObjetosDibujables.addAll(listaLadrillos);// inyección de dependencias
        // TODO 
    }

    public void reset() {
        espacio = false;
        Breakout b = null;
        Pelota p = null;
        for (Dibujable d : listaObjetosDibujables) {
            if (d instanceof Breakout) {
                b = (Breakout) d;
            }
            if (d instanceof Pelota) {
                p = (Pelota) d;
            }
        }
        p.setX(b.getX() + b.getWidth() / 2);
        p.setY(b.getY() - 10);
        p.setIncreX(5);
        p.setIncreY(5);
        b.setSkin(0);
        b.setIncre(5);
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean isEspacio() {
        return espacio;
    }

    public void setEspacio(boolean espacio) {
        this.espacio = espacio;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
