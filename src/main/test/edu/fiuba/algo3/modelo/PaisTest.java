package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Pais;

import static org.junit.jupiter.api.Assertions.*;


// TODO: Colocación de ejércitos en los países.
// TODO: Ataque entre países con el país defensor como ganador.
// TODO: Ataque entre países con el país atacante como ganador y ocupación de este.
public class PaisTest{

     @Test
     public void colocacionDeEjercitoEnPaisTest(){
         Pais paisMio = new Pais("Chile");
         Jugador jugador = new Jugador("julio");
         paisMio.agregarTropas(5,jugador);
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

        paisMio.agregarTropas(5,jugadorUno);

        assertThrows(JugadorNoPoseePaisException.class, () -> {
            paisMio.agregarTropas(5,jugadorDos);
        });
    }

}