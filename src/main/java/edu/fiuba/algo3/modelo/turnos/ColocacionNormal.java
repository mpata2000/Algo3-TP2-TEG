package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ColocacionNormal implements TipoColocacion{
    @Override
    public void colocarFichas(String unJugador, Teg teg){
        teg.agregarFichasDisponiblesA(unJugador);
    }

    @Override
    public TipoRonda pasarDeRonda(List<String> jugadores){
        return  new RondaAtaque(jugadores.get(0));
    }
}
