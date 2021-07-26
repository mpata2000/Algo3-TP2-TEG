package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ColocacionSegundaRonda implements TipoColocacion{
    @Override
    public void colocarFichas(String unJugador, Teg teg){
        teg.agregarFichasA(unJugador,3);
    }

    @Override
    public TipoRonda pasarDeRonda(List<String> jugadores){
        return new RondaAtaque(jugadores);
    }
}
