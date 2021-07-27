package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.turnos.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TipoDeRondaTest {

    @Test
    public void enRondaDeColocacionNoSePuedeAtacar(){
        TipoRonda ronda = new RondaColocacion(List.of("A"));
        Teg teg = new Teg();
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRonda.class,()-> ronda.atacar(teg,"A","B",3));
    }

    @Test
    public void enRondaDeColocacionNoSePuedePasarFichas(){
        TipoRonda ronda = new RondaColocacion(List.of("A"));
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRonda.class,()-> ronda.pasarFichas(teg,"A","B",3));
    }

    @Test
    public void enRondaDeReagrupacionNoSePuedeAtacar(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRonda.class,()-> ronda.atacar(teg,"A","B",3));
    }

    @Test
    public void enRondaDeReagrupacionNoSePuedeColocar(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRonda.class,()-> ronda.colocarFichas(teg,"A",3));
    }

    @Test
    public void enRondaDeReagrupacionSinJugadoresSinguientesDevuelveRondaColocacionAlTerminarEtapa(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaColocacion);
    }


    @Test
    public void enRondaDeReagrupacionConMasJugadoresDevuelveRondaColocacionAlTerminarEtapa(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaAtaque);
    }

    @Test
    public void enRondaAtaqueTerminarEtapaDevuelveRondaReagrupacion(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaAtaque("B");
        Teg teg = new Teg();

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaReagrupacion);
    }
}
