package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class EjercitoTest {

    @Test
    public void ejercitoNoTieneFichasTest(){
        Ejercito ejercito = new Ejercito();
        assertEquals(0,ejercito.getCantFichas());

    }

    @Test
    public void colocacionDeEjercitosTest(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);
        assertEquals(2,ejercito.getCantFichas());
    }

    @Test
    public void ejercitosPierdeLaCantidadDeFichasCorrectas(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);
        ejercito.perderFichas(1);
        assertEquals(1,ejercito.getCantFichas());
    }

    @Test
    public void ejercitosPierdeLaYDevuelveLasFichasCorrectas(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);
        assertEquals(1,ejercito.perderFichas(1));
    }
}
