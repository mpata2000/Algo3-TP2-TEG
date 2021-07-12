package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Collections;

public class BatallaTest {


    @Test
    public void paisAtacanteNoConquistaPais() {


        Pais chile = new Pais("Chile");
        Pais argentina = new Pais("Argentina");

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        chile.agregarFichas(5, jugadorUno);
        argentina.agregarFichas(5, jugadorDos);

        Batalla batalla = new Batalla(chile, argentina);


        assertFalse(batalla.batallar(2));
    }


    //TODO: mockito de pais
}
