package edu.fiuba.algo3.modelo;

public abstract class ObjetivoTeg {

    public boolean cumplioObjetivo(Teg teg,Jugador jugador){
        if(teg.cantidadDePaisesJugador(jugador) > 29){
            return true;
        }
        return this.objetivoJugador(teg,jugador);
    }


    protected abstract boolean objetivoJugador(Teg teg, Jugador jugador);
}
