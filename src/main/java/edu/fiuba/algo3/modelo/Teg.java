package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.lector.LectorDeJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teg {
    private Turnos turnos;
    private Tablero tablero;
    private Map<String, Jugador> jugadores = new HashMap<>();
    private String[] colores = {"Amarillo", "verde", "azul", "rojo", "Rosa", "Negro"};
    private ColeccionDeCartasPais cartas;

    public Teg(){
        LectorDeJson lector = new LectorDeJson();
        this.tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        this.cartas = new ColeccionDeCartasPais(lector.lectorCartasPais("resources/Teg-Cartas.json"));
    }

    //Todo: refactor de comenzar juego
    public void comenzarJuego(int cantidadJugadores) {
        for (int i = 0; i < 6 && i < cantidadJugadores; i++) {
            this.jugadores.put(this.colores[i], new Jugador(this.colores[i]));
        }

        this.turnos = new Turnos(this.jugadores,this);
        this.cartas.asignarPaises(new ArrayList<Jugador>(this.jugadores.values()));
    }


    public void rondaInicialColocarEjercitos( Jugador jugador,String nombrePais,int cant){
        this.tablero.agregarFichas(cant,jugador,nombrePais);
        this.turnos.devolverDeQuienEsTurno().actualizarFichasActuales(cant);
    }

    public void rondaColocarEjercitos(Jugador jugador,String nombrePais,int cant){
        this.tablero.agregarFichas(cant,jugador,nombrePais);
    }

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        return tablero.atacar(paisAtacante, paisDefensor, cantidad);//Else exception
    }

    /*public Jugador buscarJugador(String unNombreJugador) {
        return this.jugadores.get(unNombreJugador);
    }*/
    public void reagrupar(String paisUno,String paisdos,int cant){

    }

    public Turnos devolverTurnos(){
        return this.turnos;
    }

/*
    public void repartirPaises() {
        int indice = 0;

        for (CartaPais cartaPais : this.cartas) {
            (this.turnos.getJugadores()).get(indice).agregarCartaPais(cartaPais);
            this.tablero.agregarFichas(1,this.turnos.getJugadores().get(indice),(cartaPais.getPais()).getNombre());
            if (indice >= (this.turnos.getJugadores()).size()) indice = 0;
            if (indice>1) break;
            else indice++;
        }
    }*/
}
