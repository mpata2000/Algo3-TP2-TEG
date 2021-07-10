package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Ejercito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class EjercitoTest {

    @Test
    public void ejercitoNoTieneFichas(){
        Ejercito ejercito = new Ejercito();
        assertEquals(ejercito.getCantFichas(),0);

    }
    @Test
    public void colocacionDeEjercitosTest(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarTropas(2);
        assertEquals(ejercito.getCantFichas(),2);
    }

}
