package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TegTest {
/*
   @Test
    public void comenzarJuego(){
        Teg teg = new Teg();
        teg.comenzarJuego(2);
        teg.rondaInicialColocarEjercitos("Chile",5);
        teg.rondaInicialColocarEjercitos("Chile",3);
        assertThrows(NoEsTuTurno.class, () -> teg.rondaInicialColocarEjercitos("Chile",3));

    }*/

    @Test
    public void comenzarJuego(){
        Teg teg = new Teg();
        teg.comenzarJuego(1);
        RondaColocacionInicial  rondaColocacionInicial =  new RondaColocacionInicial();
        Turnos turnos = teg.devolverTurnos();
        turnos.colocarEjercitosEnRondaInicial("Argentina",5);
        turnos.colocarEjercitosEnRondaInicial("Argentina",3);
        assertFalse(turnos.devolverRondaActual()==rondaColocacionInicial);
    }

    @Test
    public void ColocarEjercitos2Paises(){
        Teg teg = new Teg();
        teg.comenzarJuego(1);
        RondaColocacion  rondaColocacion =  new RondaColocacion();
        Turnos turnos = teg.devolverTurnos();
        turnos.colocarEjercitosEnRondaInicial("Argentina",5);
        turnos.colocarEjercitosEnRondaInicial("Argentina",3);
        turnos.colocarEjercitosEnRondaInicial("Chile",3);
        turnos.finAtaque();
        turnos.colocarEjercitos("Argentina",5);
        assertEquals(turnos.devolverRondaActual(),rondaColocacion);
    }
}
