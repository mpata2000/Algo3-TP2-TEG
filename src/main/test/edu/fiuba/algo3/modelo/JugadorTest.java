package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void siJugadorNoConquistaPaisNoPuedeRecibirCarta(){
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);

        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void siJugadorConquistaPaisYReciveCarta(){
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorSabeQueEsElMismo(){
        Jugador jugador = new Jugador("Rojo");
        assertTrue(jugador.esElMismoJugador(jugador));
    }

    @Test
    public void jugadorSeLeAgreganFichasYTieneFichasRestantes(){
        Jugador jugador = new Jugador("Rojo");
        jugador.agregarFichas(4);
        assertTrue(jugador.tieneFichas());
    }
}
