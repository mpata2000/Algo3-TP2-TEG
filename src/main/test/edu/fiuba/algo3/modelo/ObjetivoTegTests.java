package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.objetivos.ObjetivoConquista;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoDestruccion;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
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

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(false);
        objetivo.setDuenio(jugadorUno);

        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaNoSeCumpleSiNoTieneUnContineteConvariosContinentes(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa","Asia","Oceania","America"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(false);
        when(teg.continenteEsDeJugador("Asia",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Oceania",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("America",jugadorUno.getColor())).thenReturn(true);
        objetivo.setDuenio(jugadorUno);

        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaNoSeCumpleSiNoLosPaisesPorContinente(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(new ArrayList<>(), paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(0);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(0);
        objetivo.setDuenio(jugadorUno);

        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleSiTieneLosPaisesPorContinenteYNoNecesitaConquistarContienete(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(new ArrayList<>(), paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(5);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(5);
        objetivo.setDuenio(jugadorUno);

        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleSiTieneMasDeLosPaisesPorContinenteQueNecesita(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(new ArrayList<>(), paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(8);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(8);
        objetivo.setDuenio(jugadorUno);

        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSiJugadorTieneTrientaPaisesCompletoObjetivo(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(30);
        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoDestruccionSiJugadorTieneTrientaPaisesCompletoObjetivo(){
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Negro");
        ObjetivoTeg objetivo = new ObjetivoDestruccion("Rojo", List.of(jugadorDos, jugadorUno));
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(30);
        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleTieneElContinenteRequeridoYNoNecesitaPaisesPorContinente(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(true);
        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleTieneTodosLosContinenteRequeridoYNoNecesitaPaisesPorContinente(){
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa","Asia","Oceania","America"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Asia",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Oceania",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("America",jugadorUno.getColor())).thenReturn(true);

        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaSeCumpleTieneTodosLosPaisesPorContinenteRequeridoYLosContinentes(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa","Asia","Oceania","America"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Asia",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Oceania",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("America",jugadorUno.getColor())).thenReturn(true);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(8);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(8);

        objetivo.setDuenio(jugadorUno);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaNoSeCumpleTieneTodosLosPaisesContinenteRequeridoYNoTieneUnoDeLosContinentes(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa","Asia","Oceania","America"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(false);
        when(teg.continenteEsDeJugador("Asia",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Oceania",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("America",jugadorUno.getColor())).thenReturn(true);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(8);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(8);

        objetivo.setDuenio(jugadorUno);
        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoConquistaNoSeCumpleTieneTodosLosContinenteRequeridosYNoTieneUnoDeLosPaisesPorContinente(){
        paisesPorContinente.put("Asia",5);
        paisesPorContinente.put("Europa",5);
        ObjetivoTeg objetivo = new ObjetivoConquista(List.of("Africa","Asia","Oceania","America"),paisesPorContinente);
        Jugador jugadorUno = new Jugador("Rojo");
        Teg teg = Mockito.mock(Teg.class);

        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        when(teg.continenteEsDeJugador("Africa",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Asia",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("Oceania",jugadorUno.getColor())).thenReturn(true);
        when(teg.continenteEsDeJugador("America",jugadorUno.getColor())).thenReturn(true);
        when(teg.cantidadDePaisesJugadorEnContinente("Asia",jugadorUno.getColor())).thenReturn(3);
        when(teg.cantidadDePaisesJugadorEnContinente("Europa",jugadorUno.getColor())).thenReturn(8);

        objetivo.setDuenio(jugadorUno);
        assertFalse(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoDestruccionCumplido(){
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Negro");
        ObjetivoTeg objetivo = new ObjetivoDestruccion("Rojo", List.of(jugadorDos, jugadorUno));
        objetivo.setDuenio(jugadorDos);
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(0);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

    @Test
    public void objetivoDestruccionNoCumplido(){
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Negro");
        ObjetivoTeg objetivo = new ObjetivoDestruccion("Rojo", List.of(jugadorDos, jugadorUno));
        objetivo.setDuenio(jugadorDos);
        Teg teg = Mockito.mock(Teg.class);
        when(teg.cantidadDePaisesJugador(jugadorUno.getColor())).thenReturn(3);
        assertFalse(objetivo.cumplioObjetivo(teg));
    }



    @Test
    public void objetivoDestruccionSiElJugadorNoExisteSeSeteaElJugadorDelaDerecha(){
        Jugador jugadorUno = new Jugador("Rojo");
        Jugador jugadorDos = new Jugador("Negro");
        Jugador jugadorTres = new Jugador("Verde");
        Jugador jugadorCuatro = new Jugador("Azul");
        ObjetivoTeg objetivo = new ObjetivoDestruccion("Magenta", List.of(jugadorUno,jugadorDos,jugadorTres,jugadorCuatro));
        Teg teg = Mockito.mock(Teg.class);
        objetivo.setDuenio(jugadorDos); //Deberia destruir a jugadorTres
        when(teg.cantidadDePaisesJugador(jugadorTres.getColor())).thenReturn(1);
        assertFalse(objetivo.cumplioObjetivo(teg));
        when(teg.cantidadDePaisesJugador(jugadorTres.getColor())).thenReturn(0);
        assertTrue(objetivo.cumplioObjetivo(teg));
    }

}
