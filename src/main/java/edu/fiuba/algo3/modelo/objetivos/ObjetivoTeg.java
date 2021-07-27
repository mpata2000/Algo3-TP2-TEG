package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

public abstract class ObjetivoTeg {

    protected Jugador duenioObjetivo;

    public boolean cumplioObjetivo(Teg teg){
        if(teg.cantidadDePaisesJugador(this.duenioObjetivo) > 29){
            return true;
        }
        return this.objetivoJugador(teg);
    }

    public void setDuenio(Jugador unJugador){
        this.duenioObjetivo = unJugador;
    }


    protected abstract boolean objetivoJugador(Teg teg);

}
