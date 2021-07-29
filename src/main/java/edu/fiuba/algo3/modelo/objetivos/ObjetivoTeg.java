package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

public abstract class ObjetivoTeg {

    protected String colorDuenio;

    public boolean cumplioObjetivo(Teg teg){
        if(teg.cantidadDePaisesJugador(this.colorDuenio) > 29){
            return true;
        }
        return this.objetivoJugador(teg);
    }

    public void setDuenio(Jugador unJugador){
        this.colorDuenio = unJugador.getColor();
    }


    protected abstract boolean objetivoJugador(Teg teg);

}
