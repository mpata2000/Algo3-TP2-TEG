package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjetivoConquista extends ObjetivoTeg{
    HashMap <String,Integer> paisesPorContinente = new HashMap<>();
    List<String> continentesAConquistar;

    private boolean tieneTodosLosPaises(Teg teg, Jugador jugador){
        for(Map.Entry<String,Integer> entry: paisesPorContinente.entrySet()){
            if(teg.cantidadDePaisesJugadorEnContinente(entry.getKey(),jugador) < entry.getValue()){
                return false;
            }
        }
        return true;
    }

    private boolean tieneTodosLosContienetes(Teg teg, Jugador jugador){
        for(String continente: continentesAConquistar){
            if(!teg.continenteEsDeJugador(continente,jugador)){
                return false;
            }
        }
        return true;
    }

    protected boolean objetivoJugador(Teg teg, Jugador jugador){
        return this.tieneTodosLosContienetes(teg, jugador) && this.tieneTodosLosPaises(teg, jugador);
    }
}
