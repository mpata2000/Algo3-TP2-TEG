package edu.fiuba.algo3.modelo;

import java.util.List;

public class RondaReagrupacion implements TipoRonda{
    @Override
    public RondaColocacion cambiarDeRonda(){
        return new RondaColocacion();
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
    public void inicializarRonda(List<String> jugadores, Teg teg){

    }

}
