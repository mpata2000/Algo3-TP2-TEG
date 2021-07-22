package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
