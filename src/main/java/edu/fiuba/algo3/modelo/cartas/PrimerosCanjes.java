package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

public class PrimerosCanjes implements Canje {

    private int fichas;

    PrimerosCanjes(){
        this.fichas = 4;
    }

    PrimerosCanjes(int fichas){
        this.fichas = fichas+3;
    }
    /*
     * Recive un jugador le da las fichas del canje
     * Devuelve el proximo canje
     */
    @Override
    public Canje hacerCanje(Jugador unJugador){
        unJugador.agregarFichas(fichas);
        if(fichas < 10){
            return new PrimerosCanjes(fichas);
        }
        return new EnesimoCanje(fichas);
    }
}
