package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Teg {
    private Turnos turnos;
    private Tablero tablero = new Tablero();
    private Map<String, Jugador> jugadores = new HashMap<>();
    private String[] colores = ["amarillo", "rojo","azul", "magenta", "negro", "verde"]

    Teg(int numerosJugadores){
        for(int i=0;i<6 && i<numerosJugadores;i++){
            this.jugadores.put(colores[i], new Jugador(colores[i]));
        }
        for(String color: colores){
            this.jugadores.put(color, new Jugador(color));
        }
    }

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(paisAtacante, paisDefensor, cantidad, this.turnos);//Else exception
    }

    public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }
}
