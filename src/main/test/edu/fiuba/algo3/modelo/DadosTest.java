package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DadosTest {

    @Test
    public void dadosSeCreaConUnDado(){
        Dados dados = new Dados(1);
        assertEquals(1,dados.cantidadDados());
    }

    @Test
    public void dadosSeCreaConTresDados(){
        Dados dados = new Dados(3);
        assertEquals(3,dados.cantidadDados());
    }

    @Test
    public void crearDadosConCeroDadosTiraExcepcionNoSePuedenCrearCeroDados(){

        assertThrows(NoSePuedenCrearCeroDados.class, () -> {
            Dados dados = new Dados(0);
        });
    }
}
