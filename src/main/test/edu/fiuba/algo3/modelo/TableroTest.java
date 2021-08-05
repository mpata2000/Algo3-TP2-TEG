package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ataque.AtaqueNoValidoException;
import edu.fiuba.algo3.modelo.ataque.EjercitoConUnaFichaNoPuedeAtacarException;
import edu.fiuba.algo3.modelo.ataque.EjercitoNoPuedeTirarEsaCantidadDeDadosException;
import edu.fiuba.algo3.modelo.ataque.NoSePuedenCrearCeroDadosException;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.tablero.JugadorNoPoseePaisException;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TableroTest {

    Map<String, Jugador> jugadores = new HashMap<>();
    Jugador jugadorUno;
    Jugador jugadorDos;
    Pais paisDefensor;
    Pais paisAtacante;
    ArrayList<Pais> paises = new ArrayList<>();

    @BeforeEach
    void setUp() {
        paisDefensor = new Pais("Chile", List.of("Argentina", "Brazil"));
        paisAtacante = new Pais("Argentina", List.of("Chile", "Brazil"));
        jugadorUno = new Jugador("Amarillo");
        jugadorDos = new Jugador("Rojo");
        jugadorUno.agregarFichas(10);
        jugadorDos.agregarFichas(10);
        jugadores.put("Amarillo", jugadorUno);
        jugadores.put("Rojo", jugadorDos);

    }

    @Test
    public void seObtieneElPaisesperadoDetablero() {
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertEquals(paisDefensor, tablero.getPais(paisDefensor.getNombre()));
    }


    @Test
    public void tableroAgregaCincoFichasAPaisDeJugadorQuedaConSeis() {
        paises.add(paisDefensor);
        paisDefensor.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas("Chile", jugadorUno, 5);

        assertEquals(6, paisDefensor.perderFichas(0));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest() {
        paises.add(paisDefensor);
        paisDefensor.asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas("Chile", jugadorDos, 5));
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        tablero.agregarFichas("Argentina", jugadorUno, 3);
        tablero.agregarFichas("Chile", jugadorDos, 5);

        assertThrows(NoSePuedenCrearCeroDadosException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        tablero.agregarFichas("Chile", jugadorDos, 5);

        assertThrows(EjercitoConUnaFichaNoPuedeAtacarException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        tablero.agregarFichas("Argentina", jugadorUno, 1);
        tablero.agregarFichas("Chile", jugadorDos, 4);

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDadosException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 3));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas("Argentina", jugadorUno, 5);
        tablero.agregarFichas("Chile", jugadorDos, 5);

        assertFalse(tablero.atacar(jugadorUno, "Argentina", "Chile", 2));

    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas("Argentina", jugadorUno, 4);


        assertThrows(AtaqueNoValidoException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 1));
    }

    @Test
    public void JugadorNoPuedeAtacarConUnPaisQueNoEsSuyo() {
        paisDefensor.asignarJugador(new Jugador("A"));
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.atacar(jugadorUno, "Argentina", "Chile", 1));
    }

    @Test
    public void sePuedePasarDosFichasAUnPaisLimitrofeTeniendocuatroFichas() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        tablero.agregarFichas("Argentina", jugadorUno, 3);

        assertEquals(4, paisAtacante.perderFichas(0));
        tablero.pasarFichas(jugadorUno,"Argentina", "Chile", 2);

        assertEquals(2, paisAtacante.perderFichas(0));
        assertEquals(3, paisDefensor.perderFichas(0));

    }

    @Test
    public void pasarFichasDeUnPaisQueNoEsDelJudoriraExcepcion() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.pasarFichas(jugadorUno,"Argentina", "Chile", 2));

    }

    @Test
    public void aJugadorSinPaisesSeLeAgregaTresFichas() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        Tablero tablero = new Tablero(new ArrayList<>(), paises);
        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);
        assertEquals(3, jugadorUno.sacarFichas(0));
    }

    @Test
    public void tableroDevuelveQueJugadorTieneSeisPaises() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));

        for (Pais pais: paises){
            pais.asignarJugador(jugadorUno);
        }
        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertEquals(6,tablero.cantidadDePaisesJugador(jugadorUno));
    }

    @Test
    public void tableroDevuelveQueJugadorTieneCeroPaisesSiNotienePaises() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));

        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertEquals(0,tablero.cantidadDePaisesJugador(jugadorUno));
    }

    @Test
    public void tableroDevuelveQueJugadorTieneTresDeSeisPaisesDelTablero() {
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));

        paises.get(0).asignarJugador(jugadorUno);
        paises.get(1).asignarJugador(jugadorUno);
        paises.get(4).asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(), paises);

        assertEquals(3,tablero.cantidadDePaisesJugador(jugadorUno));
    }


    @Test
    public void jugadorConUnContienteSeLeAgregaFichasExtrasCorrectas(){
        paisDefensor.asignarJugador(jugadorUno);
        Continente continenteUno = new Continente("A",7, List.of(paisDefensor));
        Tablero tablero = new Tablero(List.of(continenteUno), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        assertTrue(tablero.continenteEsDeJugador("A",jugadorUno));
        assertEquals(0,tablero.cantidadDePaisesJugador(jugadorUno));
        // 3 fichas del minimo por pais + 7 del contiente
        assertEquals(10, jugadorUno.sacarFichas(0));
    }

    @Test
    public void jugadorConDosContientesSeLeAgregaFichasExtras(){
        paisDefensor.asignarJugador(jugadorUno);
        Continente continenteUno = new Continente("A",1, List.of(paisDefensor));
        Continente continenteDos = new Continente("B",1, List.of(paisDefensor));

        Tablero tablero = new Tablero(List.of(continenteUno,continenteDos), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        assertTrue(tablero.continenteEsDeJugador("A",jugadorUno));
        assertTrue(tablero.continenteEsDeJugador("B",jugadorUno));
        // 3 fichas del minimo por pais + 2 una por cada contiente
        assertEquals(5, jugadorUno.sacarFichas(0));
    }


    @Test
    public void jugadorConDiezPaisesYDosContientesObtieneSieteFichas(){
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));
        paises.add(new Pais("E",new ArrayList<>()));
        paises.add(new Pais("F",new ArrayList<>()));
        paises.add(new Pais("G",new ArrayList<>()));
        paises.add(new Pais("H",new ArrayList<>()));

        for (Pais pais: paises){
            pais.asignarJugador(jugadorUno);
        }
        Continente continenteUno = new Continente("A",1, List.of(paisDefensor));
        Continente continenteDos = new Continente("B",1, List.of(paisDefensor));

        Tablero tablero = new Tablero(List.of(continenteUno,continenteDos), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        assertTrue(tablero.continenteEsDeJugador("A",jugadorUno));
        assertTrue(tablero.continenteEsDeJugador("B",jugadorUno));
        assertEquals(10,tablero.cantidadDePaisesJugador(jugadorUno));
        // 5 fichas(10 paises/2) + 2 una por cada contiente
        assertEquals(7, jugadorUno.sacarFichas(0));
    }

    @Test
    public void jugadorConDiezPaisesObtieneCincoFichas(){
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));
        paises.add(new Pais("E",new ArrayList<>()));
        paises.add(new Pais("F",new ArrayList<>()));
        paises.add(new Pais("G",new ArrayList<>()));
        paises.add(new Pais("H",new ArrayList<>()));

        for (Pais pais: paises){
            pais.asignarJugador(jugadorUno);
        }

        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        jugadorUno.sacarFichas(10);
        tablero.agregarFichasA(jugadorUno);

        assertEquals(10,tablero.cantidadDePaisesJugador(jugadorUno));
        // 5 fichas(10 paises/2)
        assertEquals(5, jugadorUno.sacarFichas(0));
    }

    @Test
    public void tableroDevuelveQueElcontieneteEsDeUnJugadorsiLoEs(){
        Continente continente = Mockito.mock(Continente.class);

        when(continente.getNombre()).thenReturn("A");
        when(continente.esDeJugador(jugadorUno)).thenReturn(true);
        Tablero tablero = new Tablero(List.of(continente), paises);

        assertTrue(tablero.continenteEsDeJugador("A",jugadorUno));
        verify(continente,times(1)).esDeJugador(jugadorUno);
    }

    @Test
    public void tableroDevuelveQueElContieneteNoEsDeUnJugadorSiElContinenteNoEsDelJugador(){
        Continente continente = Mockito.mock(Continente.class);

        when(continente.getNombre()).thenReturn("A");
        when(continente.esDeJugador(jugadorUno)).thenReturn(false);
        Tablero tablero = new Tablero(List.of(continente), paises);

        assertFalse(tablero.continenteEsDeJugador("A",jugadorUno));
        verify(continente,times(1)).esDeJugador(jugadorUno);
    }

    @Test
    public void tableroDevuelvelaCantidadDePaisesQueTieneElJugadorEnElContinente(){
        Continente continente = Mockito.mock(Continente.class);

        when(continente.getNombre()).thenReturn("A");
        when(continente.cantidadPaisesDe(jugadorUno)).thenReturn(5);
        Tablero tablero = new Tablero(List.of(continente), paises);

        assertEquals(5,tablero.cantidadDePaisesJugadorEnContinente("A",jugadorUno));
        verify(continente,times(1)).cantidadPaisesDe(jugadorUno);
    }

    @Test
    public void tableroDevuelveTodosLosPaisesDelJugador(){
        paises.add(paisDefensor);
        paises.add(paisAtacante);
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));
        paises.add(new Pais("E",new ArrayList<>()));
        paises.add(new Pais("F",new ArrayList<>()));
        paises.add(new Pais("G",new ArrayList<>()));
        paises.add(new Pais("H",new ArrayList<>()));

        for (Pais pais: paises){
            pais.asignarJugador(jugadorUno);
        }

        Tablero tablero = new Tablero(new ArrayList<>(), paises);


        assertEquals(10,tablero.getPaisesJugador(jugadorUno).size());
        assertEquals(0,tablero.getPaisesEnemigos(jugadorUno).size());
    }

    @Test
    public void tableroDevuelveLaCantidadDePaisesPorContinenteDelJugador(){
        Continente continente = Mockito.mock(Continente.class);

        when(continente.paisesDeJugador(jugadorUno)).thenReturn("OK");
        when(continente.esDeJugador(jugadorUno)).thenReturn(false);
        Tablero tablero = new Tablero(List.of(continente), paises);

        assertEquals("OK",tablero.getPaisesPorContinentes(jugadorUno).get(0));
        assertEquals(1,tablero.getPaisesPorContinentes(jugadorUno).size());
    }
}