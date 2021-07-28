package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataque.AtaqueNoValido;
import edu.fiuba.algo3.modelo.ataque.EjercitoConUnaFichaNoPuedeAtacarException;
import edu.fiuba.algo3.modelo.ataque.EjercitoNoPuedeTirarEsaCantidadDeDadosException;
import edu.fiuba.algo3.modelo.ataque.NoSePuedenCrearCeroDadosException;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.tablero.JugadorNoPoseePaisException;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class TableroTest {

    Map<String, Jugador> jugadores = new HashMap<>();
    Jugador jugadorUno;
    Jugador jugadorDos;
    Pais paisAtacante;
    Pais paisDefensor;
    ArrayList<Pais> paises = new ArrayList<>();

    @BeforeEach
    void setUp() {
        paisAtacante = new Pais("Chile", List.of("Argentina", "Brazil"));
        paisDefensor = new Pais("Argentina", List.of("Chile", "Brazil"));
        jugadorUno = new Jugador("Amarillo");
        jugadorDos = new Jugador("Rojo");
        jugadorUno.agregarFichas(10);
        jugadorDos.agregarFichas(10);
        jugadores.put("Amarillo", jugadorUno);
        jugadores.put("Rojo", jugadorDos);

    }

    @Test
    public void seObtieneElPaisesperadoDetablero() {
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertEquals(paisAtacante, tablero.getPais(paisAtacante.getNombre()));
    }


    @Test
    public void tableroAgregaCincoFichasAPaisDeJugadorQuedaConSeis() {
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas(5, jugadorUno, "Chile");

        assertEquals(6, paisAtacante.perderFichas(0));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest() {
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5, jugadorDos, "Chile"));
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        tablero.agregarFichas(3, jugadorUno, "Argentina");
        tablero.agregarFichas(5, jugadorDos, "Chile");

        assertThrows(NoSePuedenCrearCeroDadosException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        tablero.agregarFichas(5, jugadorDos, "Chile");

        assertThrows(EjercitoConUnaFichaNoPuedeAtacarException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        tablero.agregarFichas(1, jugadorUno, "Argentina");
        tablero.agregarFichas(4, jugadorDos, "Chile");

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDadosException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 3));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas(5, jugadorUno, "Argentina");
        tablero.agregarFichas(5, jugadorDos, "Chile");

        assertFalse(tablero.atacar(jugadorUno, "Argentina", "Chile", 2));

    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas(4, jugadorUno, "Argentina");


        assertThrows(AtaqueNoValido.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 1));
    }

    @Test
    public void sePuedePasarDosFichasAUnPaisLimitrofeTeniendocuatroFichas() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas(3, jugadorUno, "Argentina");

        assertEquals(4, paisDefensor.perderFichas(0));
        tablero.pasarFichas(jugadorUno,"Argentina", "Chile", 2);

        assertEquals(2, paisDefensor.perderFichas(0));
        assertEquals(3, paisAtacante.perderFichas(0));

    }

    @Test
    public void aJugadorSinPaisesSeLeAgregaTresFichas() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);
        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);
        assertEquals(3, jugadorUno.sacarFichas(0));
    }


    //Todo: Test de cuantas fichas le da a jugador

    @Test
    public void jugadorConUnContienteSeLeAgregaFichasExtrasCorrectas(){
        paisAtacante.asignarJugador(jugadorUno);
        Continente continenteUno = new Continente("A",7, List.of(paisAtacante));
        Tablero tablero = new Tablero(List.of(continenteUno), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        // 3 fichas del minimo por pais + 7 del contiente
        assertEquals(10, jugadorUno.sacarFichas(0));
    }

    @Test
    public void jugadorConDosContientesSeLeAgregaFichasExtras(){
        paisAtacante.asignarJugador(jugadorUno);
        Continente continenteUno = new Continente("A",1, List.of(paisAtacante));
        Continente continenteDos = new Continente("B",1, List.of(paisAtacante));
        Tablero tablero = new Tablero(List.of(continenteUno,continenteDos), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        // 3 fichas del minimo por pais + 2 una por cada contiente
        assertEquals(5, jugadorUno.sacarFichas(0));
    }
}