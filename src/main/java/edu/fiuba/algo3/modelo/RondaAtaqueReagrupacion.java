package edu.fiuba.algo3.modelo;

public class RondaAtaqueReagrupacion implements TipoRonda {

    @Override
    public RondaColocacion cambiarDeRonda(){
        return new RondaColocacion();
    }
    @Override
    public Boolean esColocacion(){
        return false;
    };
    @Override
    public Boolean esColocacionInicial(){
        return false;
    };
    @Override
    public Boolean esAtaqueReagrupacion(){
        return true;
    };

}
