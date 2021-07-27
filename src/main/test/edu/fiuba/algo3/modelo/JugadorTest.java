package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JugadorTest {

    Jugador jugador;

    @BeforeEach
    void setUp(){
        jugador = new Jugador("Rojo");
    }

    @Test
    public void siJugadorNoConquistaPaisNoPuedeRecibirCarta(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);

        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorConquistaPaisYReciveCartaPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorNoConquistaPaisNoReciveCartaPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorConquistaPaisNoReciveDosCartasPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(carta));
        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorSabeQueEsElMismo(){
        assertTrue(jugador.esElMismoJugador(jugador));
    }

    @Test
    public void jugadoresDistintosNoSonElMismoJugador(){
        assertFalse(jugador.esElMismoJugador(new Jugador("Magenta")));
    }

    @Test
    public void jugadorSeCreaSinFichasRestantes(){
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganFichasYTieneFichasRestantes(){
        jugador.agregarFichas(4);
        assertTrue(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanDosQuedaConOcho(){
        jugador.agregarFichas(10);
        assertEquals(8,jugador.sacarFichas(2));
        assertTrue(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanDiezSinFichas(){
        jugador.agregarFichas(10);
        assertEquals(0,jugador.sacarFichas(10));
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanVeinteSinFichas(){
        jugador.agregarFichas(10);
        assertEquals(0,jugador.sacarFichas(20));
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorConTresFichasNoPuedePonerCuatroFichas(){
        jugador.agregarFichas(3);
        assertFalse(jugador.puedeColocarFichas(4));
    }

    @Test
    public void jugadorConSieteFichasPuedePonerSieteFichas(){
        jugador.agregarFichas(7);
        assertTrue(jugador.puedeColocarFichas(7));
    }

    @Test
    public void siJugadorCumplioObjetivoGano(){
        ObjetivoTeg objetivo = Mockito.mock(ObjetivoTeg.class);
        Teg teg = new Teg();
        when(objetivo.cumplioObjetivo(teg,jugador)).thenReturn(true);
        jugador.darObjetivo(objetivo);
        assertTrue(jugador.gano(teg));
    }

    @Test
    public void siJugadorNoCumplioObjetivoNoGano(){
        ObjetivoTeg objetivo = Mockito.mock(ObjetivoTeg.class);
        Teg teg = new Teg();
        when(objetivo.cumplioObjetivo(teg,jugador)).thenReturn(false);
        jugador.darObjetivo(objetivo);
        assertFalse(jugador.gano(teg));
    }
}
