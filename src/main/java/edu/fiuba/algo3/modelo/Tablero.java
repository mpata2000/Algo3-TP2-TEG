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

    public void agregarFichas(int cantidadTropas, Jugador unJugador, String unNombrePais){
        this.buscarPais(unNombrePais).agregarFichas(cantidadTropas,unJugador);
    }
    
    public boolean atacar(Jugador jugador,String nombrePaisMio , String nombrePaisEnemigo, int cantidadDeDadosAtacante) {
        Pais paisAtacante = this.buscarPais(nombrePaisMio);
        if(!paisAtacante.esDeJugador(jugador)){
            return false;
        }
        Pais paisEnemigo = this.buscarPais(nombrePaisEnemigo);
        Batalla batalla = new Batalla(paisAtacante, paisEnemigo);
        return batalla.batallar(cantidadDeDadosAtacante);
    }

    public Pais buscarPais(String unNombrePais) {
        return this.paises.get(unNombrePais);
    }

    public Pais getPais(String nombrePais) {
        return this.paises.get(nombrePais);
    }

    public void calcularFichasDe(Jugador jugador) {
        int contador = 0;
        for(Pais pais: this.paises.values()){
            if(pais.esDeJugador(jugador)){
                contador++;
            }
        }
        contador = contador/2;
         jugador.agregarFichas(contador);
    }
}