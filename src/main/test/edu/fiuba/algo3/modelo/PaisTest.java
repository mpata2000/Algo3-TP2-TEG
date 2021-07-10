package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.fiuba.algo3.modelo.Pais;


// TODO: Colocación de ejércitos en los países.
// TODO: Ataque entre países con el país defensor como ganador.
// TODO: Ataque entre países con el país atacante como ganador y ocupación de este.
class PaisTest{

     @Test
     void colocacionDeEjercitoEnPaisTest(){
         Pais paisMio = new Pais("Chile");
         Jugador jugador = new Jugador("julio");
         paisMio.agregarTropas(5,jugador);
         assertEquals(paisMio.getJugador(),jugador);
     }

    @Test
    void devolverTropasPaisTest(){
        Pais paisMio = new Pais("Chile");
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisMio.agregarTropas(5,jugadorUno);
        paisMio.agregarTropas(5,jugadorDos);

        assertEquals(paisMio.getJugador(),jugadorUno);
    }
}