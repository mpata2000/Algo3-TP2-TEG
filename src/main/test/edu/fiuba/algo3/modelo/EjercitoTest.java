package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void ejercitoDefensorConDosFichasTiraDosDados(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);
        Dados dados = ejercito.tirarDados();
        assertEquals(2,dados.cantidadDados());
    }

    @Test
    public void ejercitoDefensorConUnaFichaTiraUnDado(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(1);
        Dados dados = ejercito.tirarDados();
        assertEquals(1,dados.cantidadDados());
    }

    @Test
    public void ejercitoDefensorConCincoFichasTiraTresDados(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(5);
        Dados dados = ejercito.tirarDados();
        assertEquals(3,dados.cantidadDados());
    }

    @Test
    public void ejercitoAtacanteConUnaFichaTiraExcepcion(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(1);
        assertThrows(EjercitoConUnaFichaNoPuedeAtacar.class, () -> {
            Dados dados = ejercito.tirarDados(1);
        });
    }

    @Test
    public void ejercitoAtacanteConDosFichasPuedeTirarUnDado(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);
        Dados dados = ejercito.tirarDados(1);
        assertEquals(1,dados.cantidadDados());
    }

    @Test
    public void ejercitoAtacanteConCincoFichasPuedeTirarTresDados(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(5);
        Dados dados = ejercito.tirarDados(3);
        assertEquals(3,dados.cantidadDados());
    }

    @Test
    public void ejercitoAtacanteConCincoFichasPuedeTirarTresDadosAunqueSePidieronFIchasDeMas(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(5);
        Dados dados = ejercito.tirarDados(4);
        assertEquals(3,dados.cantidadDados());
    }

    @Test
    public void ejercitoAtacanteConDosFichasNoPuedeTirarDosDados(){
        Ejercito ejercito = new Ejercito();
        ejercito.agregarFichas(2);

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDados.class, () -> {
            Dados dados = ejercito.tirarDados(2);
        });

    }
}
