package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;
import java.util.ListIterator;

public class RondaReagrupacion implements TipoRonda{

    private ListIterator<String> iteradorJugadores;
    private String jugadorActual;

    public RondaReagrupacion(String jugadorActual,List<String> jugadores) {
        this.jugadorActual = jugadorActual;
        this.iteradorJugadores = jugadores.listIterator(jugadores.indexOf(jugadorActual));
        this.jugadorActual = this.iteradorJugadores.next();
    }

    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public void pasarFichas(Teg teg,String paisUno,String paisDos,int cant){
        teg.pasarFichas(jugadorActual,paisUno,paisDos,cant);
    }

    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        if(this.iteradorJugadores.hasNext()){
            this.jugadorActual = this.iteradorJugadores.next();
            return new RondaAtaque(jugadorActual);
        }
        if(teg.hayGanador()){
            return new RondaGanador(teg.getGanador());
        }

        return new RondaColocacion(jugadores);
    }

    public String getJugadorActual() {
        return this.jugadorActual;
    }
}
