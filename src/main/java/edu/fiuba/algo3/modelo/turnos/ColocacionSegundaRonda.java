package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

public class ColocacionSegundaRonda extends ColocacionNormal{
    @Override
    public void colocarFichas(String unJugador, Teg teg){
        teg.agregarFichasA(unJugador,3);
    }
}
