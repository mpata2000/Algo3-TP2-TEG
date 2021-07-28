package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ColocacionPrimeraRonda extends ColocacionNormal{
    @Override
    public void colocarFichas(String unJugador, Teg teg){
        teg.agregarFichasA(unJugador,5);
    }

    @Override
    public TipoRonda pasarDeRonda(List<String> jugadores){
        return new RondaColocacion(new ColocacionSegundaRonda(),jugadores);

    }
}
