package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.lector.LectorDeJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teg {
    private Tablero tablero;
    private Map<String, Jugador> jugadores = new HashMap<>();
    private ColeccionDeCartasPais cartas;

    public Teg(){
        LectorDeJson lector = new LectorDeJson();
        this.tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        this.cartas = new ColeccionDeCartasPais(lector.lectorCartasPais("resources/Teg-Cartas.json"));
    }

    public Teg(Tablero tablero,Map <String,Jugador> jugadores){
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.cartas = new ColeccionDeCartasPais();
    }

    public void comenzarJuego(List<String> colores) {
        for (String color : colores) {
            this.jugadores.put(color, new Jugador(color));
        }

        this.cartas.asignarPaises(new ArrayList<>(this.jugadores.values()));
    }

    public void colocarFichas(String colorJugador, String nombrePais, int cant){
        this.tablero.agregarFichas(cant,this.jugadores.get(colorJugador),nombrePais);
    }

    public boolean atacar(String colorJugador,String paisAtacante, String paisDefensor, int cantidad){
        Jugador jugador = this.jugadores.get(colorJugador);
        if(tablero.atacar(jugador,paisAtacante, paisDefensor, cantidad)){
            jugador.conquistoPais();
            return true;
        }
        return false;
    }

    public void pasarFichas(String paisUno, String paisDos, int cant){
        this.tablero.pasarFichas(paisUno, paisDos, cant);
    }

    public boolean jugadorTieneFichas(String colorJugador) {
        return this.jugadores.get(colorJugador).tieneFichas();
    }

    public void agregarFichasDisponiblesA(String colorJugador) {
        Jugador jugador = this.jugadores.get(colorJugador);
        jugador.hacerCanje(this.cartas);
        this.tablero.agregarFichasA(jugador);
    }

    public void agregarFichasA(String colorJugador, int cantidadFichas) {
        this.jugadores.get(colorJugador).agregarFichas(cantidadFichas);
    }

    public void darCarta(String colorJugador) {
        this.cartas.darCartaA(this.jugadores.get(colorJugador));
    }

    public int cantidadDePaisesJugador(Jugador jugador) {
        return this.tablero.cantidadDePaisesJugador(jugador);
    }

    public int cantidadDePaisesJugadorEnContinente(String continente, Jugador jugador) {
        return this.tablero.cantidadDePaisesJugadorEnContinente(continente,jugador);
    }
}
