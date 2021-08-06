package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.ataque.Batalla;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoPoseePaisException;

import java.util.*;
import java.util.stream.Collectors;

public class Tablero {
    private final Map<String, Continente> continentes = new HashMap<>();
    private final Map<String, Pais> paises = new HashMap<>();

    public Tablero(List<Continente> continentes, List<Pais> paises){
        for(Continente continente: continentes){
            this.continentes.put(continente.getNombre().toUpperCase(),continente);
        }

        for(Pais pais: paises){
            this.paises.put(pais.getNombre().toUpperCase(),pais);
        }
    }

    public Pais getPais(String nombrePais) {
        return this.paises.get(nombrePais.toUpperCase());
    }

    private Continente getContinente(String nombreContinente) {
        return this.continentes.get(nombreContinente.toUpperCase());
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
        return getContinente(continente).cantidadPaisesDe(jugador);
    }

    public boolean continenteEsDeJugador(String continente, Jugador jugador) {
        return getContinente(continente).esDeJugador(jugador);
    }

    public List<Pais> getPaisesJugador(Jugador jugador) {
        return paises.values().stream().filter(pais -> pais.esDeJugador(jugador)).collect(Collectors.toList());
    }

    public List<String> getPaisesPorContinentes(Jugador jugador) {
        ArrayList<String>paisesPorContinente = new ArrayList<>();

        for(Continente continente: continentes.values()){
            paisesPorContinente.add(continente.paisesDeJugador(jugador));
        }

        return paisesPorContinente;
    }

    public List<Pais> getPaisesEnemigos(Jugador jugador) {
        return paises.values().stream().filter(pais -> !pais.esDeJugador(jugador)).collect(Collectors.toList());
    }
}