package edu.fiuba.algo3.modelo.turnos;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;
import java.util.ListIterator;

public class RondaColocacion implements TipoRonda {

    private final TipoColocacion tipoColocacion;
    private ListIterator<String> iteradorJugadores;
    private String jugadorActual;
    private boolean turnoInicilizado = false;

    RondaColocacion(List<String> jugadores){
        this.tipoColocacion = new ColocacionPrimeraRonda();
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    RondaColocacion(TipoColocacion colocacion,List<String> jugadores){
        this.tipoColocacion = colocacion;
        this.iteradorJugadores = jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
    }

    @Override
    public boolean esColocacion(){
        return true;
    }


    @Override
    public boolean esAtaque(){
        return false;
    }

    @Override
    public boolean esReagrupacion(){
        return false;
    }

    private void inicializarTurno(Teg teg){
        if(!this.turnoInicilizado) {
            this.tipoColocacion.colocarFichas(jugadorActual, teg);
            this.turnoInicilizado = true;
        }
    }

    @Override
    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRonda();
    }

    @Override
    public void pasarFichas(Teg teg,String paisUno,String paisdos,int cant){
        throw new NoSePuedeHacerEstaAccionEnEstaRonda();
    }

    @Override
    public void colocarEjercitos(Teg teg,String nombrePais, int cantidad){
        this.inicializarTurno(teg);
        teg.colocarFichas(jugadorActual,nombrePais,cantidad);
    }

    @Override
    public TipoRonda finEtapa(List<String> jugadores){
        if(this.iteradorJugadores.hasNext()){
            this.jugadorActual = this.iteradorJugadores.next();
            this.turnoInicilizado = false;
            return this;
        }

        return this.tipoColocacion.pasarDeRonda(jugadores);
    }

    @Override
    public String getJugadorActual() {
        return this.jugadorActual;
    }
}
