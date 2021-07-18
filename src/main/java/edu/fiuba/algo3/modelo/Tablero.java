package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private Map<String, Pais> paises = new HashMap<>();

    Tablero(){
    }

    public void agregarPais(Pais unPais) {
        paises.put(unPais.getNombre(), unPais);
    }

    public void agregarFichas(int cantidadTropas, Jugador unJugador, String unNombrePais){
        Pais pais = this.buscarPais(unNombrePais);
        pais.agregarFichas(cantidadTropas, unJugador);
    }
    public boolean atacar(String nombrePaisMio , String nombrePaisEnemigo,int cantidad, Turnos turnos) {
        Pais paisMio = this.buscarPais(nombrePaisMio);
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemigo);
        if(turnos.esTurnoDe(paisMio)) {
            Batalla batalla = new Batalla(paisMio, paisEnemigo);
            return batalla.batallar(cantidad);
        }
        return false;
    }

    public Pais buscarPais(String unNombrePais) {
        return this.paises.get(unNombrePais);
    }

    public boolean esDelJugador(String nombrePais,Jugador jugador){
        return (this.buscarPais(nombrePais).esDeJugador(jugador));
    }

}