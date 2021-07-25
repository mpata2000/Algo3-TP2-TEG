package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.JugadorNoTieneSuficientesFichas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TurnosTest {

    ArrayList<Pais> paisesOceania= new ArrayList<>();
    ArrayList<Pais> paises= new ArrayList<>();
    ArrayList<Pais> paisesAsia= new ArrayList<>();
    ArrayList<Continente> continentes= new ArrayList<>();
    HashMap<String, Jugador> jugadores = new HashMap<>();
    Tablero tablero;

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
        jugadores.put("Amarillo",new Jugador("Amarillo"));
        jugadores.put("Rojo",new Jugador("Rojo"));
        paises.addAll(paisesAsia);
        paises.addAll(paisesOceania);

    }

    @Test
    public void primeraRondaInicialCadaJugadorTieneCincoFichasParaPoner(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        assertEquals(5,jugadores.get("Amarillo").sacarFichas(0));
        assertEquals(5,jugadores.get("Rojo").sacarFichas(0));
    }



    @Test
    public void segundaRondaInicialCadaJugadorTieneCincoFichasParaPoner(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));


        paisesAsia.get(0).asignarJugador(jugadores.get("Amarillo"));
        paisesAsia.get(1).asignarJugador(jugadores.get("Rojo"));


        turnos.colocarEjercitos("China",5);
        turnos.colocarEjercitos("Japon",5);

        assertEquals(3,jugadores.get("Amarillo").sacarFichas(0));
        assertEquals(3,jugadores.get("Rojo").sacarFichas(0));
    }

    @Test
    public void jugadorNoPuedePonerSieteFichasEnLaPrimeraRondaInicial(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));


        paisesAsia.get(0).asignarJugador(jugadores.get("Amarillo"));
        paisesAsia.get(1).asignarJugador(jugadores.get("Rojo"));


        assertThrows(JugadorNoTieneSuficientesFichas.class,()-> turnos.colocarEjercitos("China",7));

    }

    @Test
    public void jugadorNoPuedePonerCuatrFichasEnLaSegundaRondaInicial(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        paisesAsia.get(0).asignarJugador(jugadores.get("Amarillo"));
        paisesAsia.get(1).asignarJugador(jugadores.get("Rojo"));
        turnos.colocarEjercitos("China",5);
        turnos.colocarEjercitos("Japon",5);

        assertThrows(JugadorNoTieneSuficientesFichas.class,()-> turnos.colocarEjercitos("China",4));

    }

    @Test
    public void SiUnJugadorPoneCincoFichasYDespuesTresEsRondaAtaque(){

        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitos("Argentina",5);
        turnos.colocarEjercitos("Argentina",3);
        assertTrue(turnos.devolverRondaActual().esAtaque());
    }

    @Test
    public void jugadorPoneLasOchoFichasInicialesYFinalizaSuAtaqueEsRondaDeReagrupacion(){
        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitos("Argentina",5);
        turnos.colocarEjercitos("Argentina",3);
        turnos.finAtaque();
        assertTrue(turnos.devolverRondaActual().esReagrupacion());
    }

    @Test
    public void jugadorPoneLasOchoFichasInicialesYFinalizaSuAtaqueDosVecesEsRondaDeReagrupacion(){
        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitos("Argentina",5);
        turnos.colocarEjercitos("Argentina",3);
        turnos.finAtaque();
        turnos.finAtaque();
        assertTrue(turnos.devolverRondaActual().esReagrupacion());
    }

    @Test
    public void jugadorPoneLasOchoFichasInicialesYFinalizaSuAtaqueYreagrupacionEsRondaDeColocacion(){
        Turnos turnos = new Turnos();
        turnos.agregarJugador("Amarillo");
        turnos.comenzarJuego();
        turnos.colocarEjercitos("Argentina",5);
        turnos.colocarEjercitos("Argentina",3);
        turnos.finAtaque();
        turnos.finReagrupacion();
        assertTrue(turnos.devolverRondaActual().esColocacion());
    }

    @Test
    public void ColocarEjercitos2Paises2jugadores(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("Amarillo"));
        }

        for (Pais pais : paisesOceania){
            pais.asignarJugador(jugadores.get("Rojo"));
        }

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarEjercitos("China",5);

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Borneo",5);

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Rusia",3);

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Australia",3);


        assertTrue(turnos.devolverRondaActual().esAtaque());
        assertEquals(6,paisesAsia.get(0).perderFichas(0));
        assertEquals(4,paisesAsia.get(2).perderFichas(0));
        assertEquals(6,paisesOceania.get(0).perderFichas(0));
        assertEquals(4,paisesOceania.get(2).perderFichas(0));
    }

    @Test
    public void ColocarEjercitos2Paises3jugadores(){
        jugadores.put("Verde",new Jugador("Verde"));
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("Rojo"));
        }

        (paisesOceania.get(0)).asignarJugador(jugadores.get("Amarillo"));
        (paisesOceania.get(1)).asignarJugador(jugadores.get("Amarillo"));
        (paisesOceania.get(2)).asignarJugador(jugadores.get("Verde"));
        (paisesOceania.get(3)).asignarJugador(jugadores.get("Verde"));

        turnos.colocarEjercitos("Borneo",5);
        turnos.colocarEjercitos("China",5);
        turnos.colocarEjercitos("Borneo",3);
        turnos.colocarEjercitos("Rusia",3);
        turnos.colocarEjercitos("Australia",8);

        assertTrue(turnos.devolverRondaActual().esAtaque());
        assertTrue(continentes.get(1).esDeJugador(jugadores.get("Rojo")));
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
        when(paisAtacante.getJugador()).thenReturn(jugadores.get("Amarillo"));
        when(paisAtacante.esDeJugador(jugadores.get("Amarillo"))).thenReturn(true);
        when(paisAtacante.esAdyacente(paisesOceania.get(0))).thenReturn(true);
        when(paisAtacante.esAdyacente(paisesOceania.get(3))).thenReturn(true);

        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));

        paisAtacante.asignarJugador(jugadores.get("Amarillo"));
        (paisesOceania.get(1)).asignarJugador(jugadores.get("Amarillo"));
        (paisesAsia.get(0)).asignarJugador(jugadores.get("Rojo"));
        (paisesOceania.get(0)).asignarJugador(jugadores.get("Rojo"));
        (paisesOceania.get(3)).asignarJugador(jugadores.get("Rojo"));

        turnos.colocarEjercitos("Sumatra",5);
        turnos.colocarEjercitos("China",5);
        turnos.colocarEjercitos("Sumatra",3);
        turnos.colocarEjercitos("China",3);
        jugadores.get("Amarillo").agregarFichas(2);
        turnos.atacar("Australia","Borneo",3);
        turnos.atacar("Australia","Java",3);
        assertTrue((tablero.getPais("Borneo")).esDeJugador(jugadores.get("Amarillo")));
        assertTrue((tablero.getPais("Java")).esDeJugador(jugadores.get("Amarillo")));
    }

    @Test
    public void reagruparDespuesDeAtacar(){
        tablero = new Tablero(continentes,paises);
        Teg teg = new Teg(tablero,jugadores);
        Turnos turnos = new Turnos(teg,List.of("Amarillo","Rojo"));
        for (Pais pais : paisesAsia){
            pais.asignarJugador(jugadores.get("Amarillo"));
        }

        for (Pais pais : paisesOceania){
            pais.asignarJugador(jugadores.get("Rojo"));
        }

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarEjercitos("China",5);

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Borneo",5);

        assertEquals("Amarillo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Rusia",3);

        assertEquals("Rojo",turnos.getJugadorActual());
        turnos.colocarEjercitos("Australia",3);


        assertTrue(turnos.devolverRondaActual().esAtaque());
        turnos.finAtaque();
        assertTrue(turnos.devolverRondaActual().esReagrupacion());
        turnos.finReagrupacion();
        assertTrue(turnos.devolverRondaActual().esAtaque());
        assertEquals("Rojo",turnos.getJugadorActual());

        assertTrue(turnos.devolverRondaActual().esAtaque());
        turnos.finAtaque();
        assertTrue(turnos.devolverRondaActual().esReagrupacion());
        turnos.finReagrupacion();
        assertTrue(turnos.devolverRondaActual().esColocacion());
    }
}
