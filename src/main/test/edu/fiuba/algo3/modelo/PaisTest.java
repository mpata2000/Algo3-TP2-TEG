package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



public class PaisTest{

     @Test
     public void colocacionDeEjercitoEnPaisTest(){
         Pais paisMio = new Pais("Chile");
         Jugador jugador = new Jugador("julio");
         paisMio.agregarFichas(5,jugador);

         assertEquals(paisMio.getJugador(),jugador);
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

    @Test
    public void paisConCincoFichasPuedeTirarUnDado(){
        Pais paisMio = new Pais("Chile");
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(5,jugador);

        Dados dados = paisMio.tirarDados(1);

        assertEquals(1,dados.cantidadDados());

    }

    @Test
    public void paisConUnaFichaTirarUnDado(){
        Pais paisMio = new Pais("Chile");
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(1,jugador);

        Dados dados = paisMio.tirarDados();

        assertEquals(1,dados.cantidadDados());

    }


    @Test
    public void paisConOnceFichasPuedeTirarComoMaximoTresDados(){
        Pais paisMio = new Pais("Chile");
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(11,jugador);

        Dados dados = paisMio.tirarDados();

        assertEquals(3,dados.cantidadDados());

    }

    @Test
    public void paisConOnceFichasPuedeTirarComoMaximoTresDadosAunqueSePidanDadosDeMas(){
        Pais paisMio = new Pais("Chile");
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(11,jugador);

        Dados dados = paisMio.tirarDados(6);

        assertEquals(3,dados.cantidadDados());

    }

}