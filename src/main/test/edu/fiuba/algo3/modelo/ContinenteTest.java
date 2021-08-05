package edu.fiuba.algo3.modelo;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.tablero.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ContinenteTest {
    ArrayList<Pais> paises= new ArrayList<>();
    Jugador jugadorUno;
    Jugador jugadorDos;
    Continente continente;

    @BeforeEach
    void setUp() {
        paises.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paises.add(new Pais("Sumatra", List.of("Australia","India")));
        paises.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paises.add(new Pais("Java", List.of("Australia")));
        continente = new Continente("Oceania",2,paises);
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
    }

    @Test
    public void contineteSeCreaConElNombreCorrecto(){
        assertEquals("Oceania",continente.getNombre());
    }

    @Test
    public void contineteSeCreaConLaCantidadDeFichasExtraCorrecta(){

        assertEquals(2,continente.getFichas());
    }

    @Test
    public void contineteSeCreaConLosPaisesCorrectos(){

        assertEquals(paises,continente.getPaises());
    }

    @Test
    public void contineteEsDeUnJugadorSiTieneTodosLosPaises(){
        for(Pais pais : paises) {
            pais.asignarJugador(jugadorUno);
        }
        assertEquals(4, continente.cantidadPaisesDe(jugadorUno));
        assertTrue(continente.esDeJugador(jugadorUno));
    }

    @Test
    public void contineteNoEsDeUnJugadorNoTieneNingunPaisDelContienete(){
        assertFalse(continente.esDeJugador(jugadorUno));
    }

    @Test
    public void contineteNoEsDeUnJugadorSiTieneDosPaisesDelContienete(){

        paises.get(0).asignarJugador(jugadorUno);
        paises.get(1).asignarJugador(jugadorUno);

        assertEquals(2, continente.cantidadPaisesDe(jugadorUno));
        assertFalse(continente.esDeJugador(jugadorUno));
    }

    @Test
    public void unJugadorTieneDosPaisesYOtroTambien(){
        paises.get(0).asignarJugador(jugadorUno);
        paises.get(1).asignarJugador(jugadorUno);
        paises.get(2).asignarJugador(jugadorDos);
        paises.get(3).asignarJugador(jugadorDos);

        assertEquals(2, continente.cantidadPaisesDe(jugadorUno));
        assertEquals(2, continente.cantidadPaisesDe(jugadorDos));
        assertFalse(continente.esDeJugador(jugadorUno));
        assertFalse(continente.esDeJugador(jugadorDos));
    }

    @Test
    public void elContienteEsDelJugadorYSeleAgreganFichas(){
        for(Pais pais : paises) {
            pais.asignarJugador(jugadorUno);
        }
        assertEquals(4, continente.cantidadPaisesDe(jugadorUno));
        assertTrue(continente.esDeJugador(jugadorUno));

        continente.agregarFichasExtraA(jugadorUno);
        assertEquals(2,jugadorUno.sacarFichas(0));
    }

    @Test
    public void elContienteNoEsDelJugadorEntoncesNoSeLeAgregaFichas(){
        assertFalse(continente.esDeJugador(jugadorUno));

        continente.agregarFichasExtraA(jugadorUno);
        assertEquals(0,jugadorUno.sacarFichas(0));
    }

    @Test
    public void elContienteDevueleUnStringDeLaCantidadDePaisesQueTieneElJugador(){
        assertEquals("Oceania: 0/4",continente.paisesDeJugador(jugadorUno));
        for(Pais pais : paises) {
            pais.asignarJugador(jugadorUno);
        }
        assertEquals("Oceania: 4/4",continente.paisesDeJugador(jugadorUno));
    }
}
