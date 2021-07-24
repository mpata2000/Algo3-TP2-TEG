package edu.fiuba.algo3.modelo;

import java.util.List;

public class RondaColocacion implements TipoRonda {

    private final int numDeRonda;

    RondaColocacion(){
        this.numDeRonda = 0;
    }

    RondaColocacion(int numDeRonda){
        this.numDeRonda = numDeRonda;
    }

    @Override
    public TipoRonda cambiarDeRonda() {
        if(this.numDeRonda == 1){
            return new RondaColocacion(2);
        }
        return new RondaAtaque();
    }

    @Override
    public boolean esColocacion(){
        return true;
    }


    @Override
    public boolean esAtaque(){
        return false;
    }

    public void  agregarFichasAColocarA(String unJugador,Teg teg){
        switch(this.numDeRonda){
            case 1:
                teg.agregarFichasA(unJugador,5);
                break;
            case 2:
                teg.agregarFichasA(unJugador,3);
                break;
            default:
                teg.agregarFichasDisponiblesA(unJugador);
        }
    }

    @Override
    public void inicializarRonda(List<String> jugadores, Teg teg){
        for(String colorJugador: jugadores){
            this.agregarFichasAColocarA(colorJugador,teg);
        }
    }
}
