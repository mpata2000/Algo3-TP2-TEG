package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DadosTest {

    @Test
    public void DadosSeCreaConUnDado(){
        Dados dados = new Dados(1);
        assertEquals(1,dados.cantidadDados());
    }

    @Test
    public void DadosSeCreaConTresDados(){
        Dados dados = new Dados(3);
        assertEquals(3,dados.cantidadDados());
    }


}
