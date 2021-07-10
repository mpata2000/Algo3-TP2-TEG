package edu.fiuba.algo3.modelo;

import java.util.Optional;

public class Pais {
    private String nombre;
    private Ejercito ejercito;
    private Jugador jugador;

    public Pais(String nombrePais){
        this.ejercito = new Ejercito();
        this.nombre = nombrePais;
        this.jugador = null;
    }

    public String getNombre() {return this.nombre;}
    public Jugador getJugador(){
        return this.jugador;
    }

    public void agregarTropas(int cantidadTropas, Jugador unJugador){
        if(this.jugador == null) { this.jugador = unJugador; }

        if (this.esDeJugador(unJugador)) {
            this.ejercito.agregarTropas(cantidadTropas);
        } else {
            throw new JugadorNoPoseePaisException();
        }
    }

   public boolean esDeJugador(Jugador jugador) {
       return jugador.esElMismoJugador(this.jugador);
   }
}
