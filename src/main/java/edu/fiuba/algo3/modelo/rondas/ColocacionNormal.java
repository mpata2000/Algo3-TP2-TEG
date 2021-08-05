package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ColocacionNormal{

    public void agregarFichas(String unJugador, Teg teg){
        teg.agregarFichasDisponiblesA(unJugador);
    }


    public TipoRonda pasarDeRonda(List<String> jugadores, Teg teg){
        return  new RondaAtaque(jugadores.get(0));
    }
}
