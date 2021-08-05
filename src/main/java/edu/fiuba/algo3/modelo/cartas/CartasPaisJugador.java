package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public interface CartasPaisJugador {
    boolean canjeDeCartas(Jugador jugador, CartasPaisTeg cartasPaisTeg);

    void activarCartas(Jugador unJugador);

    void agregarCartaPais(CartaPais carta);

    List<String> getCartas();
}
