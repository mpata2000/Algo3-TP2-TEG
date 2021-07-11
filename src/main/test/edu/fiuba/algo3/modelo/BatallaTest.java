package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;

public class BatallaTest {


    @Test
    public void paisAtacanteNoGanaBatalla() {

        Pais chile = new Pais("Chile");
        Pais argentina = new Pais("Argentina");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        chile.agregarFichas(3, jugadorUno);
        argentina.agregarFichas(2, jugadorDos);

        Batalla batalla = new Batalla(chile, argentina);

        ArrayList<Integer> dadosAtacante = new ArrayList<Integer>();
        ArrayList<Integer> dadosDefensor = new ArrayList<Integer>();

        dadosAtacante.add(6);
        dadosAtacante.add(3);

        dadosDefensor.add(5);
        dadosDefensor.add(4);

        assertFalse(batalla.batallar(dadosAtacante, dadosDefensor));
    }
}
