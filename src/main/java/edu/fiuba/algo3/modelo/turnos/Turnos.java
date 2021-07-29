package edu.fiuba.algo3.modelo.turnos;


import edu.fiuba.algo3.modelo.Teg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

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
        this.tipoDeRonda = new RondaColocacion(jugadores) ;
        this.teg = teg;
        this.jugadores = jugadores;
    }

    public Teg getTeg() {
        return this.teg;
    }

    public String getJugadorActual() {
        return this.tipoDeRonda.getJugadorActual();
    }

    public void agregarJugadores(List<String> listaJugadores){
        if(listaJugadores.size() > 6){
            throw new LimiteDeJugadoresException();
        }
        this.jugadores.addAll(listaJugadores);
    }

    public void comenzarJuego(){
        if(this.jugadores.size() < 2){
            throw new NoHaySuficientesJugadoresException();
        }
        Collections.shuffle(jugadores); //Mezclar los jugadores como si tiraron Dados
        this.teg.comenzarJuego(jugadores);
        this.tipoDeRonda = new RondaColocacion(jugadores) ;
    }

    public void atacar(String paisAtacante, String paisDefensor, int cantidad){
        this.tipoDeRonda.atacar(this.teg,paisAtacante, paisDefensor, cantidad);
    }

    public void pasarFichas(String paisUno,String paisdos,int cant){
        this.tipoDeRonda.pasarFichas(this.teg,paisUno,paisdos,cant);
    }

    public void colocarEjercitos(String nombrePais, int cantidad){
        this.tipoDeRonda.colocarFichas(this.teg,nombrePais,cantidad);
    }

    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }


    public void finEtapa(){
        this.tipoDeRonda = this.tipoDeRonda.finEtapa(jugadores,this.teg);
    }

}

