package edu.fiuba.algo3.modelo;

import java.util.Dictionary;
import java.util.*;

public class Tablero {
    private Ataque ataque;
    private Dictionary<Pais,String> paises;
    private Pais chile = new Pais("Chile");
    private Pais argentina = new Pais("Alemania");
    Tablero(){
        paises.put(chile,"Chile");
        paises.put(argentina,"Alemania");
    }
/**
    public void atacar(Pais nombrePaisMio , Pais nombrePaisEnemigo) {
        Pais paisMio = this.buscarPais(nombrePaisMio);
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemgio);
        ataque.crearAtaque(paisMio, paisEnemigo);
    }
    public Pais buscarPais(String nombrePais) {

    }



**/
}