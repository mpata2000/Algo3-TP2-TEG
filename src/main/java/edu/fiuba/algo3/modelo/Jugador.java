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

    public boolean esElMismoJugador(Jugador jugador){
        return this == jugador;
    }

}
