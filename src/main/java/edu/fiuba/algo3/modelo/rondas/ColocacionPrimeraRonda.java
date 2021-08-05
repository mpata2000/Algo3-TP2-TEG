package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ColocacionPrimeraRonda extends ColocacionNormal{
    @Override
    public void agregarFichas(String unJugador, Teg teg){
        teg.agregarFichasA(unJugador,5);
    }

    @Override
    public TipoRonda pasarDeRonda(List<String> jugadores, Teg teg){
        return new RondaColocacion(new ColocacionSegundaRonda(),jugadores, teg);

    }
}
