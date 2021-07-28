package edu.fiuba.algo3.modelo.turnos;


import edu.fiuba.algo3.modelo.Teg;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Turnos {
    private List<String> jugadores = new ArrayList<>();
    private TipoRonda tipoDeRonda;
    private final Teg teg;

    public Turnos() {
        this.teg = new Teg();
    }

    public Turnos(Teg teg, List<String> jugadores){
        this.tipoDeRonda = new RondaColocacion(jugadores) ;
        this.teg = teg;
        this.jugadores = jugadores;
    }

    public String getJugadorActual() {
        return this.tipoDeRonda.getJugadorActual();
    }

    public void agregarJugador(String color){
        if(this.jugadores.size() >= 6){
            throw new LimiteDeJugadoresException();
        }
        this.jugadores.add(color);
    }

    public void comenzarJuego(){
        if(this.jugadores.size() < 6){
            throw new NoHaySuficientesJugadoresException();
        }
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

