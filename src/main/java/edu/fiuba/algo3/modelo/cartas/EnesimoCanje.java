package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

public class EnesimoCanje implements Canje {
    private final int fichas;

    public EnesimoCanje(int fichas) {
        this.fichas = fichas+5;
    }

    /*
     * Recive un jugador le da las fichas del canje
     * Devuelve el proximo canje
     */
    @Override
    public Canje hacerCanje(Jugador unJugador){
        unJugador.agregarFichas(fichas);
        return new EnesimoCanje(fichas);
    }
}
