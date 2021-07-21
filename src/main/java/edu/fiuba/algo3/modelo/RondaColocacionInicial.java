package edu.fiuba.algo3.modelo;

public class RondaColocacionInicial implements TipoRonda {

    @Override
    public RondaAtaqueReagrupacion cambiarDeRonda(){
        return new RondaAtaqueReagrupacion();

    }
    @Override
    public Boolean esColocacion(){
        return false;
    };
    @Override
    public Boolean esColocacionInicial(){
        return true;
    };
    @Override
    public Boolean esAtaqueReagrupacion(){
        return false;
    };
}
