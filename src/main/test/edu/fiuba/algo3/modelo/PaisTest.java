package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.JugadorNoPoseePaisException;
import edu.fiuba.algo3.excepciones.PaisNoEsLimitrofe;
import edu.fiuba.algo3.excepciones.PaisSinSuficientesFichasParaPasar;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;



public class PaisTest{

     @Test
     public void colocacionDeEjercitoEnPaisTest(){
         Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));
         Jugador jugador = new Jugador("julio");
         paisMio.agregarFichas(5,jugador);

         assertEquals(paisMio.getJugador(),jugador);
     }


    @Test
    public void agregarTropasAUnPaisQueNoEsDelJugadorLanzaExcepcionTest(){
        Pais paisMio = new Pais("Chile",List.of("Argentina","Peru"));
        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisMio.agregarFichas(5,jugadorUno);

        assertThrows(JugadorNoPoseePaisException.class, () -> paisMio.agregarFichas(5,jugadorDos));
    }


    @Test
    public void paisAtacanteNoConquistaPaisDefensor(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);

        assertFalse(paisDefensor.perderFichas(2,paisAtacante));

    }

    @Test
    public void paisConCIncoFichasPierdeTresQuedaConDos(){
        Pais pais = new Pais("Chile",List.of("Argentina","Peru"));

        Jugador jugadorUno = new Jugador("Julian");

        pais.agregarFichas(5,jugadorUno);


        assertEquals(3,pais.perderFichas(2));
    }



    @Test
    public void paisAtacanteConquistaPaisYNoCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);

        paisDefensor.perderFichas(2,paisAtacante);

        assertTrue(paisDefensor.esDeJugador(jugadorDos));

    }

    @Test
    public void paisAtacanteConquistaPais(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);

        assertTrue(paisDefensor.perderFichas(3,paisAtacante));

    }

    @Test
    public void paisAtacanteConquistaPaisYCambiaDeDuenio(){
        Pais paisAtacante = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDefensor = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Sofia");

        paisAtacante.agregarFichas(5,jugadorUno);
        paisDefensor.agregarFichas(3,jugadorDos);
        paisDefensor.perderFichas(3,paisAtacante);
        assertTrue(paisDefensor.esDeJugador(jugadorUno));

    }

    @Test
    public void paisConCincoFichasPuedeTirarUnDado(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(5,jugador);

        Dados dados = paisMio.tirarDados(1);

        assertEquals(1,dados.cantidadDados());

    }

    @Test
    public void paisConUnaFichaTirarUnDado(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(1,jugador);

        Dados dados = paisMio.tirarDados();

        assertEquals(1,dados.cantidadDados());

    }


    @Test
    public void paisConOnceFichasPuedeTirarComoMaximoTresDados(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(11,jugador);

        Dados dados = paisMio.tirarDados();

        assertEquals(3,dados.cantidadDados());

    }

    @Test
    public void paisConOnceFichasPuedeTirarComoMaximoTresDadosAunqueSePidanDadosDeMas(){
        Pais paisMio = new Pais("Chile", List.of("Argentina","Peru"));
        Jugador jugador = new Jugador("julio");
        paisMio.agregarFichas(11,jugador);

        Dados dados = paisMio.tirarDados(6);

        assertEquals(3,dados.cantidadDados());

    }

    @Test
    public void paisConTresFichasNoPuedePasarTresFichas(){
        Pais paisUno = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");

        paisUno.agregarFichas(3,jugadorUno);
        paisDos.agregarFichas(3,jugadorUno);

        assertThrows(PaisSinSuficientesFichasParaPasar.class, ()->paisUno.pasarFichasA(paisDos, 3));
    }

    @Test
    public void paisNoPuedePasarFichasQueNoEsDeJugador(){
        Pais paisUno = new Pais("Chile",List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");
        Jugador jugadorDos = new Jugador("Juan");

        paisUno.agregarFichas(3,jugadorUno);
        paisDos.agregarFichas(3,jugadorDos);

        assertThrows(JugadorNoPoseePaisException.class, ()->paisUno.pasarFichasA(paisDos, 2));
    }

    @Test
    public void paisConTresFichasPuedePasarDos(){
        Pais paisUno = new Pais("Chile", List.of("Algo","Peru"));
        Pais paisDos = new Pais("Algo", List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");

        paisUno.agregarFichas(3,jugadorUno);
        paisDos.agregarFichas(3,jugadorUno);

        paisUno.pasarFichasA(paisDos, 2);
        //Todo: revisar el assert
        assertEquals(4,paisDos.perderFichas(1));
    }

    @Test
    public void paisNoPuedePasarFichasAUnPaisNoLimitrofe(){
        Pais paisUno = new Pais("Chile",List.of("Argentina","Peru"));
        Pais paisDos = new Pais("Algo",List.of("Argentina","Chile"));

        Jugador jugadorUno = new Jugador("Julian");

        paisUno.agregarFichas(3,jugadorUno);
        paisDos.agregarFichas(3,jugadorUno);

        assertThrows(PaisNoEsLimitrofe.class, ()->paisUno.pasarFichasA(paisDos, 2));
    }


}