package edu.fiuba.algo3.modelo.objetivos;


import edu.fiuba.algo3.modelo.Teg;

import java.util.List;
import java.util.Map;

public class ObjetivoConquista extends ObjetivoTeg{
    Map <String,Integer> paisesPorContinente;
    List<String> continentesAConquistar;

    public ObjetivoConquista(List<String> continentes,Map<String,Integer> paisesPorContinente){
        this.continentesAConquistar = continentes;
        this.paisesPorContinente = paisesPorContinente;
    }

    private boolean tieneTodosLosPaises(Teg teg){
        for(Map.Entry<String,Integer> entry: paisesPorContinente.entrySet()){
            if(teg.cantidadDePaisesJugadorEnContinente(entry.getKey(),this.colorDuenio) < entry.getValue()){
                return false;
            }
        }
        return true;
    }

    private boolean tieneTodosLosContienetes(Teg teg){
        for(String continente: continentesAConquistar){
            if(!teg.continenteEsDeJugador(continente,this.colorDuenio)){
                return false;
            }
        }
        return true;
    }

    protected boolean objetivoJugador(Teg teg){
        return this.tieneTodosLosContienetes(teg) && this.tieneTodosLosPaises(teg);
    }
}
