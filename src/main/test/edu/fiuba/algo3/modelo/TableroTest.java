package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class TableroTest {

    @Test
    public void agregarFichasAUnPaisVacioEntoncesElPaisEsDelJugadorTest(){
        Pais pais = new Pais("Argentina", List.of("Chile","Brazil"));
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(pais);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        Jugador jugador = new Jugador("Julian");
        tablero.agregarFichas(5,jugador, "Argentina");

        assertTrue(pais.esDeJugador(jugador));
    }

    @Test
    public void agregarFichasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        Pais pais = new Pais("Argentina", List.of("Chile","Brazil"));
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(pais);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        tablero.agregarPais(pais);

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");

        tablero.agregarFichas(5,jugadorUno, "Argentina");

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5,jugadorDos, "Argentina"));
    }

    @Test
    public void ataque(){
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));
        ArrayList<Pais> paises = new ArrayList<>();
        paises.add(pais1);
        paises.add(pais2);
        Tablero tablero = new Tablero(new ArrayList<>(),paises);

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");

        tablero.agregarFichas(5,jugadorUno, "Argentina");

        assertThrows(JugadorNoPoseePaisException.class, () -> tablero.agregarFichas(5,jugadorDos, "Argentina"));
    }
    /*
    @Test
    public void paisAtacanteNoPuedeTirarCeroDados() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        tablero.agregarFichas(3, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertThrows(NoSePuedenCrearCeroDados.class, () -> tablero.atacar("Argentina","Chile",0));
    }

    @Test
    public void paisAtacanteConUnaFichaNoPuedeAtacar() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        tablero.agregarFichas(1, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertThrows(EjercitoConUnaFichaNoPuedeAtacar.class, () -> tablero.atacar("Argentina","Chile",1));
    }

    @Test
    public void paisAtacanteNoPuedeTirarDadosDeMas() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        tablero.agregarFichas(2, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertThrows(EjercitoNoPuedeTirarEsaCantidadDeDados.class, () -> tablero.atacar("Argentina","Chile",3));
    }

    @Test
    public void paisAtacanteNoConquistaPais() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        tablero.agregarFichas(5, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorDos,"Chile");

        assertFalse(tablero.atacar("Argentina","Chile",2));

    }

/*    @Test
    // NO SUPIMOS HACERLO EN MOQUITO
    public void paisAtacanteConquistaPaisDefensor() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Martin");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));
        Pais pais1 = Mockito.mock(Pais.class);
        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);
        Dados dados = Mockito.mock(Dados.class);
        int[] conjunto = {0, 3};

        when(dados.comparadorDeDados(any(Dados.class))).thenReturn(conjunto);
        when(pais1.tirarDados(3)).thenReturn(dados);
        when(pais1.getJugador()).thenReturn(jugadorUno);
        tablero.agregarFichas(3, jugadorDos, "Chile");
        //tablero.agregarFichas(5, jugadorUno, "Argentina");

        assertTrue(tablero.atacar("Argentina", "Chile", 3));
    }
    }

    @Test
    public void JugadorNoPuedeAtacarseASiMismo() {
        Tablero tablero = new Tablero();
        Jugador jugadorUno = new Jugador("Julian");
        Pais pais1 = new Pais("Argentina", List.of("Chile","Brazil"));
        Pais pais2 = new Pais("Chile",List.of("Argentina","Peru"));

        tablero.agregarPais(pais1);
        tablero.agregarPais(pais2);

        tablero.agregarFichas(4, jugadorUno,"Argentina");
        tablero.agregarFichas(5, jugadorUno,"Chile");

        assertThrows(AtaqueNoValido.class, () -> tablero.atacar("Argentina","Chile",1));

    }*/
}