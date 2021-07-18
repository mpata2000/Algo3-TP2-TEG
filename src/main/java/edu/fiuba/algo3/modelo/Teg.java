package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.ArchivoNoEncontrado;
import edu.fiuba.algo3.lectorJson.LectorDeJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Teg {
    private Turnos turnos;
    private Tablero tablero;
    private Map<String, Jugador> jugadores = new HashMap<>();
    private String[] colores = {"Amarillo", "verde", "azul", "rojo", "Rosa", "Negro"};
    private ArrayList<Pais> paises;

    public Teg(int cantidadJugadores) throws ArchivoNoEncontrado {
        this.tablero = LectorDeJson.lectorTablero();

        for (int i = 0; i < 6 && i < cantidadJugadores; i++) {
            this.jugadores.put(this.colores[i], new Jugador(this.colores[i]));
        }
        for (String color : this.colores) {
            this.jugadores.put(color, new Jugador(color));
        }
    }

    public void comenzarJuego() {
        this.turnos = new Turnos(this.jugadores);
        this.turnos.repartirPaises(this.paises);
    }

    public void rondaColocarEjercitos(String nombrePais,int cant){
        this.tablero.agregarFichas(nombrePais,5);
        this.tablero.agregarFichas(nombrePais,3);

    }
    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(paisAtacante, paisDefensor, cantidad, this.turnos);//Else exception
    }

    public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }
}
