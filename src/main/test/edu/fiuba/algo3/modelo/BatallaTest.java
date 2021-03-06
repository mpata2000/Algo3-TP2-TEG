package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.AtaqueNoValidoException;
import edu.fiuba.algo3.modelo.excepciones.EjercitoConUnaFichaNoPuedeAtacarException;
import edu.fiuba.algo3.modelo.excepciones.EjercitoNoPuedeTirarEsaCantidadDeDadosException;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedenCrearCeroDadosException;
import edu.fiuba.algo3.modelo.ataque.Batalla;
import edu.fiuba.algo3.modelo.ataque.Dados;
import edu.fiuba.algo3.modelo.tablero.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
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
        paisAtacante = new Pais("Chile", List.of("Argentina","Peru"));
        paisDefensor = new Pais("Argentina", List.of("Chile","Brazil"));
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
        jugadorUno.agregarFichas(20);
        jugadorDos.agregarFichas(20);
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paisAtacante.agregarFichas(2, jugadorUno);
        paisDefensor.agregarFichas(4, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(NoSePuedenCrearCeroDadosException.class, () -> batalla.batallar(0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);

        paisDefensor.agregarFichas(4, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(EjercitoConUnaFichaNoPuedeAtacarException.class, () -> batalla.batallar(1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paisAtacante.agregarFichas(1, jugadorUno);
        paisDefensor.agregarFichas(4, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDadosException.class, () -> batalla.batallar(3));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paisAtacante.agregarFichas(5, jugadorUno);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertFalse(batalla.batallar(2));
    }

    @Test
    public void paisAtacanteConquistaPaisDefensor(){
        paisDefensor.asignarJugador(jugadorDos);
        paisAtacante = Mockito.mock(Pais.class);
        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0,3};

        when(dados.compararDados(any(Dados.class))).thenReturn(conjunto);
        when(paisAtacante.tirarDados(3)).thenReturn(dados);
        when(paisAtacante.getJugador()).thenReturn(jugadorUno);
        when(paisAtacante.esAdyacente(paisDefensor)).thenReturn(true);
        paisDefensor.agregarFichas(2, jugadorDos);
        Batalla batalla = new Batalla(paisAtacante,paisDefensor);

        assertTrue(batalla.batallar(3));

        paisDefensor.agregarFichas(1,jugadorUno);
        assertTrue(paisDefensor.esDeJugador(jugadorUno));
    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.agregarFichas(3, jugadorUno);
        paisDefensor.agregarFichas(4, jugadorUno);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);
        assertThrows(AtaqueNoValidoException.class, () -> batalla.batallar(1));
    }

    @Test
    public void PaisesQueNoSonAdyacentesNoPuedenBatallar() {
        paisAtacante.asignarJugador(jugadorUno);
        paisAtacante.agregarFichas(4, jugadorUno);
        //Las adyacencias del defensor no importan
        paisDefensor = new Pais("Borneo", List.of("Australia","Sumatra"));
        paisDefensor.asignarJugador(jugadorDos);
        paisDefensor.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(paisAtacante, paisDefensor);

        assertThrows(AtaqueNoValidoException.class, () -> batalla.batallar(1));
    }

}
