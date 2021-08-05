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
import static org.mockito.ArgumentMatchers.any;
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
        teg.comenzarJuego(List.of("Rojo","Amarillo"));
        assertEquals(25,teg.cantidadDePaisesJugador("Rojo"));
        assertEquals(25,teg.cantidadDePaisesJugador("Amarillo"));
    }

    @Test
    public void tegDetectaQueHayUnGanadorSiUnJugadorCumplioSuObjetivo(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(false);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores, new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
    }

    @Test
    public void tegHayUnGanadorDevuelveElGanadorCorrecto(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(false);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock2.getColor()).thenReturn("Amarillo");
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
        assertEquals("Amarillo",teg.getGanador());
    }

    @Test
    public void tegLeSacaLaCoquistaDepaisATodosLosJugadores(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        teg.sacarConquistaDePaisAJugadores();
        verify(jugadorMock1,times(1)).sacarConquista();
        verify(jugadorMock2,times(1)).sacarConquista();
    }

    @Test
    public void teDevuelveElPrimerGanador(){
        when(jugadorMock1.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock2.gano(any(Teg.class))).thenReturn(true);
        when(jugadorMock1.getColor()).thenReturn("Rojo");
        when(jugadorMock2.getColor()).thenReturn("Amarillo");
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        assertTrue(teg.hayGanador());
        assertEquals("Rojo",teg.getGanador());
    }

    @Test
    public void tegLeDaUnaCartaAUnJugador(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.darCartaPaisA("Rojo");

        verify(jugadorMock1,times(1)).darCartaPais(any(CartaPais.class));
    }

    @Test
    public void tegLeDiceAJugadorDeHacerCanje(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.hacerCanjeJugador("Rojo");

        verify(jugadorMock1,times(1)).hacerCanje(any(MazoDeCartasPais.class));
    }

    @Test
    public void tegDelegaPasarFichasATablero(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.pasarFichas("Rojo","A","B",1);

        verify(tablero,times(1)).pasarFichas(jugadorMock1,"A","B",1);
    }

    @Test
    public void tegDelegaColocarFichasATablero(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.colocarFichas("Rojo","A",3);

        verify(tablero,times(1)).agregarFichas("A", jugadorMock1, 3);
    }

    @Test
    public void tegJugadorGanaBatallaYSeLoActualiza(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(tablero.atacar(jugadorMock1,"A","B",1)).thenReturn(true);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        assertTrue(teg.atacarConA("Rojo","A","B",1));
        verify(jugadorMock1,times(1)).conquistoPais();
    }

    @Test
    public void tegJugadorNoGanaBatalla(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(tablero.atacar(jugadorMock1,"A","B",1)).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        assertFalse(teg.atacarConA("Rojo","A","B",1));
        verify(jugadorMock1,times(0)).conquistoPais();
    }

    @Test
    public void tegLeDaLasFichasCorrectasAJugadorYElJugadorTieneFichas(){
        Jugador jugadorTest = new Jugador("Rojo");
        jugadores.put("Rojo",jugadorTest);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.agregarFichasA("Rojo",3);
        assertTrue(teg.jugadorTieneFichas("Rojo"));
        assertEquals(3,jugadorTest.sacarFichas(0));
    }

    @Test
    public void tegLeagregaFichasDisponiblesAjugador(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));
        teg.agregarFichasDisponiblesA("Rojo");

        verify(tablero,times(1)).agregarFichasA(jugadorMock1);
        verify(jugadorMock1,times(1)).activarCartas();
    }


    @Test
    public void tegDevuelveSiElContienteEsDelJugador(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(tablero.continenteEsDeJugador("A",jugadorMock1)).thenReturn(true);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertTrue(teg.continenteEsDeJugador("A","Rojo"));
        assertFalse(teg.continenteEsDeJugador("A","Amarillo"));
        verify(tablero,times(1)).continenteEsDeJugador("A",jugadorMock1);
        verify(tablero,times(1)).continenteEsDeJugador("A",jugadorMock2);

    }

    @Test
    public void tegDevuelveLaCantidadDePaisesPorContienteDelJugador(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(tablero.cantidadDePaisesJugadorEnContinente("A",jugadorMock1)).thenReturn(5);
        when(tablero.cantidadDePaisesJugadorEnContinente("A",jugadorMock2)).thenReturn(2);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertEquals(5,teg.cantidadDePaisesJugadorEnContinente("A","Rojo"));
        assertEquals(2,teg.cantidadDePaisesJugadorEnContinente("A","Amarillo"));
        verify(tablero,times(1)).cantidadDePaisesJugadorEnContinente("A",jugadorMock1);
        verify(tablero,times(1)).cantidadDePaisesJugadorEnContinente("A",jugadorMock2);

    }


    @Test
    public void jugadorPuedeHacerCanjeYTegDevuelveTrue(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(jugadorMock1.hacerCanje(any(CartasPaisTeg.class))).thenReturn(true);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertTrue(teg.hacerCanjeJugador("Rojo"));

    }


    @Test
    public void jugadorNoPuedeHacerCanjeYTegDevuelveFalse(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(jugadorMock1.hacerCanje(any(CartasPaisTeg.class))).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertFalse(teg.hacerCanjeJugador("Rojo"));
    }


    @Test
    public void jugadorNoPuedeRecivirCartapaisYTegDevuelveFalse(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(jugadorMock1.darCartaPais(any(CartaPais.class))).thenReturn(false);

        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(List.of(new CartaPais("A","B"))));

        assertFalse(teg.darCartaPaisA("Rojo"));
    }

    @Test
    public void jugadorPuedeRecivirCartapaisYTegDevuelveTrue(){
        jugadores.put("Rojo",jugadorMock1);
        jugadores.put("Amarillo",jugadorMock2);
        when(jugadorMock1.darCartaPais(any(CartaPais.class))).thenReturn(true);

        List<CartaPais> lista = CartasPais.create(Teg.PATHJSON.concat("Teg-Cartas.json"),tablero);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais(lista));

        assertTrue(teg.darCartaPaisA("Rojo"));
    }

    @Test
    public void TegDevuelveElObjetivoDelJugador(){
        jugadores.put("Rojo",jugadorMock1);
        when(jugadorMock1.textoObjetivo()).thenReturn("OK");
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals("OK", teg.textoObjetivo("Rojo"));
        verify(jugadorMock1,times(1)).textoObjetivo();
    }

    @Test
    public void TegDevuelveLasFichasJugadorActual(){
        jugadores.put("Rojo",jugadorMock1);
        when(jugadorMock1.getFichas()).thenReturn(1);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals(1, teg.getFichas("Rojo"));
        verify(jugadorMock1,times(1)).getFichas();
    }

    @Test
    public void TegDevuelveLosPaisesPorContinenteDeJugador(){
        jugadores.put("Rojo",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        List<String> list = List.of("A","B");
        when(tableroMock.getPaisesPorContinentes(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());

        assertEquals(list, teg.getPaisesPorContinentes("Rojo"));
        verify(tableroMock,times(1)).getPaisesPorContinentes(jugadorMock1);
    }


    @Test
    public void TegDevuelveLasCatasPaisDelJugadorActual(){
        jugadores.put("Rojo",jugadorMock1);

        List<String> list = List.of("A","B");
        when(jugadorMock1.getCartas()).thenReturn(list);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());

        assertEquals(list, teg.getCartasJugador("Rojo"));
        verify(jugadorMock1,times(1)).getCartas();
    }

    @Test
    public void TegDevuelveLosPaisesDelJugadorActual(){
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        jugadores.put("Rojo",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(tableroMock.getPaisesJugador(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());


        assertEquals(list, teg.getPaisesJugador("Rojo"));
        verify(tableroMock,times(1)).getPaisesJugador(jugadorMock1);
    }

    @Test
    public void TegDevuelveLosPaisesQueNoSonDelJugadorActual(){
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        jugadores.put("Rojo",jugadorMock1);
        Tablero tableroMock = Mockito.mock(Tablero.class);
        when(tableroMock.getPaisesEnemigos(jugadorMock1)).thenReturn(list);
        Teg teg = new Teg(tableroMock,jugadores,new MazoDeCartasPais());


        assertEquals(list, teg.getPaisesEnemigos("Rojo"));
        verify(tableroMock,times(1)).getPaisesEnemigos(jugadorMock1);
    }

}
