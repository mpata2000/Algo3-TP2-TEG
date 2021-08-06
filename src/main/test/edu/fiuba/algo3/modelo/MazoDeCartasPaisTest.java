package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.cartas.MazoDeCartasPais;
import edu.fiuba.algo3.modelo.cartas.MazoNoTieneSuficientesCartasException;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MazoDeCartasPaisTest {

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
        MazoDeCartasPais cartasPais = new MazoDeCartasPais();

        assertEquals(0,cartasPais.cantidadDeCartas());
    }


    @Test
    public void coleccionDeCartasConVariasCarta(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);

        assertEquals(4,cartasPais.cantidadDeCartas());
    }

    @Test
    public void sePuedeAgregarUnaCartaAColeccionDeCartas(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais();
        cartasPais.agregarCartaPais(cartas.get(0));
        assertEquals(1,cartasPais.cantidadDeCartas());
    }

    @Test
    public void coleccionDeCartasAsignaTodosLosPaises(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugador = new Jugador("Julian");
        cartasPais.asignarPaises(List.of(jugador));
        for(Pais pais: paises){
            assertTrue(pais.esDeJugador(jugador));
        }
    }

    @Test
    public void SeLeAsignaLaCartaSiempreAlPrimerJugador(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais();
        cartasPais.agregarCartaPais(cartas.get(0));
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("carlos");

        cartasPais.asignarPaises(List.of(jugadorUno,jugadorDos));
        assertTrue(paises.get(0).esDeJugador(jugadorUno));
    }

    @Test
    public void coleccionDeCartasNoHaceConDosParesDeCartasConMismoSimbolo(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertFalse(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(4,cartasPais.cantidadDeCartas());
    }

    @Test
    public void coleccionDeCartasHaceCanjeSiTieneTresSimboloIguales(){
        cartas.add(new CartaPais("Alaska","Globo"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
    }

    @Test
    public void canjeConComodin(){
        cartas.add(new CartaPais("Alaska","Comodin"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
    }

    @Test
    public void canjeDetreCartasDistintas(){
        cartas.add(new CartaPais("Alaska","Barco"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(2,cartasPais.cantidadDeCartas());
        assertEquals(4,jugadorUno.sacarFichas(0));
    }


    @Test
    public void soloUnComodinNoHayCanje(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(List.of(new CartaPais("Alaska","Comodin")));
        Jugador jugadorUno = new Jugador("Julian");
        assertFalse(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(0,jugadorUno.sacarFichas(0));
    }


    @Test
    public void segundoCanjeDaSieteFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(7,jugadorUno.sacarFichas(0));

    }


    @Test
    public void tercerCanjeDaDiezFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(10,jugadorUno.sacarFichas(0));


    }


    @Test
    public void cuartoCanjeDaQuinceFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(10);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 15 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(15,jugadorUno.sacarFichas(0));


    }

    @Test
    public void enesimoCanjeDaCincoFichasMasQueElCanjeAnteriorAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");


        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(10);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 15 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        jugadorUno.sacarFichas(15);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 20 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(20,jugadorUno.sacarFichas(0));

    }

    @Test
    public void coleccionConDosCanjesPosiblesHaceUnoNoPierdeElOtro(){
        cartas.add(new CartaPais("Alaska","Globo"));
        cartas.add(new CartaPais("Alaska","Cañon"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(3,cartasPais.cantidadDeCartas());

    }

    @Test
    public void coleccionConDosCanjesPosiblesHaceLosDosCanjes(){
        cartas.add(new CartaPais("Alaska","Barco"));
        cartas.add(new CartaPais("Alaska","Barco"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(3,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new MazoDeCartasPais()));
        assertEquals(11,jugadorUno.sacarFichas(0));
    }

    @Test
    public void coleccionAlHacerCanjePasaBienLasCartasALaOtraColeccionUnCanje(){
        cartas.add(new CartaPais("Alaska","Barco"));
        cartas.add(new CartaPais("Alaska","Barco"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        MazoDeCartasPais mazo = new MazoDeCartasPais();
        Jugador jugadorUno = new Jugador("Julian");


        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,mazo));
        assertEquals(3,cartasPais.cantidadDeCartas());
        assertEquals(3,mazo.cantidadDeCartas());
    }

    @Test
    public void coleccionAlHacerCanjePasaBienLasCartasALaOtraColeccionDosCanjes(){
        cartas.add(new CartaPais("Alaska","Barco"));
        cartas.add(new CartaPais("Alaska","Barco"));
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        MazoDeCartasPais mazo = new MazoDeCartasPais();
        Jugador jugadorUno = new Jugador("Julian");


        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,mazo));
        assertEquals(3,cartasPais.cantidadDeCartas());
        assertEquals(3,mazo.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,mazo));
        assertEquals(0,cartasPais.cantidadDeCartas());
        assertEquals(6,mazo.cantidadDeCartas());
    }


    @Test
    public void coleccionDeCartasSinCartasNoPuedeDarCartasAJugador(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais();
        Jugador jugador = Mockito.mock(Jugador.class);

        assertEquals(0,cartasPais.cantidadDeCartas());
        assertThrows(MazoNoTieneSuficientesCartasException.class, ()-> cartasPais.darCartaA(jugador));
    }

    @Test
    public void coleccionDeCartasNoLePuedeDarCartasAJugadorSiTieneCartasPeroJugadorNoConquistoPais(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugador = Mockito.mock(Jugador.class);
        when(jugador.darCartaPais(any(CartaPais.class))).thenReturn(false);
        assertEquals(4,cartasPais.cantidadDeCartas());
        cartasPais.darCartaA(jugador);
        verify(jugador,times(1)).darCartaPais(any(CartaPais.class));
        assertEquals(4,cartasPais.cantidadDeCartas());
    }

    @Test
    public void coleccionDeCartasLePuedeDarCartasAJugadorSiTieneCartasYJugadorConquistoPais(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugador = Mockito.mock(Jugador.class);
        when(jugador.darCartaPais(any(CartaPais.class))).thenReturn(true);
        assertEquals(4,cartasPais.cantidadDeCartas());
        cartasPais.darCartaA(jugador);
        verify(jugador,times(1)).darCartaPais(any(CartaPais.class));
        assertEquals(3,cartasPais.cantidadDeCartas());
    }

    @Test
    public void coleccionDeCartasActivaTodasLasCartasDelJugador(){
        MazoDeCartasPais cartasPais = new MazoDeCartasPais(cartas);
        Jugador jugador = new Jugador("Rojo");
        cartasPais.asignarPaises(List.of(jugador)); //Pone una ficha y asigna los jugadores
        cartasPais.activarCartas(jugador); //activa todas las cartas del mazo,le da dos fichas al pais si es del jugador
        for(Pais pais: paises){
            assertTrue(pais.esDeJugador(jugador));
            assertEquals(3,pais.perderFichas(0));
        }
    }

}
