package edu.fiuba.algo3.modelo.rondas;

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
    public String getNombre() {
        return "Ronda de Reagrupacion";
    }

    public boolean atacarACon(Teg teg, String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public void pasarFichas(Teg teg, String paisOrigen, String paisDestino, int cant){
        teg.pasarFichas(jugadorActual, paisOrigen, paisDestino,cant);
    }

    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public boolean darCartaPais(Teg teg){
        return  teg.darCartaPaisA(jugadorActual);
    }

    public boolean hacerCanje(Teg teg){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        if(iteradorJugadores.hasNext()){
            jugadorActual = iteradorJugadores.next();
            return new RondaAtaque(jugadorActual);
        }
        if(teg.hayGanador()){
            return new RondaGanador(teg.getGanador());
        }
        teg.sacarConquistaDePaisAJugadores();
        return new RondaColocacion(new ColocacionNormal(),jugadores, teg);
    }


    public String getJugadorActual() {
        return jugadorActual;
    }
}
