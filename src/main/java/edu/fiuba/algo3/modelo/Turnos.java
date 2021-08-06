package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.rondas.RondaColocacion;
import edu.fiuba.algo3.modelo.rondas.TipoRonda;
import edu.fiuba.algo3.modelo.tablero.Pais;

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

    public static void reset(){
        turnos = null;
    }

    public Turnos() {
        this.teg = new Teg();
    }

    public Turnos(Teg teg, List<String> jugadores){
        this.tipoDeRonda = new RondaColocacion(jugadores, teg) ;
        this.teg = teg;
        this.jugadores = jugadores;
    }

    public String getJugadorActual() {
        return this.tipoDeRonda.getJugadorActual();
    }

    /*
     * Comienza el juego, elijiendo aleatoriamente el orden inicial
     * de los jugadores y creando una primera ronda de colocacion
     */
    public void comenzarJuego(List<String> listaJugadores){
        this.jugadores.addAll(listaJugadores);
        if(this.jugadores.size() < 2 || this.jugadores.size() > 6){
            throw new LimiteDeJugadoresException();
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
        return this.teg.textoObjetivo(tipoDeRonda.getJugadorActual());
    }

    public int getFichas(){return this.teg.getFichas(tipoDeRonda.getJugadorActual());}

    public List<String> getPaisesPorContinentes() {
        return this.teg.getPaisesPorContinentes(tipoDeRonda.getJugadorActual());
    }

    public List<String> getCartasJugador() {
        return this.teg.getCartasJugador(tipoDeRonda.getJugadorActual());
    }


    public List<Pais> getPaisesJugador() {
        return teg.getPaisesJugador(tipoDeRonda.getJugadorActual());
    }

    public List<Pais> getPaisesEnemigos() {
        return teg.getPaisesEnemigos(tipoDeRonda.getJugadorActual());
    }
}

