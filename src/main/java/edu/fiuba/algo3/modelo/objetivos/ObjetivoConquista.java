package edu.fiuba.algo3.modelo.objetivos;


import edu.fiuba.algo3.modelo.Teg;
import java.util.List;
import java.util.Map;

public class ObjetivoConquista extends ObjetivoTeg{
    final Map <String,Integer> paisesPorContinente;
    final List<String> continentesAConquistar;

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

    @Override
    public String textoObjetivo(){
        StringBuilder objetivo = new StringBuilder();

        if(!continentesAConquistar.isEmpty()) {
            objetivo.append(" Continentes a Conquistar:\n");
            for (String continente : continentesAConquistar) {
                objetivo.append("\t> ").append(continente).append("\n");
            }
        }

        if(!paisesPorContinente.isEmpty()) {
            objetivo.append(" Paises por Continentes a Conquistar: \n");

            for (Map.Entry<String, Integer> pair : paisesPorContinente.entrySet()) {
                objetivo.append("\t> ").append(pair.getKey()).append(": ").append(pair.getValue()).append(" paises\n");
            }
        }
        return objetivo.toString();
    }
}
