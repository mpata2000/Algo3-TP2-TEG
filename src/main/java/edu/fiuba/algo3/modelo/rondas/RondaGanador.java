package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;
import edu.fiuba.algo3.modelo.excepciones.NoSePuedeHacerEstaAccionEnEstaRondaException;

import java.util.List;

public class RondaGanador implements TipoRonda {
    private final String ganador;

    public RondaGanador(String ganador) {
        this.ganador = ganador;
    }


    public void atacarACon(Teg teg, String paisAtacante, String paisDefensor, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    @Override
    public String getNombre() {
        return "Termino el juego";
    }

    public void pasarFichas(Teg teg, String paisOrigen, String paisDestino, int cant){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public boolean darCartaPais(Teg teg){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }

    public boolean hacerCanje(Teg teg){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public String getJugadorActual() {
        return this.ganador;
    }

}
