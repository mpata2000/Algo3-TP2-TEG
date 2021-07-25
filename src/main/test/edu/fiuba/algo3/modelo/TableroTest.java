package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TableroTest {

    Map<String, Jugador> jugadores = new HashMap<>();
    Jugador jugadorUno;
    Jugador jugadorDos;
    Pais paisAtacante;
    Pais paisDefensor;
    ArrayList<Pais> paises = new ArrayList<>();

    @BeforeEach
    void setUp() {
            paisAtacante = new Pais("Chile", List.of("Argentina","Brazil"));
            paisDefensor =  new Pais("Argentina", List.of("Chile","Brazil"));
            jugadorUno = new Jugador("Amarillo");
            jugadorDos = new Jugador("Rojo");
            jugadorUno.agregarFichas(10);
            jugadorDos.agregarFichas(10);
            jugadores.put("Amarillo",jugadorUno);
            jugadores.put("Rojo",jugadorDos);

    }


    @Test
    public void agregarFichasAUnPaisVacioEntoncesElPaisEsDelJugadorTest(){
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        tablero.agregarFichas(5,jugadorUno, "Chile");

        assertTrue(paisAtacante.esDeJugador(jugadorUno));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5,jugadorDos, "Chile"));
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);


        tablero.agregarFichas(3, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertThrows(NoSePuedenCrearCeroDados.class, () -> tablero.atacar(jugadorUno,"Argentina","Chile",0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);


        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertThrows(EjercitoConUnaFichaNoPuedeAtacar.class, () -> tablero.atacar(jugadorUno,"Argentina","Chile",1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);


        tablero.agregarFichas(1, jugadorUno,"Argentina");
        tablero.agregarFichas(4, jugadorDos,"Chile");

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDados.class, () -> tablero.atacar(jugadorUno,"Argentina","Chile",3));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        tablero.agregarFichas(5, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertFalse(tablero.atacar(jugadorUno,"Argentina","Chile",2));

    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        tablero.agregarFichas(4, jugadorUno,"Argentina");


        assertThrows(AtaqueNoValido.class, () -> tablero.atacar(jugadorUno,"Argentina","Chile",1));

    }
}