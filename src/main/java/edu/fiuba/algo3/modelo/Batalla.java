package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Batalla {

    private Pais paisAtacante;
    private Pais paisDefensor;

    Batalla(Pais paisAtacante, Pais paisDefensor){
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
    }

    public boolean batallar(ArrayList<Integer> dadosAtacante, ArrayList<Integer> dadosDefensor){
        int fichasPerdidasAtacante = 0;
        int fichasPerdidasDefensor = 0;

        for (int i = 0; i < dadosAtacante.size() | i<dadosDefensor.size(); i++){
            if (dadosAtacante.get(i) > dadosDefensor.get(i)) {
                fichasPerdidasDefensor++;
            }
            else{
                fichasPerdidasAtacante++;
            }
        }
        this.paisAtacante.perderFichas(fichasPerdidasAtacante);
        return (this.paisDefensor.perderFichas(fichasPerdidasDefensor, this.paisAtacante));
    }
}
