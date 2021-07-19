package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.ArchivoNoEncontrado;
import edu.fiuba.algo3.lectorJson.LectorDeJson;

import java.util.HashMap;
import java.util.Map;

public class Teg {
    private Turnos turnos;
    private Tablero tablero;
    private Map<String, Jugador> jugadores = new HashMap<>();

    public Teg(int cantidadJugadores) throws ArchivoNoEncontrado {
        LectorDeJson lector = new LectorDeJson();
        this.tablero = lector.lectorTablero("resources/Teg-Tablero.json");
    }

    /*private Teg(int numerosJugadores){
        for(int i=0;i<6 && i<numerosJugadores;i++){
            this.jugadores.put(colores[i], new Jugador(colores[i]));
        }
        for(String color: colores){
            this.jugadores.put(color, new Jugador(color));
        }
    }*/

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(paisAtacante, paisDefensor, cantidad, this.turnos);//Else exception
    }

    public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }
}
