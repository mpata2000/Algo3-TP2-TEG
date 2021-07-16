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

    /*
    public void atacar(Pais nombrePaisMio , Pais nombrePaisEnemigo) {
        Pais paisMio = this.buscarPais(nombrePaisMio);
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemgio);
        ataque.crearAtaque(paisMio, paisEnemigo);
    }*/

    public Pais buscarPais(String unNombrePais) {
        return this.paises.get(unNombrePais);
    }

}