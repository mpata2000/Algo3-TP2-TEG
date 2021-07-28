package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.lector.LectorDeJson;
import edu.fiuba.algo3.modelo.cartas.CartasPaisTeg;
import edu.fiuba.algo3.modelo.cartas.ColeccionDeCartasPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.*;


public class Teg {
    private final Tablero tablero;
    private Map<String, Jugador> jugadores = new HashMap<>();
    private final CartasPaisTeg cartas;
    private List<ObjetivoTeg> objetivos = new ArrayList<>();

    public Teg(){
        LectorDeJson lector = new LectorDeJson();
        this.tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        this.cartas = new ColeccionDeCartasPais(lector.lectorCartasPais("resources/Teg-Cartas.json"));
        this.objetivos.addAll(lector.lectorObjetivosConquista("resources/Teg-Objetivos.json"));
    }

    public Teg(Tablero tablero,Map <String,Jugador> jugadores){
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.cartas = new ColeccionDeCartasPais();
    }

    public void comenzarJuego(List<String> colores) {
        for (String color : colores) {
            this.jugadores.put(color, new Jugador(color));
        }
        this.cartas.asignarPaises(new ArrayList<>(this.jugadores.values()));
        this.objetivos.addAll(LectorDeJson.creadorDeObjetivososDestruccion(new ArrayList<>(this.jugadores.values())));
        this.asignarObjetivos();
    }

    private void asignarObjetivos() {

        Collections.shuffle(this.objetivos);
        int i = 0;
        for(Jugador jugador: jugadores.values()){
            jugador.darObjetivo(this.objetivos.get(i));
            i++;
        }
    }

    public void colocarFichas(String colorJugador, String nombrePais, int cant){
        this.tablero.agregarFichas(cant,this.jugadores.get(colorJugador),nombrePais);
    }

    public boolean atacar(String colorJugador,String paisAtacante, String paisDefensor, int cantidad){
        Jugador jugador = this.jugadores.get(colorJugador);
        if(tablero.atacar(jugador,paisAtacante, paisDefensor, cantidad)){
            jugador.conquistoPais();
            //Todo: Preguntar dar carta automaticamente
            return true;
        }
        return false;
    }

    public void pasarFichas(String colorJugador,String paisUno, String paisDos, int cant){
        this.tablero.pasarFichas(jugadores.get(colorJugador),paisUno, paisDos, cant);
    }

    public void agregarFichasDisponiblesA(String colorJugador) {
        Jugador jugador = this.jugadores.get(colorJugador);
        jugador.activarCartas();
        this.tablero.agregarFichasA(jugador);
        //Todo: Preguntar si hacer canje automatico
    }

    public void agregarFichasA(String colorJugador, int cantidadFichas) {
        this.jugadores.get(colorJugador).agregarFichas(cantidadFichas);
    }

    public void hacerCanjeJugador(String colorJugador){
         this.jugadores.get(colorJugador).hacerCanje(this.cartas);
    }

    public void darCarta(String colorJugador) {
        this.cartas.darCartaA(this.jugadores.get(colorJugador));
    }

    public int cantidadDePaisesJugador(Jugador jugador) {
        return this.tablero.cantidadDePaisesJugador(jugador);
    }

    public int cantidadDePaisesJugadorEnContinente(String continente, Jugador jugador) {
        return this.tablero.cantidadDePaisesJugadorEnContinente(continente,jugador);
    }

    public boolean continenteEsDeJugador(String continente,Jugador jugador) {
        return this.tablero.continenteEsDeJugador(continente,jugador);
    }

    public boolean hayGanador(){
        return this.jugadores.values().stream().anyMatch(j -> j.gano(this));
    }

    public boolean jugadorTieneFichas(String colorJugador) {
        return this.jugadores.get(colorJugador).tieneFichas();
    }

    public String getGanador() {
        return this.jugadores.entrySet().stream()
                .filter(e -> e.getValue().gano(this))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }
}
