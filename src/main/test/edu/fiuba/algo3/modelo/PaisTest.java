package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// TODO: Colocación de ejércitos en los países.
// TODO: Ataque entre países con el país atacante como ganador y ocupación de este.
public class PaisTest{

     @Test
     public void colocacionDeEjercitoEnPaisTest(){
         Pais paisMio = new Pais("Chile");
         Jugador jugador = new Jugador("julio");
         paisMio.agregarFichas(5,jugador);
         assertEquals(paisMio.getJugador(),jugador);
     }

    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("1a");
        });

        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void agregarTropasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        Pais paisMio = new Pais("Chile");
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisMio.agregarFichas(5,jugadorUno);

        assertThrows(JugadorNoPoseePaisException.class, () -> {
            paisMio.agregarFichas(5,jugadorDos);
        });
    }

    // TODO: Ataque entre países con el país defensor como ganador.
    @Test
    public void paisatacanteNoConquistaPaisDefensor(){
        Pais paisAtacante = new Pais("Chile");
        Pais paisDefensor = new Pais("Algo");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);

        assertFalse(paisDefensor.perderFichas(2,paisAtacante));

    }

    @Test
    public void paisAtacanteConquistaPaisYNoCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile");
        Pais paisDefensor = new Pais("Algo");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);
        paisDefensor.perderFichas(2,paisAtacante);
        assertTrue(paisDefensor.esDeJugador(jugadorDos));

    }

    @Test
    public void paisAtacanteConquistaPais(){
        Pais paisAtacante = new Pais("Chile");
        Pais paisDefensor = new Pais("Algo");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);

        assertTrue(paisDefensor.perderFichas(3,paisAtacante));

    }

    @Test
    public void paisAtacanteConquistaPaisYCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile");
        Pais paisDefensor = new Pais("Algo");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);
        paisDefensor.perderFichas(3,paisAtacante);
        assertTrue(paisDefensor.esDeJugador(jugadorUno));

    }

}