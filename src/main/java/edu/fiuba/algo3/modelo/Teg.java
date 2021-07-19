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
    private ArrayList<CartaPais> cartas;

    public Teg() throws ArchivoNoEncontrado {
        LectorDeJson lector = new LectorDeJson();
        this.tablero = lector.lectorTablero("resources/Teg-Tablero.json");
    }

    public void comenzarJuego(int cantidadJugadores) {
        for (int i = 0; i < 6 && i < cantidadJugadores; i++) {
            this.jugadores.put(this.colores[i], new Jugador(this.colores[i]));
        }
        for (String color : this.colores) {
            this.jugadores.put(color, new Jugador(color));
        }

        this.turnos = new Turnos(this.jugadores);
        this.turnos.repartirPaises(this.cartas);
    }

    public void rondaInicialColocarEjercitos(String color, String nombrePais,int cant){
        Jugador jugador = this.buscarJugador(color);
        this.tablero.agregarFichas(cant,jugador,nombrePais,this.turnos);
        jugador.actualizarFichasActuales(cant);
    }

    public void rondaColocarEjercitos(String color,String nombrePais,int cant){
        Jugador jugador = this.buscarJugador(color);
        this.tablero.agregarFichas(cant,jugador,nombrePais,this.turnos);
    }

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(paisAtacante, paisDefensor, cantidad, this.turnos);//Else exception
    }

    public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }
}
