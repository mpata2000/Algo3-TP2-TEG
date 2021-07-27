package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.cartas.ColeccionDeCartasPais;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
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


    @Test
    public void segundoCanjeDaSieteFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(7,jugadorUno.sacarFichas(0));

    }


    @Test
    public void tercerCanjeDaDiezFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(10,jugadorUno.sacarFichas(0));


    }


    @Test
    public void cuartoCanjeDaQuinceFichasAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(10);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 15 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(15,jugadorUno.sacarFichas(0));


    }

    @Test
    public void enesimoCanjeDaCincoFichasMasQueElCanjeAnteriorAJugador(){
        cartas.add(new CartaPais("Alaska","Globo"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");


        //Suma 4 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(4);

        cartasPais.agregarCartaPais(new CartaPais("Algo","Cañon"));
        //Suma 7 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(7);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 10 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(10);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 15 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        jugadorUno.sacarFichas(15);

        cartasPais.agregarCartasPais(List.of(
                new CartaPais("A","Cañon"),
                new CartaPais("B","Cañon"),
                new CartaPais("C","Cañon")));
        //Suma 20 fichas a Jugador
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(20,jugadorUno.sacarFichas(0));

    }

    @Test
    public void coleccionConDosCanjesPosiblesHaceUnoNoPierdeElOtro(){
        cartas.add(new CartaPais("Alaska","Globo"));
        cartas.add(new CartaPais("Alaska","Cañon"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(3,cartasPais.cantidadDeCartas());

    }

    @Test
    public void coleccionConDosCanjesPosiblesHaceLosDosCanjes(){
        cartas.add(new CartaPais("Alaska","Barco"));
        cartas.add(new CartaPais("Alaska","Barco"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        Jugador jugadorUno = new Jugador("Julian");

        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(3,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,new ColeccionDeCartasPais()));
        assertEquals(11,jugadorUno.sacarFichas(0));
    }

    @Test
    public void coleccionAlHacerCanjePasaBienLasCartasALaOtraColeccionUnCanje(){
        cartas.add(new CartaPais("Alaska","Barco"));
        cartas.add(new CartaPais("Alaska","Barco"));
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        ColeccionDeCartasPais mazo = new ColeccionDeCartasPais();
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
        ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais(cartas);
        ColeccionDeCartasPais mazo = new ColeccionDeCartasPais();
        Jugador jugadorUno = new Jugador("Julian");


        assertEquals(6,cartasPais.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,mazo));
        assertEquals(3,cartasPais.cantidadDeCartas());
        assertEquals(3,mazo.cantidadDeCartas());
        assertTrue(cartasPais.canjeDeCartas(jugadorUno,mazo));
        assertEquals(0,cartasPais.cantidadDeCartas());
        assertEquals(6,mazo.cantidadDeCartas());
    }

}
