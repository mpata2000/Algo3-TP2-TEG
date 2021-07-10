package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import edu.fiuba.algo3.modelo.Pais;

public class PaisTest{
    /**
    @Test
    public void paisSeCreaConNombreCorrecto() {
        Pais paisMio = new Pais("Chile");
        assertEquals(paisMio.nombre(),"Chile");
    }
    @Test
    public void colocacionDeEjercitoEnPais(){
        Pais paisMio = new Pais("Chile");
        Ejercito ejercitoMagenta = new Ejercito();
        paisMio.setearEjercito(ejercitoMagenta);
        paisMio.agregarTropas(5);
        assert(paisMio.esDelJugador(ejercitoMagenta));
    }
    @Test
    public void colocacionDeEjercitoEnPais2(){
        Pais paisMio = new Pais("Alemania");
        Ejercito ejercitoVerde = new Ejercito();
        Ejercito ejercitoMagenta = new Ejercito();
        paisMio.agregarTropas(3);
        assertFalse(paisMio.esDelJugador(ejercitoMagenta));
    }
    @Test
    public void colocacionDeEjercitoEnPais3(){
        Pais paisMio = new Pais("Alemania");
        Jugador jugador = new Jugador();
        Ejercito ejercitoVerde = new Ejercito();
        jugador.setearEjercito(ejercitoVerde);
        paisMio.agregarTropas(3);
    }

    @Test
    public void testt(){
        Jugador jugador = new Jugador();
        Mapa mapa = new Mapa();
        CartaPais carta = new CartaPais("Chile");
        carta.setearPais(jugador);
        carta.devolverPais()

    }**/
     @Test
     public void colocacionDeEjercitoEnPais(){
         Pais paisMio = new Pais("Chile");
         Jugador jugador = new Jugador("julio");
         paisMio.agregarTropas(5,jugador);
         assertEquals(paisMio.getJugador(),jugador);
     }

    @Test
    public void devolverTropasPais(){
        Pais paisMio = new Pais("Chile");
        Jugador jugador = new Jugador("julian");
        Jugador jugadorDos = new Jugador("sofi");
        paisMio.agregarTropas(5,jugador);
        paisMio.agregarTropas(5,jugadorDos);
        assertEquals(paisMio.getJugador(),jugador);
    }

    /**Colocación de ejércitos en los países.
    Ataque entre países con el país defensor como ganador.
    Ataque entre países con el país atacante como ganador y ocupación de este.
**/




}