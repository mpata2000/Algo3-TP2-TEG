package edu.fiuba.algo3.modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tablero {
    private final Map<String, Continente> continentes = new HashMap<>();
    private final Map<String, Pais> paises = new HashMap<>();

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

    public void agregarFichas(int cantidadFichas, Jugador unJugador, String unNombrePais){
        this.buscarPais(unNombrePais).agregarFichas(cantidadFichas,unJugador);
    }
    
    public boolean atacar(Jugador unJugador,String nombrePaisAtacante, String nombrePaisEnemigo, int cantidadDadosAtacante) {
        Pais paisAtacante = this.buscarPais(nombrePaisAtacante);
        if(!paisAtacante.esDeJugador(unJugador)){
            return false;
        }
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemigo);
        Batalla batalla = new Batalla(paisAtacante, paisEnemigo);
        return batalla.batallar(cantidadDadosAtacante);
    }

    public Pais buscarPais(String nombrePais) {
        return this.paises.get(nombrePais);
    }

    public Pais getPais(String nombrePais) {
        return this.paises.get(nombrePais);
    }

    public void calcularFichasDe(Jugador unJugador) {
        int contador = 0;
        for(Pais pais: this.paises.values()){
            if(pais.esDeJugador(unJugador)){
                contador++;
            }
        }
        contador = contador/2;
         unJugador.agregarFichas(contador);
    }
}