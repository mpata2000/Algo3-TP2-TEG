package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TableroTest {

    @Test
    public void agregarFichasAUnPaisVacioEntoncesElPaisEsDelJugadorTest(){
        Tablero tablero = new Tablero();
        Pais pais = new Pais("Argentina");
        tablero.agregarPais(pais);

        Jugador jugador = new Jugador("Julian");
        tablero.agregarFichas(5,jugador, "Argentina");

        assertTrue(pais.esDeJugador(jugador));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        Tablero tablero = new Tablero();
        Pais pais = new Pais("Argentina");

        tablero.agregarPais(pais);

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");

        tablero.agregarFichas(5,jugadorUno, "Argentina");

        assertThrows(JugadorNoPoseePaisException.class, () -> {
            pais.agregarFichas(5,jugadorDos);
        });
    }


}
