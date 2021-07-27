package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartaPaisTest {
    ArrayList<Pais> paises = new ArrayList<>();
    Tablero tablero;

    @BeforeEach
    void setUp() {
        paises.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paises.add(new Pais("Sumatra", List.of("Australia","India")));
        paises.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paises.add(new Pais("Java", List.of("Australia")));
        tablero = new Tablero(new ArrayList<>(),paises);
    }

    @Test
    public void cartaPaisSeCreaConElNombreCorrecto(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        assertEquals("Borneo",carta.getNombrePais());
    }

    @Test
    public void cartaPaisSeCreaConElSimboloCorrecto(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        assertEquals("Barco",carta.getSimbolo());
    }

    @Test
    public void cartaPaisSeAsignaBienElPais(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        assertEquals(tablero.getPais("Borneo"),carta.getPais());
    }

    @Test
    public void cartaPaisAignaElPaisAlJugadorCorrectamente(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        Jugador jugador = new Jugador("Algo");
        carta.asignarPaisA(jugador);
        assertTrue(paises.get(0).esDeJugador(jugador));
    }


    @Test
    public void cartaPaisSeActivaPaisEsDeJugadorYPoneDosFichasAlPais(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        Jugador jugador = new Jugador("Algo");
        carta.asignarPaisA(jugador);
        carta.activarCarta(jugador);
        assertEquals(3,tablero.getPais("Borneo").perderFichas(0));
    }

    @Test
    public void cartaPaisNoSePuedeActivarDosVeces(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        Jugador jugador = new Jugador("Algo");
        carta.asignarPaisA(jugador);
        carta.activarCarta(jugador);
        carta.activarCarta(jugador);
        assertEquals(3,tablero.getPais("Borneo").perderFichas(0));
    }

    @Test
    public void cartaPaisNoSeActivaSiJugadorNoTieneElPais(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        Jugador jugadorUno = new Jugador("Algo");
        Jugador jugadorDos = new Jugador("Algo2");
        carta.asignarPaisA(jugadorUno);
        carta.activarCarta(jugadorDos);
        assertEquals(1,tablero.getPais("Borneo").perderFichas(0));
    }

}
