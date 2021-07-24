package edu.fiuba.algo3.modelo;

import java.util.List;

public interface TipoRonda {

    TipoRonda cambiarDeRonda();

    boolean esColocacion();

    boolean esAtaque();

    void inicializarRonda(List<String> jugadores, Teg teg);
}
