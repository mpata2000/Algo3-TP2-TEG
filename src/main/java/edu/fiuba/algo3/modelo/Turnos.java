package edu.fiuba.algo3.modelo;

public class Turnos {
    private int turno = 0;
    private Jugador[] ronda = null;
    private String tipoDeRonda = "Colocacion";

    public boolean esTurnoDe(Pais unPais){
        return (unPais.esDeJugador(this.ronda[this.turno]));
    }

    public void avanzarRonda(){
        if (this.ronda[(this.turno)+1] != null) this.turno += 1;
        else cambiarRonda();
    }


    public void cambiarRonda(){
        this.turno = 0;
        if (this.tipoDeRonda == "Colocacion") this.tipoDeRonda = "Ataque";
        if (this.tipoDeRonda == "Ataque") this.tipoDeRonda = "Colocacion";
    }
}

