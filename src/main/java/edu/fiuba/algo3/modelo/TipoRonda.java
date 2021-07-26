package edu.fiuba.algo3.modelo;


public interface TipoRonda {

    TipoRonda cambiarDeRonda();

    boolean esColocacion();

    boolean esAtaque();

    boolean esReagrupacion();

    void inicializarRonda(String jugadorActual, Teg teg);
}
