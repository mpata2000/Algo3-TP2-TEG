package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.MazoDeCartasPais;
import edu.fiuba.algo3.modelo.tablero.JugadorNoTieneSuficientesFichasException;
import edu.fiuba.algo3.modelo.ataque.Dados;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.rondas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TurnosTest {

    ArrayList<Pais> paisesOceania= new ArrayList<>();
    ArrayList<Pais> paises= new ArrayList<>();
    ArrayList<Pais> paisesAsia= new ArrayList<>();
    ArrayList<Continente> continentes= new ArrayList<>();
    HashMap<String, Jugador> jugadores = new HashMap<>();
    Tablero tablero;
    ObjetivoTeg objetivoGana ;
    ObjetivoTeg objetivoPierde ;

    @BeforeEach
    void setUp() {
        paisesOceania.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paisesOceania.add(new Pais("Sumatra", List.of("Australia","India")));
        paisesOceania.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paisesOceania.add(new Pais("Java", List.of("Australia")));
        paisesAsia.add(new Pais("China", List.of("Australia","Malasia")));
        paisesAsia.add(new Pais("Japon", List.of("Australia","India")));
        paisesAsia.add(new Pais("Rusia", List.of("Chile","Sumatra","Java","Borneo")));
        continentes.add(new Continente("Oceania",2,paisesOceania));
        continentes.add(new Continente("Asia",2,paisesAsia));
        jugadores.put("AMARILLO",new Jugador("Amarillo"));
        jugadores.put("ROJO",new Jugador("Rojo"));
        paises.addAll(paisesAsia);
        paises.addAll(paisesOceania);
        objetivoGana = Mockito.mock(ObjetivoTeg.class);
        objetivoPierde = Mockito.mock(ObjetivoTeg.class);
        when(objetivoGana.cumplioObjetivo(any(Teg.class))).thenReturn(true);
        when(objetivoPierde.cumplioObjetivo(any(Teg.class))).thenReturn(false);
    }

    @Test
    public void primeraRondaInicialCadaJugadorTieneCincoFichasParaPoner(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        paisesAsia.get(0).asignarJugador(jugadores.get("AMARILLO"));

        //La cantidad de fichas se calcula la primera vez que trata de poner fichas
        turnos.colocarFichas("China",0);
        assertEquals(5,jugadores.get("AMARILLO").sacarFichas(0));
    }



    @Test
    public void segundaRondaInicialCadaJugadorTieneCincoFichasParaPoner(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));


        paisesAsia.get(0).asignarJugador(jugadores.get("AMARILLO"));
        paisesAsia.get(1).asignarJugador(jugadores.get("ROJO"));


        turnos.colocarFichas("China",5);
        turnos.finEtapa();
        turnos.colocarFichas("Japon",5);
        turnos.finEtapa();

        //La cantidad de fichas se calcula la primera vez que trata de poner fichas
        turnos.colocarFichas("China",0);
        assertEquals(3,jugadores.get("AMARILLO").sacarFichas(0));
    }

    @Test
    public void jugadorNoPuedePonerSieteFichasEnLaPrimeraRondaInicial(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));


        paisesAsia.get(0).asignarJugador(jugadores.get("AMARILLO"));
        paisesAsia.get(1).asignarJugador(jugadores.get("ROJO"));


        assertThrows(JugadorNoTieneSuficientesFichasException.class,()-> turnos.colocarFichas("China",7));

    }

    @Test
    public void jugadorNoPuedePonerCuatrFichasEnLaSegundaRondaInicial(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        paisesAsia.get(0).asignarJugador(jugadores.get("AMARILLO"));
        paisesAsia.get(1).asignarJugador(jugadores.get("ROJO"));
        turnos.colocarFichas("China",5);
        turnos.finEtapa();
        turnos.colocarFichas("Japon",5);
        turnos.finEtapa();

        assertThrows(JugadorNoTieneSuficientesFichasException.class,()-> turnos.colocarFichas("China",4));
        assertThrows(JugadorSigueTeniendoFichasException.class, turnos::finEtapa);


    }

    @Test
    public void NoSePuedeComenzarJuegoCOn(){

        Turnos turnos = new Turnos();
        List<String> list = List.of("Amarillo");
        assertThrows(LimiteDeJugadoresException.class,()-> turnos.comenzarJuego(list));
    }

    @Test
    public void TurnosPuedeComenzarConDosJugadoresYArrancaEnRondaColocacion(){
        Turnos turnos = new Turnos();
        turnos.comenzarJuego(List.of("Amarillo","Rojo"));
        assertTrue(turnos.devolverRondaActual() instanceof RondaColocacion);
    }

    @Test
    public void TurnoEnRondaDeColocacionNoSePuedeDarCarta(){
        Teg teg = Mockito.mock(Teg.class);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class, turnos :: darCartaPais);
    }

    @Test
    public void TurnoEnRondaDeColocacionSePuedeHacerCanjeSiJugadorPuede(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.hacerCanjeJugador("Amarillo")).thenReturn(true);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertTrue(turnos.realizarCanje());
    }

    @Test
    public void TurnoEnRondaDeColocacionDevuelveFalseCanjeSiJugadorNoPuedeHacerCanje(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.hacerCanjeJugador("Amarillo")).thenReturn(false);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertFalse(turnos.realizarCanje());
    }

    @Test
    public void NoSePuedeAgregarMasDeSeisJugadores(){
        List<String> list = List.of("Amarillo", "Rojo", "Verde", "Magenta","Negro","Azul","Celeste");
        Turnos turnos = new Turnos();
        assertThrows(LimiteDeJugadoresException.class, ()->  turnos.comenzarJuego(list));
    }

    @Test
    public void PrimerasDosROndasEntreDosJugadoresPuedenColocarSusOchoFichasIniciales(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("AMARILLO"));
        }

        for (Pais pais : paisesOceania){
            pais.asignarJugador(jugadores.get("ROJO"));
        }

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("China",5);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("Borneo",5);
        turnos.finEtapa();

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("Rusia",3);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("Australia",3);
        turnos.finEtapa();


        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        assertEquals(6,paisesAsia.get(0).perderFichas(0));
        assertEquals(4,paisesAsia.get(2).perderFichas(0));
        assertEquals(6,paisesOceania.get(0).perderFichas(0));
        assertEquals(4,paisesOceania.get(2).perderFichas(0));
    }

    @Test
    public void ColocarEjercitos2Paises3jugadores(){
        jugadores.put("VERDE",new Jugador("Verde"));
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo","Verde"));

        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("ROJO"));
        }

        (paisesOceania.get(0)).asignarJugador(jugadores.get("AMARILLO"));
        (paisesOceania.get(1)).asignarJugador(jugadores.get("AMARILLO"));
        (paisesOceania.get(2)).asignarJugador(jugadores.get("VERDE"));
        (paisesOceania.get(3)).asignarJugador(jugadores.get("VERDE"));

        turnos.colocarFichas("Borneo",5);
        turnos.finEtapa();
        turnos.colocarFichas("China",5);
        turnos.finEtapa();
        turnos.colocarFichas("Australia",5);
        turnos.finEtapa();
        turnos.colocarFichas("Borneo",3);
        turnos.finEtapa();
        turnos.colocarFichas("Rusia",3);
        turnos.finEtapa();
        turnos.colocarFichas("Australia",3);
        turnos.finEtapa();

        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        assertTrue(continentes.get(1).esDeJugador(jugadores.get("ROJO")));
    }


    @Test
    public void TurnoEnRondaDeAtaqueNoSePuedeHacerCanje(){
        Teg teg = Mockito.mock(Teg.class);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();
        turnos.finEtapa();

        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class, turnos ::realizarCanje);
    }

    @Test
    public void TurnoEnRondaDeAtaqueNoSePuedeDarCartaPais(){
        Teg teg = Mockito.mock(Teg.class);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();
        turnos.finEtapa();


        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class, turnos :: darCartaPais);
    }

    @Test
    public void ColocarEjercitos2Paises2jugadoresAtque(){
        Pais paisAtacante = Mockito.mock(Pais.class);
        paises.set(5,paisAtacante);

        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0,3};
        when(dados.compararDados(any(Dados.class))).thenReturn(conjunto);
        when(paisAtacante.getNombre()).thenReturn("Australia");
        when(paisAtacante.tirarDados(3)).thenReturn(dados);
        when(paisAtacante.getJugador()).thenReturn(jugadores.get("AMARILLO"));
        when(paisAtacante.esDeJugador(jugadores.get("AMARILLO"))).thenReturn(true);
        when(paisAtacante.esAdyacente(paisesOceania.get(0))).thenReturn(true);
        when(paisAtacante.esAdyacente(paisesOceania.get(3))).thenReturn(true);

        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        paisAtacante.asignarJugador(jugadores.get("AMARILLO"));
        (paisesOceania.get(1)).asignarJugador(jugadores.get("AMARILLO"));
        (paisesAsia.get(0)).asignarJugador(jugadores.get("ROJO"));
        (paisesOceania.get(0)).asignarJugador(jugadores.get("ROJO"));
        (paisesOceania.get(3)).asignarJugador(jugadores.get("ROJO"));

        turnos.colocarFichas("Sumatra",5);
        turnos.finEtapa();
        turnos.colocarFichas("China",5);
        turnos.finEtapa();
        turnos.colocarFichas("Sumatra",3);
        turnos.finEtapa();
        turnos.colocarFichas("China",3);
        turnos.finEtapa();
        jugadores.get("AMARILLO").agregarFichas(2);
        turnos.atacarACon("Australia","Borneo",3);
        turnos.atacarACon("Australia","Java",3);
        assertTrue((tablero.getPais("Borneo")).esDeJugador(jugadores.get("AMARILLO")));
        assertTrue((tablero.getPais("Java")).esDeJugador(jugadores.get("AMARILLO")));
    }

    @Test
    public void reagruparDespuesDeAtacar(){
        tablero = new Tablero(continentes,paises);
        jugadores.get("AMARILLO").darObjetivo(objetivoPierde);
        jugadores.get("ROJO").darObjetivo(objetivoPierde);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("AMARILLO"));
        }

        for (Pais pais : paisesOceania){
            pais.asignarJugador(jugadores.get("ROJO"));
        }

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("China",5);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("Borneo",5);
        turnos.finEtapa();

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("Rusia",3);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("Australia",3);
        turnos.finEtapa();


        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        turnos.finEtapa();
        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        turnos.finEtapa();
        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        assertEquals("Rojo",turnos.getJugadorActual());

        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        turnos.finEtapa();
        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        turnos.finEtapa();
        assertTrue(turnos.devolverRondaActual() instanceof RondaColocacion);
    }


    @Test
    public void TurnoEnRondaReagrupacionSePuedenPasarFichasCorrectamente(){
        tablero = new Tablero(continentes,paises);
        jugadores.get("AMARILLO").darObjetivo(objetivoPierde);
        jugadores.get("ROJO").darObjetivo(objetivoPierde);
        Teg teg = new Teg(tablero,jugadores,new MazoDeCartasPais());
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("ROJO"));
        }

        for (Pais pais : paisesOceania){
            pais.asignarJugador(jugadores.get("AMARILLO"));
        }

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("Borneo",5);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("China",5);
        turnos.finEtapa();

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarFichas("Australia",3);
        turnos.finEtapa();

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarFichas("Rusia",3);
        turnos.finEtapa();


        assertTrue(turnos.devolverRondaActual() instanceof RondaAtaque);
        turnos.finEtapa();
        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        turnos.pasarFichas("Borneo","Australia",3);
        assertEquals(3,paisesOceania.get(0).perderFichas(0));
        assertEquals(7,paisesOceania.get(2).perderFichas(0));
    }


    @Test
    public void TurnoEnRondaReagrupacionNoSePuedeHacerCanje(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.hacerCanjeJugador("Amarillo")).thenReturn(true);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));


        turnos.finEtapa();
        turnos.finEtapa();


        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();

        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        assertThrows(NoSePuedeHacerEstaAccionEnEstaRondaException.class, turnos ::realizarCanje);
    }


    @Test
    public void TurnoEnRondaReagrupacionPuedeDarCartaSiJugadorPuede(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.darCartaPaisA("Amarillo")).thenReturn(true);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();

        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        assertTrue(turnos.darCartaPais());
    }

    @Test
    public void TurnoEnRondaReagrupacionNoPuedeDarCartaSiJugadorNoPuede(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.darCartaPaisA("Amarillo")).thenReturn(false);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();
        turnos.finEtapa();

        turnos.finEtapa();

        assertTrue(turnos.devolverRondaActual() instanceof RondaReagrupacion);
        assertFalse(turnos.darCartaPais());
    }

    @Test
    public void TurnosDevuelveElObjetivoDelJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.textoObjetivo("Amarillo")).thenReturn("OK");
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals("OK", turnos.textoDeObjetivo());
        verify(teg,times(1)).textoObjetivo("Amarillo");
    }

    @Test
    public void TurnosDevuelveLasFichasJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        when(teg.getFichas("Amarillo")).thenReturn(1);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(1, turnos.getFichas());
        verify(teg,times(1)).getFichas("Amarillo");
    }

    @Test
    public void TurnosDevuelveLosPaisesPorContinenteDeJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        List<String> list = List.of("A","B");
        when(teg.getPaisesPorContinentes("Amarillo")).thenReturn(list);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(list, turnos.getPaisesPorContinentes());
        verify(teg,times(1)).getPaisesPorContinentes("Amarillo");
    }


    @Test
    public void TurnosDevuelveLasCatasPaisDelJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        List<String> list = List.of("A","B");
        when(teg.getCartasJugador("Amarillo")).thenReturn(list);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(list, turnos.getCartasJugador());
        verify(teg,times(1)).getCartasJugador("Amarillo");
    }

    @Test
    public void TurnosDevuelveLosPaisesDelJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        when(teg.getPaisesJugador("Amarillo")).thenReturn(list);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(list, turnos.getPaisesJugador());
        verify(teg,times(1)).getPaisesJugador("Amarillo");
    }

    @Test
    public void TurnosDevuelveLosPaisesQueNoSonDelJugadorActual(){
        Teg teg = Mockito.mock(Teg.class);
        List<Pais> list = List.of(new Pais("A",List.of("A")),new Pais("A",List.of("A")));
        when(teg.getPaisesEnemigos("Amarillo")).thenReturn(list);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(list, turnos.getPaisesEnemigos());
        verify(teg,times(1)).getPaisesEnemigos("Amarillo");
    }

    @Test
    public void TurnosGetInstanceCreaunaSolaInstancia(){
        Turnos turno = Turnos.getInstance();
        assertEquals(turno, Turnos.getInstance());
    }
}
