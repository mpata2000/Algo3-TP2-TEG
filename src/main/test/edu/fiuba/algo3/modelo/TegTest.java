package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TegTest {



    @Test
    public void comenzarJuego(){

        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitosEnRondaInicial("Argentina",5);
        turnos.colocarEjercitosEnRondaInicial("Argentina",3);
        assertTrue(turnos.devolverRondaActual().esAtaqueReagrupacion());
    }

    @Test
    public void ColocarEjercitos2Paises(){
        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitosEnRondaInicial("Argentina",5);
        turnos.colocarEjercitosEnRondaInicial("Argentina",3);
        turnos.finAtaque();
        assertTrue(turnos.devolverRondaActual().esColocacion());
    }
}
