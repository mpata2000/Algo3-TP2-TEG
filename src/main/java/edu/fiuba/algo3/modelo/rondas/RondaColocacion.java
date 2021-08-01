package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;
import java.util.ListIterator;

public class RondaColocacion implements TipoRonda {

    private final ColocacionNormal tipoColocacion;
    private ListIterator<String> iteradorJugadores;
    private String jugadorActual;
    private boolean turnoInicilizado = false;

    public RondaColocacion(List<String> jugadores){
        this.tipoColocacion = new ColocacionPrimeraRonda();
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    public RondaColocacion(ColocacionNormal colocacion,List<String> jugadores){
        this.tipoColocacion = colocacion;
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    private void inicializarTurno(Teg teg){
        if(!turnoInicilizado) {
            tipoColocacion.agregarFichas(jugadorActual, teg);
            turnoInicilizado = true;
        }
    }


    public void atacarACon(Teg teg, String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public void pasarFichas(Teg teg, String paisOrigen, String paisDestino, int cant){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        this.inicializarTurno(teg);
        teg.colocarFichas(jugadorActual,nombrePais,cantidad);
    }


    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        if(teg.jugadorTieneFichas(jugadorActual)){
            throw new JugadorSigueTeniendoFichasException();
        }
        if(this.iteradorJugadores.hasNext()){
            jugadorActual = iteradorJugadores.next();
            turnoInicilizado = false;
            return this;
        }

        return tipoColocacion.pasarDeRonda(jugadores);
    }

    public boolean darCartaPais(Teg teg){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public boolean hacerCanje(Teg teg){
        return  teg.hacerCanjeJugador(jugadorActual);
    }

    public String getJugadorActual() {
        return jugadorActual;
    }
}
