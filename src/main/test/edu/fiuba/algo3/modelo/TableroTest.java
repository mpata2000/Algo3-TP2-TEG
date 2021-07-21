package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
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
    Turnos turnos;
    ArrayList<Pais> paises = new ArrayList<>();

    @BeforeEach
    void setUp() {
        paisAtacante = new Pais("Chile", List.of("Argentina","Brazil"));
        paisDefensor =  new Pais("Argentina", List.of("Chile","Brazil"));
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
        jugadores.put("Amarillo",jugadorUno);
        jugadores.put("Rojo",jugadorDos);
        turnos = new Turnos(jugadores);
        turnos = Mockito.mock(Turnos.class);
    }

    @Test
    public void agregarFichasAUnPaisVacioEntoncesElPaisEsDelJugadorTest(){
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        tablero.agregarFichas(5,jugadorUno, "Chile",turnos);

        assertTrue(paisAtacante.esDeJugador(jugadorUno));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        paises.add(paisAtacante);
        paisAtacante.asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5,jugadorDos, "Chile",turnos));
    }

    //Todo: refacor de esta prueba
    @Test
    public void ataque(){
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);

        Tablero tablero = new Tablero(new ArrayList<>(),paises);
        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        tablero.agregarFichas(5,jugadorUno, "Argentina",turnos);

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5,jugadorDos, "Argentina",turnos));
    }

    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);
        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        tablero.agregarFichas(3, jugadorUno,"Argentina",turnos);
        tablero.agregarFichas(5, jugadorDos,"Chile",turnos);

        assertThrows(NoSePuedenCrearCeroDados.class, () -> tablero.atacar("Argentina","Chile",0,turnos));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        tablero.agregarFichas(5, jugadorDos,"Chile",turnos);

        assertThrows(EjercitoConUnaFichaNoPuedeAtacar.class, () -> tablero.atacar("Argentina","Chile",1,turnos));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        tablero.agregarFichas(1, jugadorUno,"Argentina",turnos);
        tablero.agregarFichas(4, jugadorDos,"Chile",turnos);

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDados.class, () -> tablero.atacar("Argentina","Chile",3,turnos));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        paisDefensor.asignarJugador(jugadorUno);
        paisAtacante.asignarJugador(jugadorDos);
        paises.add(paisAtacante);
        paises.add(paisDefensor);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        tablero.agregarFichas(5, jugadorUno,"Argentina",turnos);
        tablero.agregarFichas(5, jugadorDos,"Chile",turnos);

        assertFalse(tablero.atacar("Argentina","Chile",2,turnos));

    }

/*    @Test
    // NO SUPIMOS HACERLO EN MOQUITO
    public void paisAtacanteConquistaPaisDefensor() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));
        Pais pais1 = Mockito.mock(Pais.class);
        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0, 3};

        when(dados.comparadorDeDados(any(Dados.class))).thenReturn(conjunto);
        when(pais1.tirarDados(3)).thenReturn(dados);
        when(pais1.getJugador()).thenReturn(jugadorUno);
        tablero.agregarFichas(3, jugadorDos, "Chile");
        //tablero.agregarFichas(5, jugadorUno, "Argentina");

        assertTrue(tablero.atacar("Argentina", "Chile", 3));
    }
    }
*/
    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        Pais pais = new Pais("Argentina", List.of("Chile","Brazil"));
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(pais);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);
        Jugador jugadorUno = new Jugador("Julian");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(paisAtacante);
        tablero.agregarPais(paisDefensor);
        when(turnos.esTurnoDe(paisDefensor)).thenReturn(true);
        when(turnos.esTurnoDe(paisAtacante)).thenReturn(true);
        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorUno);
        tablero.agregarFichas(4, jugadorUno,"Argentina",turnos);
        tablero.agregarFichas(5, jugadorUno,"Chile",turnos);

        assertThrows(AtaqueNoValido.class, () -> tablero.atacar("Argentina","Chile",1,turnos));

    }
}