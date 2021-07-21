package edu.fiuba.algo3.modelo;

public class RondaColocacion implements TipoRonda {

    @Override
    public RondaAtaqueReagrupacion cambiarDeRonda() {
        return new RondaAtaqueReagrupacion();
    }
    @Override
    public Boolean esColocacion(){
        return true;
    };
    @Override
    public Boolean esColocacionInicial(){
        return false;
    };
    @Override
    public Boolean esAtaqueReagrupacion(){
        return false;
    };
}
