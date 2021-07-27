package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.objetivos.ObjetivoConquista;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class ObjetivoTegTests {
    HashMap<String,Integer> paisesPorContinente;

    @BeforeEach
    void setUp(){
        paisesPorContinente = new HashMap<>();
    }

    @Test
    public void objetivoConquistaNoSeCumpleSiNoTieneUnContinete(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno)).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno)).thenReturn(false);
        objetivo.setDuenio(jugadorUno);
        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSiJugadorTieneTrientaPaisesCompletoObjetivo(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno)).thenReturn(30);
        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleTieneElContinenteRequerido(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno)).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno)).thenReturn(true);
        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }
}
