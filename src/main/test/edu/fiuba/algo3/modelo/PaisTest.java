package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.JugadorNoPoseePaisException;
import edu.fiuba.algo3.modelo.excepciones.PaisNoEsLimitrofeException;
import edu.fiuba.algo3.modelo.excepciones.PaisSinSuficientesFichasParaPasarException;
import edu.fiuba.algo3.modelo.ataque.Dados;
import edu.fiuba.algo3.modelo.tablero.Pais;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



public class PaisTest{

    Jugador jugadorUno;
    Jugador jugadorDos;

    @BeforeEach
    void setUp(){
        jugadorUno = new Jugador("Rojo");
        jugadorDos = new Jugador("Amarillo");
        jugadorUno.agregarFichas(15);
        jugadorDos.agregarFichas(15);
    }

     @Test
     public void asignarPaisTest(){
         Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

         paisMio.asignarJugador(jugadorUno);

         assertEquals(jugadorUno,paisMio.getJugador());
         assertEquals(1,paisMio.getFichas());
         assertEquals("ROJO",paisMio.getColorJugador());
     }

    @Test
    public void paisNoSePuedeAsignarDosVeces(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);
        paisMio.asignarJugador(jugadorDos);

        assertEquals(paisMio.getJugador(),jugadorUno);
    }


    @Test
    public void agregarTropasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        Pais paisMio = new Pais("Chile",List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);

        assertThrows(JugadorNoPoseePaisException.class, () -> paisMio.agregarFichas(5,jugadorDos));
    }


    @Test
    public void paisAtacanteNoConquistaPaisDefensor(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));


        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);

        paisAtacante.agregarFichas(4,jugadorUno);
        paisDefensor.agregarFichas(2,jugadorDos);

        assertFalse(paisDefensor.perderFichas(2,paisAtacante));

    }

    @Test
    public void paisConCIncoFichasPierdeTresQuedaConDos(){
        Pais pais = new Pais("Chile",List.of("Argentina","Peru"));

        pais.asignarJugador(jugadorUno);
        pais.agregarFichas(4,jugadorUno);


        assertEquals(3,pais.perderFichas(2));
        assertEquals(3,pais.getFichas());
    }



    @Test
    public void paisAtacanteConquistaPaisYNoCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);

        paisAtacante.agregarFichas(4,jugadorUno);
        paisDefensor.agregarFichas(2,jugadorDos);

        paisDefensor.perderFichas(2,paisAtacante);

        assertTrue(paisDefensor.esDeJugador(jugadorDos));

    }

    @Test
    public void paisAtacanteConquistaPais(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);

        paisAtacante.agregarFichas(4,jugadorUno);
        paisDefensor.agregarFichas(2,jugadorDos);

        assertTrue(paisDefensor.perderFichas(3,paisAtacante));

    }

    @Test
    public void paisAtacanteConquistaPaisYCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        paisAtacante.asignarJugador(jugadorUno);
        paisDefensor.asignarJugador(jugadorDos);

        paisAtacante.agregarFichas(4,jugadorUno);
        paisDefensor.agregarFichas(2,jugadorDos);
        paisDefensor.perderFichas(3,paisAtacante);
        assertTrue(paisDefensor.esDeJugador(jugadorUno));

    }

    @Test
    public void paisConCincoFichasPuedeTirarUnDado(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);
        paisMio.agregarFichas(5,jugadorUno);

        Dados dados = paisMio.tirarDados(1);

        assertEquals(1,dados.cantidadDados());

    }

    @Test
    public void paisConUnaFichaTirarUnDado(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);

        Dados dados = paisMio.tirarDados();

        assertEquals(1,dados.cantidadDados());

    }


    @Test
    public void paisConDoceFichasPuedeTirarComoMaximoTresDados(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);
        paisMio.agregarFichas(11,jugadorUno);

        Dados dados = paisMio.tirarDados();

        assertEquals(3,dados.cantidadDados());

    }

    @Test
    public void paisConDoceFichasPuedeTirarComoMaximoTresDadosAunqueSePidanDadosDeMas(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));

        paisMio.asignarJugador(jugadorUno);
        paisMio.agregarFichas(11,jugadorUno);

        Dados dados = paisMio.tirarDados(6);

        assertEquals(3,dados.cantidadDados());

    }

    @Test
    public void paisConTresFichasNoPuedePasarTresFichas(){
        Pais paisUno = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        paisUno.asignarJugador(jugadorUno);
        paisDos.asignarJugador(jugadorUno);

        paisUno.agregarFichas(2,jugadorUno);
        paisDos.agregarFichas(3,jugadorUno);

        assertThrows(PaisSinSuficientesFichasParaPasarException.class, ()->paisUno.pasarFichasA(paisDos, 3));
    }

    @Test
    public void paisNoPuedePasarFichasQueNoEsDeJugador(){
        Pais paisUno = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        paisUno.asignarJugador(jugadorUno);
        paisDos.asignarJugador(jugadorDos);

        paisUno.agregarFichas(3,jugadorUno);
        paisDos.agregarFichas(3,jugadorDos);

        assertThrows(JugadorNoPoseePaisException.class, ()->paisUno.pasarFichasA(paisDos, 2));
    }

    @Test
    public void paisConTresFichasPuedePasarDos(){
        Pais paisUno = new Pais("Chile", List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo", List.of("Argentina","Chile"));

        paisUno.asignarJugador(jugadorUno);
        paisDos.asignarJugador(jugadorUno);

        paisUno.agregarFichas(2,jugadorUno);
        paisDos.agregarFichas(2,jugadorUno);

        paisUno.pasarFichasA(paisDos, 2);
        assertEquals(4,paisDos.perderFichas(1));
    }

    @Test
    public void paisNoPuedePasarFichasAUnPaisNoLimitrofe(){
        Pais paisUno = new Pais("Chile",List.of("Argentina","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        paisUno.asignarJugador(jugadorUno);
        paisDos.asignarJugador(jugadorUno);

        paisUno.agregarFichas(2,jugadorUno);
        paisDos.agregarFichas(2,jugadorUno);

        assertThrows(PaisNoEsLimitrofeException.class, ()->paisUno.pasarFichasA(paisDos, 2));
    }


}