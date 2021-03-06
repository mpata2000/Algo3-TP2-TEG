
package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadorSigueTeniendoFichasException;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeHacerEstaAccionEnEstaRondaException;
import edu.fiuba.algo3.modelo.excepciones.PasajeDeFichasNoValidoEnAtaqueException;
import edu.fiuba.algo3.modelo.rondas.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TipoDeRondaTest {

    @Test
    public void enRondaDeColocacionNoSePuedeAtacar(){
        Teg teg = Mockito.mock(Teg.class);
        TipoRonda ronda = new RondaColocacion(List.of("A"),teg);
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.atacarACon(teg,"A","B",3));
    }

    @Test
    public void enRondaDeColocacionNoSePuedePasarFichas(){
        Teg teg = Mockito.mock(Teg.class);
        TipoRonda ronda = new RondaColocacion(List.of("A"),teg);
        assertEquals("Ronda de Colocacion",ronda.getNombre());
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.pasarFichas(teg,"A","B",3));
    }

    @Test
    public void enRondaDeReagrupacionNoSePuedeAtacar(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();
        assertEquals("Ronda de Reagrupacion",ronda.getNombre());
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.atacarACon(teg,"A","B",3));
    }

    @Test
    public void enRondaDeReagrupacionNoSePuedeColocar(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.colocarFichas(teg,"A",3));
    }

    @Test
    public void enRondaDeReagrupacionSinJugadoresSinguientesDevuelveRondaColocacionAlTerminarEtapa(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = Mockito.mock(Teg.class);

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaColocacion);
    }


    @Test
    public void enRondaDeReagrupacionConMasJugadoresDevuelveRondaColocacionAlTerminarEtapa(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaAtaque);
    }

    @Test
    public void enRondaDeReagrupacionPasaARondaAtaqueConELSiguienteJugador(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = new Teg();

        assertEquals("A",ronda.getJugadorActual());
        ronda = ronda.finEtapa(list,teg);

        assertTrue(ronda instanceof RondaAtaque);
        assertEquals("Ronda de Ataque",ronda.getNombre());
        assertEquals("B",ronda.getJugadorActual());
    }

    @Test
    public void enRondaDeReagrupacionPasaARondaColocacionYSeArrancaDesdeElPrimerJugador(){
        List<String> list = List.of("A","B","C","D");
        TipoRonda ronda = new RondaReagrupacion(list.get(3), list);
        Teg teg = Mockito.mock(Teg.class);

        assertEquals("D",ronda.getJugadorActual());
        ronda = ronda.finEtapa(list,teg);

        assertTrue(ronda instanceof RondaColocacion);
        assertEquals("A",ronda.getJugadorActual());
    }

    @Test
    public void enRondaDeReagrupacionSinJugadoresSinguientesConGanadorDevuelveRondaGanadorAlTerminar(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = Mockito.mock(Teg.class);
        when(teg.hayGanador()).thenReturn(true);

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaGanador);
    }

    @Test
    public void enRondaAtaqueTerminarEtapaDevuelveRondaReagrupacion(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaAtaque("B");
        Teg teg = new Teg();

        assertTrue(ronda.finEtapa(list,teg) instanceof RondaReagrupacion);
    }

    @Test
    public void enRondaAtaqueTerminarYRondaReagrupacionSeCreaconELJugadorActualCorrecto(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaAtaque("B");
        Teg teg = new Teg();

        ronda = ronda.finEtapa(list,teg);
        assertTrue(ronda instanceof RondaReagrupacion);
        assertEquals("Ronda de Reagrupacion",ronda.getNombre());
        assertEquals("B",ronda.getJugadorActual());
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeColocar(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.colocarFichas(teg,"A",3));
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeReagruparSiJugadorNoBatallo(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = new Teg();

        assertThrows(PasajeDeFichasNoValidoEnAtaqueException.class,()-> ronda.pasarFichas(teg,"A","B",3));
    }

    @Test
    public void enRondaDeAtaqueSePuedeReagruparSiJugadorGanoBatalla(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(true);

        ronda.atacarACon(teg,"A","B",3);
        ronda.pasarFichas(teg,"A","B",3);
        verify(teg,times(1)).pasarFichas("A","A","B",3);
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeReagruparSiJugadorNoGanoBatalla(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(false);

        ronda.atacarACon(teg,"A","B",3);
        assertThrows(PasajeDeFichasNoValidoEnAtaqueException.class,()-> ronda.pasarFichas(teg,"A","B",3));
        verify(teg,times(0)).pasarFichas("A","A","B",3);
        verify(teg,times(1)).atacarConA("A","A","B",3);
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeReagruparSiNoSePasanFichasAPaisConquistado(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(true);

        ronda.atacarACon(teg,"A","B",3);
        assertThrows(PasajeDeFichasNoValidoEnAtaqueException.class,()-> ronda.pasarFichas(teg,"A","C",3));
        verify(teg,times(0)).pasarFichas("A","A","C",3);
        verify(teg,times(1)).atacarConA("A","A","B",3);
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeReagruparSiNoSePasanFichasDesdePaisAtacante(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(true);

        ronda.atacarACon(teg,"A","B",3);
        assertThrows(PasajeDeFichasNoValidoEnAtaqueException.class,()-> ronda.pasarFichas(teg,"C","B",3));
        verify(teg,times(0)).pasarFichas("A","C","B",3);
        verify(teg,times(1)).atacarConA("A","A","B",3);
    }

    @Test
    public void enRondaDeAtaqueNoSePuedeReagruparSiNoSePasanFichasDesdePaisAtacanteAPaisConquistado(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(true);

        ronda.atacarACon(teg,"A","B",3);
        assertThrows(PasajeDeFichasNoValidoEnAtaqueException.class,()-> ronda.pasarFichas(teg,"B","C",3));
        verify(teg,times(0)).pasarFichas("A","B","C",3);
        verify(teg,times(1)).atacarConA("A","A","B",3);
    }

    @Test
    public void enRondaDeAtaqueSePuedeAtacar(){
        TipoRonda ronda = new RondaAtaque("A");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.atacarConA("A","A","B",3)).thenReturn(true);

        ronda.atacarACon(teg,"A","B",3);
        verify(teg,times(1)).atacarConA("A","A","B",3);
    }

    @Test
    public void cuandoSePasaRondaGanadorSeCreaConElGanador(){
        List<String> list = List.of("A");
        TipoRonda ronda = new RondaReagrupacion(list.get(0), list);
        Teg teg = Mockito.mock(Teg.class);
        when(teg.hayGanador()).thenReturn(true);
        when(teg.getGanador()).thenReturn("ganador");

        ronda = ronda.finEtapa(list,teg);
        assertTrue(ronda instanceof RondaGanador);
        assertEquals("ganador", ronda.getJugadorActual());
    }

    @Test
    public void enRondaGanadorNoSePuedeColocar(){
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.colocarFichas(teg,"A",3));
    }

    @Test
    public void enRondaGanadorNoSePuedeRegrupar(){
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.pasarFichas(teg,"A","B",3));
    }

    @Test
    public void enRondaGanadorNoSePuedeAtacar(){
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.atacarACon(teg,"A","B",3));
    }

    @Test
    public void enRondaGanadorNoSePuedeHacerCanje(){
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.hacerCanje(teg));
    }

    @Test
    public void enRondaGanadorNoSePuedePedirCarta(){
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();

        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.darCartaPais(teg));
    }

    @Test
    public void enRondaGanadorNoSePuedepasarDeEtapa(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaGanador("A");
        Teg teg = new Teg();
        assertEquals("Termino el juego",ronda.getNombre());
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class,()-> ronda.finEtapa(list,teg));
    }

    @Test
    public void primeraRondaLeDaCincoFichasALosJugadores(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(true);
        TipoRonda ronda = new RondaColocacion(new ColocacionPrimeraRonda(),list,teg);

        verify(teg,times(1)).agregarFichasA("A",5);
        verify(teg,times(1)).agregarFichasA("B",5);
        assertEquals("A",ronda.getJugadorActual());
    }

    @Test
    public void segundaRondaLeDaTresFichasALosJugadores(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(true);
        TipoRonda ronda = new RondaColocacion(new ColocacionSegundaRonda(),list,teg);

        verify(teg,times(1)).agregarFichasA("A",3);
        verify(teg,times(1)).agregarFichasA("B",3);
        assertEquals("A",ronda.getJugadorActual());
    }



    @Test
    public void rondaDeColocacionNormalDaFichasSegunTableroALosJugadores(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(true);
        TipoRonda ronda = new RondaColocacion(new ColocacionNormal(),list,teg);

        verify(teg,times(1)).agregarFichasDisponiblesA("A");
        verify(teg,times(1)).agregarFichasDisponiblesA("B");
        assertEquals("A",ronda.getJugadorActual());
    }

    @Test
    public void rondaDeColocacionPasaARondaAtaqueAlTerminar(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(false);
        when(teg.jugadorTieneFichas("A")).thenReturn(false);
        TipoRonda ronda = new RondaColocacion(new ColocacionNormal(),list,teg);

        ronda = ronda.finEtapa(list,teg);
        ronda = ronda.finEtapa(list,teg);
        assertTrue(ronda instanceof RondaAtaque);
        assertEquals("A",ronda.getJugadorActual());
    }

    @Test
    public void primeraRondaColocacionPasaASegundaRondaColocacionCuandoNoHayMasJugadoresYNoTienenFichasYArrancaPorElPrimerJugador(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(false);
        when(teg.jugadorTieneFichas("B")).thenReturn(false);
        TipoRonda ronda = new RondaColocacion(list,teg);

        ronda = ronda.finEtapa(list,teg);
        ronda = ronda.finEtapa(list,teg);
        assertTrue(ronda instanceof RondaColocacion);
        assertEquals("A",ronda.getJugadorActual());
        ronda.colocarFichas(teg,"a",1);
        verify(teg,times(1)).agregarFichasA("A",3);
    }

    @Test
    public void primeraRondaColocacionPasaASiguenteJugadorSiPrimerJugadorYaNotieneFichas(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(false);
        TipoRonda ronda = new RondaColocacion(list,teg);

        ronda = ronda.finEtapa(list,teg);
        assertTrue(ronda instanceof RondaColocacion);
        assertEquals("B",ronda.getJugadorActual());
    }

    @Test
    public void rondaColocacionNoSePuedeTerminarEtapaSiJugadorSigueTeniendoFichas(){
        List<String> list = List.of("A","B");
        Teg teg = Mockito.mock(Teg.class);
        when(teg.jugadorTieneFichas("A")).thenReturn(true);
        TipoRonda ronda = new RondaColocacion(list,teg);

        assertThrows(JugadorSigueTeniendoFichasException.class,()-> ronda.finEtapa(list,teg));
    }

    @Test
    public void enRondaDeReagrupacionSePuedenPasarFichas(){
        List<String> list = List.of("A","B");
        TipoRonda ronda = new RondaReagrupacion("A",list);
        Teg teg = Mockito.mock(Teg.class);

        ronda.pasarFichas(teg,"A","B",3);
        verify(teg,times(1)).pasarFichas("A","A","B",3);
    }

}
