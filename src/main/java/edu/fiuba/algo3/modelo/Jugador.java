package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.io.*;

public class Jugador {
    private Ejercito ejercito;
    private Tablero tablero;
    private String nombre;
    private int fichasIniciales ;

    public Jugador(String nombre){
        this.nombre = nombre;
        this.fichasIniciales = 8;
    }
    public void actualizarFichasActuales(int cant){
        if(cant > this.fichasIniciales){return;}
        this.fichasIniciales = this.fichasIniciales-cant;
    }
    public int devolverFichas(){
        return this.fichasIniciales;
    }

    public void agregarFichaInicial(String nombrePais, Turnos turnos){
        this.tablero.agregarFichas(1,this,nombrePais,turnos);
    }
    public void agregarCartaPais(CartaPais carta){

    }
    public boolean esElMismoJugador(Jugador jugador){
        return this == jugador;
    }

}
