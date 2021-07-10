package edu.fiuba.algo3.modelo;

import java.util.Optional;

public class Pais {
    private String nombre;
    private Ejercito ejercito;
    private Jugador jugador;

    public Pais(String nombrePais){
        this.ejercito = new Ejercito();
        this.nombre = nombrePais;
    }
    public  String nombre(){
        return this.nombre;
    }

    public void agregarTropas(int cantidadTropas, Jugador jugador){
        if (this.esDeJugador(jugador)) {
            this.jugador = jugador;
            this.ejercito.agregarTropas(cantidadTropas);
        }
    }

    public Jugador getJugador(){
        return this.jugador;
    }

   public boolean esDeJugador(Jugador jugador) {
       return jugador.esElMismoJugador(this.jugador);
   }

    public Ejercito getEjercito(){
        return this.ejercito;
    }

    public Boolean esDelJugador(Ejercito ejercito){
        return this.ejercito.esDelJugador(ejercito);
    }
}
