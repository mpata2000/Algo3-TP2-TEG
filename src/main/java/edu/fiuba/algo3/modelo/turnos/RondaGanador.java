package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class RondaGanador implements TipoRonda {
    private final String ganador;

    public RondaGanador(String ganador) {
        this.ganador = ganador;
    }


    public void atacar(Teg teg, String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public void pasarFichas(Teg teg,String paisUno,String paisDos,int cant){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        return this;
    }

    public String getJugadorActual() {
        return this.ganador;
    }
}
