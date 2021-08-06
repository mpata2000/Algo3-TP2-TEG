package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.cartas.CartasPais;
import edu.fiuba.algo3.modelo.cartas.CartasPaisTeg;
import edu.fiuba.algo3.modelo.cartas.MazoDeCartasPais;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TegTest {
    Jugador jugadorMock1;
    Jugador jugadorMock2;
    Tablero tablero;
    LinkedHashMap<String,Jugador> jugadores;

    @BeforeEach
    void setUp(){
        jugadorMock1 = Mockito.mock(Jugador.class);
        jugadorMock2 = Mockito.mock(Jugador.class);
        tablero = Mockito.mock(Tablero.class);
        jugadores = new LinkedHashMap<>();
    }

    @Test
    public void comenzarTegAignaObjetivosYPaisesALosJugadores(){
        Teg teg = new Teg();
        teg.comenzarJuego(List.of("ROJO","AMARILLO"));
        assertEquals(25,teg.cantidadDePaisesJugador("ROJO"));
        assertEquals(25,teg.cantidadDePaisesJugador("AMARILLO"));
    }

    @Test
    public void tegDetectaQueHayUnGanadorSiUnJugadorCumplioSuObjetivo(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(false);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores, new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
    }

    @Test
    public void tegHayUnGanadorDevuelveElGanadorCorrecto(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(false);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock2.getColor()).thenReturn("AMARILLO");
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
        assertEquals("AMARILLO",teg.getGanador());
    }

    @Test
    public void tegLeSacaLaCoquistaDepaisATodosLosJugadores(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        teg.sacarConquistaDePaisAJugadores();
        verify(jugadorMock1,times(1)).sacarConquista();
        verify(jugadorMock2,times(1)).sacarConquista();
    }

    @Test
    public void teDevuelveElPrimerGanador(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock1.getColor()).thenReturn("ROJO");
        when(jugadorMock2.getColor()).thenReturn("AMARILLO");
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
        assertEquals("ROJO",teg.getGanador());
    }

    @Test
    public void tegLeDaUnaCartaAUnJugador(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.darCartaPaisA("ROJO");

        verify(jugadorMock1,times(1)).darCartaPais(any(CartaPais.class));
    }

    @Test
    public void tegLeDiceAJugadorDeHacerCanje(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.hacerCanjeJugador("ROJO");

        verify(jugadorMock1,times(1)).hacerCanje(any(MazoDeCartasPais.class));
    }

    @Test
    public void tegDelegaPasarFichasATablero(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.pasarFichas("ROJO","A","B",1);

        verify(tablero,times(1)).pasarFichas(jugadorMock1,"A","B",1);
    }

    @Test
    public void tegDelegaColocarFichasATablero(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.colocarFichas("ROJO","A",3);

        verify(tablero,times(1)).agregarFichas("A", jugadorMock1, 3);
    }

    @Test
    public void tegJugadorGanaBatallaYSeLoActualiza(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(tablero.atacar(jugadorMock1,"A","B",1)).thenReturn(true);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        assertTrue(teg.atacarConA("ROJO","A","B",1));
        verify(jugadorMock1,times(1)).conquistoPais();
    }

    @Test
    public void tegJugadorNoGanaBatalla(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(tablero.atacar(jugadorMock1,"A","B",1)).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        assertFalse(teg.atacarConA("ROJO","A","B",1));
        verify(jugadorMock1,times(0)).conquistoPais();
    }

    @Test
    public void tegLeDaLasFichasCorrectasAJugadorYElJugadorTieneFichas(){
        Jugador jugadorTest = new Jugador("ROJO");
        jugadores.put("ROJO",jugadorTest);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.agregarFichasA("ROJO",3);
        assertTrue(teg.jugadorTieneFichas("ROJO"));
        assertEquals(3,jugadorTest.sacarFichas(0));
    }

    @Test
    public void tegLeagregaFichasDisponiblesAjugador(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.agregarFichasDisponiblesA("ROJO");

        verify(tablero,times(1)).agregarFichasA(jugadorMock1);
        verify(jugadorMock1,times(1)).activarCartas();
    }


    @Test
    public void tegDevuelveSiElContienteEsDelJugador(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(tablero.continenteEsDeJugador("A",jugadorMock1)).thenReturn(true);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertTrue(teg.continenteEsDeJugador("A","ROJO"));
        assertFalse(teg.continenteEsDeJugador("A","AMARILLO"));
        verify(tablero,times(1)).continenteEsDeJugador("A",jugadorMock1);
        verify(tablero,times(1)).continenteEsDeJugador("A",jugadorMock2);

    }

    @Test
    public void tegDevuelveLaCantidadDePaisesPorContienteDelJugador(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(tablero.cantidadDePaisesJugadorEnContinente("A",jugadorMock1)).thenReturn(5);
        when(tablero.cantidadDePaisesJugadorEnContinente("A",jugadorMock2)).thenReturn(2);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertEquals(5,teg.cantidadDePaisesJugadorEnContinente("A","ROJO"));
        assertEquals(2,teg.cantidadDePaisesJugadorEnContinente("A","AMARILLO"));
        verify(tablero,times(1)).cantidadDePaisesJugadorEnContinente("A",jugadorMock1);
        verify(tablero,times(1)).cantidadDePaisesJugadorEnContinente("A",jugadorMock2);

    }


    @Test
    public void jugadorPuedeHacerCanjeYTegDevuelveTrue(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(jugadorMock1.hacerCanje(any(CartasPaisTeg.class))).thenReturn(true);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertTrue(teg.hacerCanjeJugador("ROJO"));

    }


    @Test
    public void jugadorNoPuedeHacerCanjeYTegDevuelveFalse(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(jugadorMock1.hacerCanje(any(CartasPaisTeg.class))).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertFalse(teg.hacerCanjeJugador("ROJO"));
    }


    @Test
    public void jugadorNoPuedeRecivirCartapaisYTegDevuelveFalse(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(jugadorMock1.darCartaPais(any(CartaPais.class))).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertFalse(teg.darCartaPaisA("ROJO"));
    }

    @Test
    public void jugadorPuedeRecivirCartapaisYTegDevuelveTrue(){
        jugadores.put("ROJO",jugadorMock1);
        jugadores.put("AMARILLO",jugadorMock2);
        when(jugadorMock1.darCartaPais(any(CartaPais.class))).thenReturn(true);

        List<CartaPais> lista = CartasPais.create(Teg.PATHJSON.concat("Teg-Cartas.json"),tablero);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(lista));

        assertTrue(teg.darCartaPaisA("ROJO"));
    }

    @Test
    public void TegDevuelveElObjetivoDelJugador(){
        jugadores.put("ROJO",jugadorMock1);
        when(jugadorMock1.textoObjetivo()).thenReturn("OK");
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals("OK", teg.textoObjetivo("ROJO"));
        verify(jugadorMock1,times(1)).textoObjetivo();
    }

    @Test
    public void TegDevuelveLasFichasJugadorActual(){
        jugadores.put("ROJO",jugadorMock1);
        when(jugadorMock1.getFichas()).thenReturn(1);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals(1, teg.getFichas("ROJO"));
        verify(jugadorMock1,times(1)).getFichas();
    }

    @Test
    public void TegDevuelveLosPaisesPorContinenteDeJugador(){
        jugadores.put("ROJO",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        List<String> list = List.of("A","B");
        when(tableroMock.getPaisesPorContinentes(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());

        assertEquals(list, teg.getPaisesPorContinentes("ROJO"));
        verify(tableroMock,times(1)).getPaisesPorContinentes(jugadorMock1);
    }


    @Test
    public void TegDevuelveLasCatasPaisDelJugadorActual(){
        jugadores.put("ROJO",jugadorMock1);

        List<String> list = List.of("A","B");
        when(jugadorMock1.getCartas()).thenReturn(list);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals(list, teg.getCartasJugador("ROJO"));
        verify(jugadorMock1,times(1)).getCartas();
    }

    @Test
    public void TegDevuelveLosPaisesDelJugadorActual(){
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        jugadores.put("ROJO",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(tableroMock.getPaisesJugador(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());


        assertEquals(list, teg.getPaisesJugador("ROJO"));
        verify(tableroMock,times(1)).getPaisesJugador(jugadorMock1);
    }

    @Test
    public void TegDevuelveLosPaisesQueNoSonDelJugadorActual(){
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        jugadores.put("ROJO",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(tableroMock.getPaisesEnemigos(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());


        assertEquals(list, teg.getPaisesEnemigos("ROJO"));
        verify(tableroMock,times(1)).getPaisesEnemigos(jugadorMock1);
    }

}
