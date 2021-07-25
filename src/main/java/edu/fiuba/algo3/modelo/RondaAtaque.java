package edu.fiuba.algo3.modelo;

import java.util.List;

public class RondaAtaque implements TipoRonda {

    @Override
    public TipoRonda cambiarDeRonda(){
        return new RondaReagrupacion();
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
    public void inicializarRonda(List<String> jugadores, Teg teg){

    }

}
