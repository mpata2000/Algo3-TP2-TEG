package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.cartas.MazoDeCartasPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class JugadorTest {

    Jugador jugador;
    Pais paisMock;
    @BeforeEach
    void setUp(){
        jugador = new Jugador("Rojo");
        paisMock = Mockito.mock(Pais.class);
    }

    @Test
    public void siJugadorNoConquistaPaisNoPuedeRecibirCarta(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);

        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorConquistaPaisYReciveCartaPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorNoConquistaPaisNoReciveCartaPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorConquistaPaisNoReciveDosCartasPais(){
        Pais pais = new Pais("Alaska", List.of("Rusia"));
        CartaPais carta = new CartaPais("Alaska","Globo",pais);
        pais.asignarJugador(jugador);
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(carta));
        assertFalse(jugador.darCartaPais(carta));
    }

    @Test
    public void jugadorSabeQueEsElMismo(){
        assertTrue(jugador.esElMismoJugador(jugador));
    }

    @Test
    public void jugadoresDistintosNoSonElMismoJugador(){
        assertFalse(jugador.esElMismoJugador(new Jugador("Magenta")));
    }

    @Test
    public void jugadorSeCreaSinFichasRestantes(){
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganFichasYTieneFichasRestantes(){
        jugador.agregarFichas(4);
        assertTrue(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanDosQuedaConOcho(){
        jugador.agregarFichas(10);
        assertEquals(8,jugador.sacarFichas(2));
        assertTrue(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanDiezSinFichas(){
        jugador.agregarFichas(10);
        assertEquals(0,jugador.sacarFichas(10));
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorSeLeAgreganDiezFichasYSeleSacanVeinteSinFichas(){
        jugador.agregarFichas(10);
        assertEquals(0,jugador.sacarFichas(20));
        assertFalse(jugador.tieneFichas());
    }

    @Test
    public void jugadorConTresFichasNoPuedePonerCuatroFichas(){
        jugador.agregarFichas(3);
        assertFalse(jugador.puedeColocarFichas(4));
    }

    @Test
    public void jugadorConSieteFichasPuedePonerSieteFichas(){
        jugador.agregarFichas(7);
        assertTrue(jugador.puedeColocarFichas(7));
    }

    @Test
    public void siJugadorCumplioObjetivoGano(){
        ObjetivoTeg objetivo = Mockito.mock(ObjetivoTeg.class);
        Teg teg = new Teg();
        when(objetivo.cumplioObjetivo(teg)).thenReturn(true);
        jugador.darObjetivo(objetivo);
        assertTrue(jugador.gano(teg));
    }

    @Test
    public void siJugadorNoCumplioObjetivoNoGano(){
        ObjetivoTeg objetivo = Mockito.mock(ObjetivoTeg.class);
        Teg teg = new Teg();
        when(objetivo.cumplioObjetivo(teg)).thenReturn(false);
        jugador.darObjetivo(objetivo);
        assertFalse(jugador.gano(teg));
    }

    @Test
    public void jugadorNoPuedeRecivirCartaSiNoConquistoUnPais(){
        jugador.darCartaPais(new CartaPais("A","Globo"));
        assertFalse(jugador.darCartaPais(new CartaPais("A","Globo")));
    }

    @Test
    public void jugadorPuedeRecivirCartaSiConquistoUnPais(){
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(new CartaPais("A","Globo",paisMock)));
    }

    @Test
    public void jugadorNoPuedeRecivirUnaSegundaCartaSinHaberConquistadoAntesOtroPais(){
        jugador.conquistoPais();
        assertTrue(jugador.darCartaPais(new CartaPais("A","Globo",paisMock)));
        assertFalse(jugador.darCartaPais(new CartaPais("A","Globo",paisMock)));
    }

    @Test
    public void jugadorHaceCanjeDeTresCartasReciveFichasYSeLasPasaAMazo(){
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("A","Globo",paisMock));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("B","Globo",paisMock));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("C","Globo",paisMock));
        MazoDeCartasPais mazo = new MazoDeCartasPais();

        assertFalse(jugador.tieneFichas());
        jugador.hacerCanje(mazo);
        assertEquals(4,jugador.sacarFichas(0));
        assertEquals(3,mazo.cantidadDeCartas());
    }

    @Test
    public void jugadorNoHaceCanjeDeTresCartas(){
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("B","Globo", paisMock));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("C","Globo", paisMock));
        MazoDeCartasPais mazo = new MazoDeCartasPais();

        assertFalse(jugador.tieneFichas());
        jugador.hacerCanje(mazo);
        assertEquals(0,jugador.sacarFichas(0));
        assertEquals(0,mazo.cantidadDeCartas());
    }

    @Test
    public void jugadorActivaLasCartasDeLasCualesPoseeElPais() {
        List<Pais> paises = new ArrayList<>();
        paises.add(new Pais("A",new ArrayList<>()));
        paises.add(new Pais("B",new ArrayList<>()));
        paises.add(new Pais("C",new ArrayList<>()));
        paises.add(new Pais("D",new ArrayList<>()));

        paises.get(0).asignarJugador(jugador);
        paises.get(1).asignarJugador(jugador); //Agrega una ficha la asignasion
        paises.get(2).asignarJugador(new Jugador("A"));
        paises.get(3).asignarJugador(new Jugador("A"));

        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("A","Globo",paises.get(0)));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("B","Globo",paises.get(1)));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("C","Globo",paises.get(2)));
        jugador.conquistoPais();
        jugador.darCartaPais(new CartaPais("D","Globo",paises.get(3)));

        jugador.activarCartas();

        assertEquals(3,paises.get(0).perderFichas(0));
        assertEquals(3,paises.get(1).perderFichas(0));
        assertEquals(1,paises.get(2).perderFichas(0));
        assertEquals(1,paises.get(3).perderFichas(0));
    }
}
