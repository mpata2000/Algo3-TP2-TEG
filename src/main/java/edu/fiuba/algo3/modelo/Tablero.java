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

    public Pais getPais(String nombrePais) {
        return this.paises.get(nombrePais);
    }

    public Map<String, Continente> getContinentes() {
        return continentes;
    }

    public Map<String, Pais> getPaises() {
        return paises;
    }

    public void agregarFichas(int cantidadFichas, Jugador unJugador, String unNombrePais){
        this.getPais(unNombrePais).agregarFichas(cantidadFichas,unJugador);
    }

    public boolean atacar(Jugador unJugador,String nombrePaisAtacante, String nombrePaisEnemigo, int cantidadDadosAtacante) {
        Pais paisAtacante = this.getPais(nombrePaisAtacante);
        if(!paisAtacante.esDeJugador(unJugador)){
            return false;
        }
        Pais paisEnemigo = this.getPais(nombrePaisEnemigo);
        Batalla batalla = new Batalla(paisAtacante, paisEnemigo);
        return batalla.batallar(cantidadDadosAtacante);
    }

    public void agregarFichasA(Jugador unJugador) {
        int contador = 0;
        for(Pais pais: this.paises.values()){
            if(pais.esDeJugador(unJugador)){
                contador++;
            }
        }

        contador = Math.max(contador/2,3); // como minimo tres fichas
        unJugador.agregarFichas(contador);

        for(Continente continente: this.continentes.values()){
            continente.agregarFichasExtraA(unJugador);
        }
    }

    public void pasarFichas(String paisUno, String paisDos,int cantidadFichas) {
        this.getPais(paisUno).pasarFichasA(this.getPais(paisDos),cantidadFichas);
    }
}