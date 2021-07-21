package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Turnos {

    //Todo: refactor completo de turnos

    private int turno = 0;
    private ArrayList<Jugador> rondaJugadores = new ArrayList<>();
    private String tipoDeRonda = "Colocacion";

    Turnos(Map<String, Jugador> jugadores) {
        jugadores.forEach((color, jugador) -> rondaJugadores.add(jugador));
    }

    public boolean esTurnoDe(Pais unPais){
        return (unPais.esDeJugador(this.rondaJugadores.get(this.turno)));
    }

    public void avanzarRonda(){
        if (this.rondaJugadores.get((this.turno)+1) != null) this.turno += 1;
        else cambiarRonda();
    }

    public void cambiarRonda(){
        this.turno = 0;
        if (this.tipoDeRonda == "Colocacion") this.tipoDeRonda = "Ataque";
        if (this.tipoDeRonda == "Ataque") this.tipoDeRonda = "Colocacion";
    }

}

