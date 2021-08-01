package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.ataque.Batalla;
import edu.fiuba.algo3.modelo.Jugador;

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

    public void agregarFichas(String nombrePais, Jugador unJugador, int cantidadFichas){
        this.getPais(nombrePais).agregarFichas(cantidadFichas,unJugador);
    }

    public boolean atacar(Jugador unJugador,String nombrePaisAtacante, String nombrePaisEnemigo, int cantidadDadosAtacante) {
        Pais paisAtacante = this.getPais(nombrePaisAtacante);
        if(!paisAtacante.esDeJugador(unJugador)){
            throw new JugadorNoPoseePaisException();
        }
        Pais paisEnemigo = this.getPais(nombrePaisEnemigo);
        Batalla batalla = new Batalla(paisAtacante, paisEnemigo);
        return batalla.batallar(cantidadDadosAtacante);
    }

    /*
    * Devuelve la cantidad de paises poseidos por el jugador
     */
    public int cantidadDePaisesJugador(Jugador unJugador){
        int contador = 0;
        for(Pais pais: this.paises.values()){
            if(pais.esDeJugador(unJugador)){
                contador++;
            }
        }
        return contador;
    }

    /*
    * Le agrega una cantidad de fichas a el jugador recivido
    * La cantidad de fichas va a ser la mitad de paises que posee el jugador
    * o como minimo tres fichas.
    * Tambien se le agregan fichas extras si posee un contiente entero
     */
    public void agregarFichasA(Jugador unJugador) {

        unJugador.agregarFichas(Math.max((this.cantidadDePaisesJugador(unJugador)/2),3));

        for(Continente continente: this.continentes.values()){
            continente.agregarFichasExtraA(unJugador);
        }
    }

    public void pasarFichas(Jugador unJugador,String paisOrigen, String paisDestino,int cantidadFichas) {
        Pais pais = this.getPais(paisOrigen);
        if(!pais.esDeJugador(unJugador)) {
            throw new JugadorNoPoseePaisException();
        }
        pais.pasarFichasA(this.getPais(paisDestino),cantidadFichas);
    }

    public int cantidadDePaisesJugadorEnContinente(String continente, Jugador jugador) {
        return this.continentes.get(continente).cantidadPaisesDe(jugador);
    }

    public boolean continenteEsDeJugador(String continente, Jugador jugador) {
        return this.continentes.get(continente).esDeJugador(jugador);
    }
}