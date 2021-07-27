package edu.fiuba.algo3.modelo.cartas;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public interface CartasPaisTeg {
    void asignarPaises(List<Jugador> jugadors);

    void darCartaA(Jugador jugador);

    void agregarCartasPais(List<CartaPais> cartas);
}
