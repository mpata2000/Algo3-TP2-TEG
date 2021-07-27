package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public interface TipoColocacion {
    void colocarFichas(String unJugador, Teg teg);

    TipoRonda pasarDeRonda(List<String> jugadores);
}
