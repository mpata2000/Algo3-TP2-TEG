package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.EjercitoConUnaFichaNoPuedeAtacar;
import edu.fiuba.algo3.excepciones.EjercitoNoPuedeTirarEsaCantidadDeDados;
import edu.fiuba.algo3.excepciones.NoSePuedenCrearCeroDados;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BatallaTest {

    Pais paisAtacante;
    Pais paisDefensor;
    Jugador jugadorUno;
    Jugador jugadorDos;

    @BeforeEach
    void setUp() {
        paisAtacante = new Pais("Chile",Arrays.asList("Argentina","Peru"));
        paisDefensor = new Pais("Argentina", Arrays.asList("Chile","Brazil"));
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {

        paisAtacante.agregarFichas(3, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(NoSePuedenCrearCeroDados.class, () -> batalla.batallar(0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {

        paisAtacante.agregarFichas(1, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(EjercitoConUnaFichaNoPuedeAtacar.class, () -> batalla.batallar(1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {

        paisAtacante.agregarFichas(2, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDados.class, () -> batalla.batallar(3));
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

    @Test
    public void paisAtacanteConquistaPaisDefensor(){
        paisAtacante = Mockito.mock(Pais.class);
        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0,3};

        when(dados.comparadorDeDados(any(Dados.class))).thenReturn(conjunto);
        when(paisAtacante.tirarDados(3)).thenReturn(dados);
        when(paisAtacante.getJugador()).thenReturn(jugadorUno);
        when(paisAtacante.esAdyacente(paisDefensor)).thenReturn(true);
        paisDefensor.agregarFichas(3, jugadorDos);
        Batalla batalla = new Batalla(paisAtacante,paisDefensor);

        assertTrue(batalla.batallar(3));

        paisDefensor.agregarFichas(1,jugadorUno);
        assertTrue(paisDefensor.esDeJugador(jugadorUno));
    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {

        paisAtacante.agregarFichas(4, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorUno);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(AtaqueNoValido.class, () -> batalla.batallar(1));
    }

}
