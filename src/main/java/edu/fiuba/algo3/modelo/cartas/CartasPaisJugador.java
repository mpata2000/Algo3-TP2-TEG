package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

public interface CartasPaisJugador {
    boolean canjeDeCartas(Jugador jugador, CartasPaisTeg cartasPaisTeg);

    void activarCartas(Jugador unJugador);

    void agregarCartaPais(CartaPais carta);
}
