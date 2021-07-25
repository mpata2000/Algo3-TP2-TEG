package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.JugadorNoPoseePaisException;

public class CartaPais {
    private final String nombrePais;
    private final String simbolo;
    private Pais pais;
    private boolean activada;

    CartaPais(String nombrePais,String simbolo){
        this.nombrePais = nombrePais;
        this.simbolo = simbolo;
        this.activada = false;
    }

    CartaPais(String nombrePais,String simbolo,Pais unPais){
        this.nombrePais = nombrePais;
        this.simbolo = simbolo;
        this.pais = unPais;
        this.activada = false;
    }

    public void asignarPaisA(Jugador unJugador){
        this.pais.asignarJugador(unJugador);
    }

    public void setPais(Tablero tablero) {
        this.pais = tablero.getPais(this.nombrePais);
    }

    public Pais getPais() {
        return this.pais;
    }

    public String getNombrePais() {
        return this.nombrePais;
    }

    public String getSimbolo() {
        return this.simbolo;
    }

    public boolean esSimbolo(String simbolo) {
        return (this.simbolo.equalsIgnoreCase(simbolo) || this.simbolo.equalsIgnoreCase("Comodin"));
    }

    public void activarCarta(Jugador unJugador){
        if(!this.activada &&  this.pais.esDeJugador(unJugador)) {
            unJugador.agregarFichas(2);
            this.pais.agregarFichas(2, unJugador);
            this.activada = true;
        }
    }
}
