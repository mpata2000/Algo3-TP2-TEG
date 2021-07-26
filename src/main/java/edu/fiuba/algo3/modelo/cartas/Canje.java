package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

public interface Canje {

    /*
    * Recive un jugador le da las fichas del canje
    * Devuelve el proximo canje
     */
    Canje hacerCanje(Jugador unJugador);

}
