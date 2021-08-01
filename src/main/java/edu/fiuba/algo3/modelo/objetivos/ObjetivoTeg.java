package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

public abstract class ObjetivoTeg {

    protected String colorDuenio;

    protected boolean objetivoPrincipal(Teg teg){
        return (teg.cantidadDePaisesJugador(this.colorDuenio) > 29);
    }

    protected abstract boolean objetivoJugador(Teg teg);

    public boolean cumplioObjetivo(Teg teg){
        return objetivoPrincipal(teg) || objetivoJugador(teg);
    }

    public void setDuenio(Jugador unJugador){
        this.colorDuenio = unJugador.getColor();
    }

}
