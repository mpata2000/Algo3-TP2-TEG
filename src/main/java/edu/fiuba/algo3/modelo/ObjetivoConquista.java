package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class ObjetivoConquista extends ObjetivoTeg{
    HashMap <String,Integer> paisesPorContinente = new HashMap<>();

    protected boolean objetivoJugador(Teg teg, Jugador jugador){
        for(Map.Entry<String,Integer> entry: paisesPorContinente.entrySet()){
            if(teg.cantidadDePaisesJugadorEnContinente(entry.getKey(),jugador) < entry.getValue()){
                return false;
            }
        }
        return true;
    }
}
