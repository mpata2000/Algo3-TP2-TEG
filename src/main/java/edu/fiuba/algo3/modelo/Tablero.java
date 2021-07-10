package edu.fiuba.algo3.modelo;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Tablero {
    private Batalla batalla;
    private Map<String, Pais> paises = new HashMap<String, Pais>();

    private Pais chile = new Pais("Chile");
    private Pais argentina = new Pais("Argentina");

    Tablero(){
        paises.put("Chile",chile);
        paises.put("Argentina",argentina);
    }

    public void agregarPais(Pais unPais) {
        paises.put(unPais.getNombre(), unPais);
    }

    public void agregarTropas(int cantidadTropas, Jugador unJugador, String unNombrePais){
        Pais pais = this.buscarPais(unNombrePais);
        pais.agregarTropas(cantidadTropas, unJugador);
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