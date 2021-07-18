package edu.fiuba.algo3.modelo;

public class Turnos() {
    private int turno = 0;
    private Jugador[] ronda = null;

    public boolean esTurnoDe(Jugador unJugador){
        return (this.ronda[this.turno] == unJugador);

    }

    public void avanzarRonda(){
        if (this.ronda[(this.turno)+1]) this.turno += 1;
        else this.turno = 0;
    }
}

