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

   public Teg(Tablero tablero,HashMap <String,Jugador> jugadores){
        this.tablero = tablero;
        this.jugadores = jugadores;
    }


    public void comenzarJuego(List<String> colores) {
        for (String color : colores) {
            this.jugadores.put(color, new Jugador(color));
        }

        this.cartas.asignarPaises(new ArrayList<>(this.jugadores.values()));
    }


    public void rondaInicialColocarFichas(String jugador, String nombrePais, int cant){

        this.tablero.agregarFichas(cant,this.jugadores.get(jugador),nombrePais);
    }

    public void rondaColocarFichas(String jugador, String nombrePais, int cant){
        this.tablero.agregarFichas(cant, this.jugadores.get(jugador), nombrePais);
    }

    public boolean atacar(String jugador,String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(this.jugadores.get(jugador),paisAtacante, paisDefensor, cantidad);//Else exception
    }

    /*public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }*/
    public void reagrupar(String paisUno, String paisdos, int cant){

    }

    public boolean jugadorTieneFichas(String jugador) {
        return this.jugadores.get(jugador).tieneFichas();
    }

    public void calcularFichasDisponiblesDe(String jugadorActual) {
        this.tablero.calcularFichasDe(this.jugadores.get(jugadorActual));
    }
}
