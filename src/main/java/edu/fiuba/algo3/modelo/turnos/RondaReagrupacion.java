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

    @Override
    public boolean esColocacion(){
        return false;
    }

    @Override
    public boolean esAtaque(){
        return false;
    }

    @Override
    public boolean esReagrupacion(){
        return true;
    }


    @Override
    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRonda();
    }

    @Override
    public void pasarFichas(Teg teg,String paisUno,String paisDos,int cant){
        teg.pasarFichas(paisUno,paisDos,cant);
    }

    @Override
    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRonda();
    }

    @Override
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

    @Override
    public String getJugadorActual() {
        return this.jugadorActual;
    }
}
