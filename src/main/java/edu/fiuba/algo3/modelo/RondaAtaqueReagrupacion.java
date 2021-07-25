package edu.fiuba.algo3.modelo;

import java.util.List;

public class RondaAtaqueReagrupacion implements TipoRonda {
    private boolean parteAtaque = true;

    @Override
    public TipoRonda cambiarDeRonda(){
        if(this.parteAtaque){
            this.parteAtaque = false;
            return this;
        }
        return new RondaColocacion();
    }

    @Override
    public boolean esColocacion(){
        return false;
    }

    @Override
    public boolean esAtaque(){
        return this.parteAtaque;
    }

    @Override
    public boolean esReagrupacion(){
        return !this.parteAtaque;
    }

    @Override
    public void inicializarRonda(List<String> jugadores, Teg teg){

    }

}
