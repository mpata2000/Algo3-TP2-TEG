package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablero {
    private Map<String, Continente> continentes = new HashMap<>();
    private Map<String, Pais> paises = new HashMap<>();

    public Tablero(List<Continente> continentes, List<Pais> paises){
        for(Continente continente: continentes){
            this.continentes.put(continente.getNombre(),continente);
        }

        for(Pais pais: paises){
            this.paises.put(pais.getNombre(),pais);
        }
    }

    public Map<String, Continente> getContinentes() {
        return continentes;
    }

    public Map<String, Pais> getPaises() {
        return paises;
    }

    public void agregarPais(Pais unPais) {
        paises.put(unPais.getNombre(), unPais);
    }

    public void agregarFichas(int cantidadTropas, Jugador unJugador, String unNombrePais){
        Pais pais = this.buscarPais(unNombrePais);
        pais.agregarFichas(cantidadTropas,unJugador);

    }
    public boolean atacar(String nombrePaisMio , String nombrePaisEnemigo, int cantidadDeDadosAtacante) {
        Pais paisMio = this.buscarPais(nombrePaisMio);
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemigo);
        Batalla batalla = new Batalla(paisMio, paisEnemigo);
        return batalla.batallar(cantidadDeDadosAtacante);
    }

    public Pais buscarPais(String unNombrePais) {
        return this.paises.get(unNombrePais);
    }

    public boolean esDelJugador(String nombrePais,Jugador jugador){
        return (this.buscarPais(nombrePais).esDeJugador(jugador));
    }

    public Pais getPais(String nombrePais) {
        return this.paises.get(nombrePais);
    }
}