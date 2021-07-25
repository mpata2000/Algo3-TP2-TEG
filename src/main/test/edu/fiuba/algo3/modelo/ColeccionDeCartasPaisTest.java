package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ColeccionDeCartasPaisTest {

    ArrayList<Pais> paises = new ArrayList<>();
    ArrayList<CartaPais> cartas = new ArrayList<>();
    Tablero tablero;

    @BeforeEach
    void setUp() {
        paises.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paises.add(new Pais("Sumatra", List.of("Australia","India")));
        paises.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paises.add(new Pais("Java", List.of("Australia")));
        tablero = new Tablero(new ArrayList<>(),paises);

        cartas.add(new CartaPais("Borneo","Globo"));//Cambio de simbolo de Barco->Globo
        cartas.add(new CartaPais("Sumatra","Globo"));
        cartas.add(new CartaPais("Australia","Cañon"));
        cartas.add(new CartaPais("Java","Cañon"));

        for(CartaPais carta: cartas){
            carta.setPais(tablero);
        }
    }

    @Test
    public void coleccionDeCartasSeCreaSinCartas(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais();

        assertEquals(0,cartasPais.cantidadDeCartas());
    }


    @Test
    public void coleccionDeCartasConVariasCarta(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);

        assertEquals(4,cartasPais.cantidadDeCartas());
    }

    @Test
    public void sePuedeAgregarUnaCartaAColeccionDeCartas(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais();
        cartasPais.agregarCartaPais(cartas.get(0));
        assertEquals(1,cartasPais.cantidadDeCartas());
    }

    @Test
    public void coleccionDeCartasAsignaTodosLosPaises(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugador = new Jugador("Julian");
        cartasPais.asignarPaises(List.of(jugador));
        for(Pais pais: paises){
            assertTrue(pais.esDeJugador(jugador));
        }
    }

    @Test
    public void SeLeAsignaLaCartaSiempreAlPrimerJugador(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais();
        cartasPais.agregarCartaPais(cartas.get(0));
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("carlos");

        cartasPais.asignarPaises(List.of(jugadorUno,jugadorDos));
        assertTrue(paises.get(0).esDeJugador(jugadorUno));
    }

    @Test
    public void coleccionDeCartasHacecanjeSiTieneTresSimboloIguales(){
        cartas.add(new CartaPais("Alaska","Globo"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
    }

    @Test
    public void comodin(){
        cartas.add(new CartaPais("Alaska","Comodin"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
    }

    @Test
    public void tresDistintas(){
        cartas.add(new CartaPais("Alaska","Barco"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
        assertEquals(4,jugadorUno.sacarFichas(0));
    }


    @Test
    public void soloUnComodinNoHayCanje(){
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(List.of(new CartaPais("Alaska","Comodin")));
        Jugador jugadorUno = new Jugador("Julian");
        assertFalse(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(0,jugadorUno.sacarFichas(0));
    }

}
