package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JugadorTest {

    @Test
    public void siJugadorLeTocaUnaCartaQuePoseeelPaisGanaDosFichas(){
        Jugador jugador = new Jugador("Rojo");
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.agregarCartaPais(carta);
        assertEquals(3,pais.perderFichas(0));
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
