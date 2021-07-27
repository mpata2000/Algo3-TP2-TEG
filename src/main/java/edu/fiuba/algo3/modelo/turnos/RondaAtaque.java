package edu.fiuba.algo3.modelo.turnos;

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


    @Override
    public boolean esColocacion(){
        return false;
    }

    @Override
    public boolean esAtaque(){
        return true;
    }

    @Override
    public boolean esReagrupacion(){
        return false;
    }


    @Override
    public void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad){
        if(teg.atacar(jugadorActual,paisAtacante, paisDefensor, cantidad)){
            this.conquistoPais = true;
            this.paisAtcante = paisAtacante;
            this.paisConquistado = paisDefensor;
        }
    }

    @Override
    public void pasarFichas(Teg teg,String paisUno,String paisDos,int cant){
        if(this.conquistoPais && paisUno.equalsIgnoreCase(this.paisAtcante) && paisDos.equalsIgnoreCase(this.paisConquistado)) {
            teg.pasarFichas(paisUno, paisDos, cant);
        }else{
            throw new PasajeDeFichasNoValidoEnAtaque();
        }
    }

    @Override
    public void colocarFichas(Teg teg, String nombrePais, int cantidad){
        throw new NoSePuedeHacerEstaAccionEnEstaRonda();
    }

    @Override
    public TipoRonda finEtapa(List<String> jugadores,Teg teg){
        return new RondaReagrupacion(this.jugadorActual,jugadores);
    }

    @Override
    public String getJugadorActual() {
        return this.jugadorActual;
    }
}
