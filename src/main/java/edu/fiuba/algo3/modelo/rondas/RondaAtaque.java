package edu.fiuba.algo3.modelo.rondas;

import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class RondaAtaque implements TipoRonda {
    private final String jugadorActual;
    private boolean conquistoPais = false;
    private String paisAtcante;
    private String paisConquistado;


    public RondaAtaque(String jugadorActual){
        this.jugadorActual = jugadorActual;
    }



    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        if(teg.atacar(jugadorActual,paisAtacante, paisDefensor, cantidad)){
            this.conquistoPais = true;
            this.paisAtcante = paisAtacante;
            this.paisConquistado = paisDefensor;
        }
    }

    public void pasarFichas(Teg teg, String paisOrigen, String paisDestino, int cant){
        if(this.conquistoPais && paisOrigen.equalsIgnoreCase(this.paisAtcante) && paisDestino.equalsIgnoreCase(this.paisConquistado)) {
            teg.pasarFichas(jugadorActual, paisOrigen, paisDestino, cant);
        }else{
            throw new PasajeDeFichasNoValidoEnAtaqueException();
        }
    }


    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRondaException();
    }


    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        return new RondaReagrupacion(this.jugadorActual,jugadores);
    }


    public String getJugadorActual() {
        return this.jugadorActual;
    }
}