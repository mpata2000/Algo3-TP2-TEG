package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;
import java.util.ListIterator;

public class RondaColocacion implements TipoRonda {

    private final TipoColocacion tipoColocacion;
    private ListIterator<String> iteradorJugadores;
    private String jugadorActual;
    private boolean turnoInicilizado = false;

    public RondaColocacion(List<String> jugadores){
        this.tipoColocacion = new ColocacionPrimeraRonda();
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    public RondaColocacion(TipoColocacion colocacion,List<String> jugadores){
        this.tipoColocacion = colocacion;
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    private void inicializarTurno(Teg teg){
        if(!this.turnoInicilizado) {
            this.tipoColocacion.colocarFichas(jugadorActual, teg);
            this.turnoInicilizado = true;
        }
    }


    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public void pasarFichas(Teg teg,String paisUno,String paisdos,int cant){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        this.inicializarTurno(teg);
        teg.colocarFichas(jugadorActual,nombrePais,cantidad);
    }


    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        if(teg.jugadorTieneFichas(this.jugadorActual)){
            throw new JugadorSigueTeniendoFichasException();
        }
        if(this.iteradorJugadores.hasNext()){
            this.jugadorActual = this.iteradorJugadores.next();
            this.turnoInicilizado = false;
            return this;
        }

        return this.tipoColocacion.pasarDeRonda(jugadores);
    }


    public String getJugadorActual() {
        return this.jugadorActual;
    }
}
