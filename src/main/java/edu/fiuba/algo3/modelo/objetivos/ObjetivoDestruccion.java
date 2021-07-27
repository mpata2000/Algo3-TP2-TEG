package edu.fiuba.algo3.modelo.objetivos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public class ObjetivoDestruccion extends ObjetivoTeg{
    private final List<Jugador> jugadores;
    private Jugador jugadorADestruir;

    public ObjetivoDestruccion(String color,List<Jugador> jugadoresEnOrden){
        this.jugadorADestruir = jugadoresEnOrden.stream()
                .filter(j -> j.getColor().equalsIgnoreCase(color))
                .findFirst()
                .orElse(null);
        this.jugadores = jugadoresEnOrden;
    }

    @Override
    public void setDuenio(Jugador unJugador){
        this.duenioObjetivo = unJugador;
        if(unJugador.esElMismoJugador(jugadorADestruir) || (jugadorADestruir == null)){
            jugadorADestruir = jugadores.get(jugadores.indexOf(unJugador)+1%jugadores.size());
        }
    }

    protected boolean objetivoJugador(Teg teg){
        return (teg.cantidadDePaisesJugador(jugadorADestruir) < 1);
    }

}
