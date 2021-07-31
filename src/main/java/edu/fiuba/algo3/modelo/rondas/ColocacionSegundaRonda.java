package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;

public class ColocacionSegundaRonda extends ColocacionNormal{
    @Override
    public void agregarFichas(String unJugador, Teg teg){
        teg.agregarFichasA(unJugador,3);
    }
}
