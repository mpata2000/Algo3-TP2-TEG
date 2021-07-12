package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;

public class BatallaTest {

    Pais paisAtacante;
    Pais paisDefensor;
    Jugador jugadorUno;
    Jugador jugadorDos;

    @BeforeEach
    void setUp() {
        paisAtacante = new Pais("Chile");
        paisDefensor = new Pais("Argentina");
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
    }

    @Test
    public void paisAtacanteNoConquistaPais() {

        paisAtacante.agregarFichas(5, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertFalse(batalla.batallar(2));
    }
    //Ataque entre países con el país defensor como ganador.
    //Ataque entre países con el país atacante como ganador y ocupación de este.

    //TODO: mockito de pais

   /* @Test
    public void jj(){
        paisAtacante = Mockito.mock(Pais.class);
        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0,3};

        when(dados.comparaDados(dadosDefensor)).thenReturn(conjunto);
        when(paisAtacante.tirarDados(3)).thenReturn(dados);
        when(paisAtacante.getJugador()).thenReturn(jugadorUno);
        paisDefensor.agregarFichas(3, jugadorDos);
        Batalla batalla = new Batalla(paisAtacante,paisDefensor);
        assertTrue(batalla.batallar(3));
        paisDefensor.agregarFichas(1,jugadorUno);
    }
   */
}
