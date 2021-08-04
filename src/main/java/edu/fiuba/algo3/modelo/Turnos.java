package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.rondas.LimiteDeJugadoresException;
import edu.fiuba.algo3.modelo.rondas.NoHaySuficientesJugadoresException;
import edu.fiuba.algo3.modelo.rondas.RondaColocacion;
import edu.fiuba.algo3.modelo.rondas.TipoRonda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Turnos {
    private static Turnos turnos;
    private List<String> jugadores = new ArrayList<>();
    private TipoRonda tipoDeRonda;
    private final Teg teg;

    public static Turnos getInstance() {
        if(turnos == null){
            turnos = new Turnos();
        }
        return turnos;
    }

    public Turnos() {
        this.teg = new Teg();
    }

    public Turnos(Teg teg, List<String> jugadores){
        this.tipoDeRonda = new RondaColocacion(jugadores, teg) ;
        this.teg = teg;
        this.jugadores = jugadores;
    }

    public Teg getTeg() {
        return this.teg;
    }

    public String getJugadorActual() {
        return this.tipoDeRonda.getJugadorActual();
    }

    /*
     * Agrega los jugadores a una lista de strings
     */
    public void agregarJugadores(List<String> listaJugadores){
        if(listaJugadores.size() > 6){
            throw new LimiteDeJugadoresException();
        }
        this.jugadores.addAll(listaJugadores);
    }

    /*
     * Comienza el juego, elijiendo aleatoriamente el orden inicial
     * de los jugadores y creando una primera ronda de colocacion
     */
    public void comenzarJuego(){
        if(this.jugadores.size() < 2){
            throw new NoHaySuficientesJugadoresException();
        }
        Collections.shuffle(jugadores); //Mezcla los jugadores como si tiraron Dados
        this.teg.comenzarJuego(jugadores);
        this.tipoDeRonda = new RondaColocacion(jugadores, teg) ;
    }

    /*
     * Ataque de un pais a otro con una cierta cantidad de fichas
     */
    public void atacarACon(String paisAtacante, String paisDefensor, int cantidad){
        this.tipoDeRonda.atacarACon(this.teg,paisAtacante, paisDefensor, cantidad);
    }

    /*
     * Pasaje de una cierta cantidad de fichas de un pais a otro
     */
    public void pasarFichas(String paisUno,String paisdos,int cant){
        this.tipoDeRonda.pasarFichas(this.teg,paisUno,paisdos,cant);
    }

    /*
     * Colocacion de una cierta cantidad de fichas en un pais
     */
    public void colocarFichas(String nombrePais, int cantidad){
        this.tipoDeRonda.colocarFichas(this.teg,nombrePais,cantidad);
    }

    /*
     * Distribuye una carta pais a un jugador
     */
    public boolean darCartaPais(){
        return this.tipoDeRonda.darCartaPais(this.teg);
    }

    /*
     * Realiza el canje de una trio cartaPais por una cierta cantidad de fichas
     */
    public boolean realizarCanje(){
        return this.tipoDeRonda.hacerCanje(this.teg);
    }

    /*
     * Devuelve el tipo de ronda que se esta jugando en el momento
     */
    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }

    /*
     * Finaliza el tipo de ronda actual
     */
    public void finEtapa(){
        this.tipoDeRonda = this.tipoDeRonda.finEtapa(jugadores,this.teg);
    }

    public String textoDeObjetivo(){
        return ((this.teg.getJugador(this.getJugadorActual())).devolverObjetivo().textoObjetivo());
    }

    public int getFichas(){return this.teg.getFichas(tipoDeRonda.getJugadorActual());}

    public String paisesjugador() {
        return this.teg.paisesJugador(tipoDeRonda.getJugadorActual());
    }
    public String getTodosLosPaises() {
        return this.teg.getTodosLosPaises();
    }

    public String getPaisesPorContinente() {
        return this.teg.getPaisesPorContinentes();
    }

}

