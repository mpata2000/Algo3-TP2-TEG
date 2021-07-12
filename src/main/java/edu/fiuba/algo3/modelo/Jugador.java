package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.io.*;

public class Jugador {
    private Ejercito ejercito;
    private Tablero tablero;
    private String nombre;

    public Jugador(String nombre){
        this.nombre = nombre;
    }
/**
    public void atacarConA(Pais nombrePaisMio,Pais nombrePaisEnemgio){
        mapa.atacar(nombrePaisMio,nombrePaisEnemgio);
    }

    public void setearEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    public void agregarTropas(int cant){
    }

    public Ejercito ejercito(){
        return this.ejercito;
    }
 **/
    public boolean esElMismoJugador(Jugador jugador){
        return this == jugador;
    }
}
