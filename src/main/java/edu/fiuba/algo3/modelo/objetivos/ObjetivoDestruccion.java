package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

import java.util.HashMap;
import java.util.List;

public class ObjetivoDestruccion extends ObjetivoTeg{
    private String colorADestruir;

    public ObjetivoDestruccion(String color){
        this.colorADestruir = color;
    }
    protected boolean objetivoJugador(Teg teg, Jugador jugador){
        return true;
    }

    public String getColor(){
        return this.colorADestruir;
    }

    private Boolean destruyoEnemigo(HashMap<String,Jugador> jugadores){
        if(jugadores.get(this.colorADestruir).devolverCartasPais() == null){
            return true;
        }return false;
    }

}
