package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Turnos {
    private List<String> jugadores = new ArrayList<>();
    private TipoRonda tipoDeRonda;
    private final Teg teg;
    private ListIterator<String> iteradorJugadores;
    private String jugadorActual;

    Turnos() {
        this.tipoDeRonda = new RondaColocacion(1) ;
        this.teg = new Teg();
    }

    Turnos(Teg teg, List<String> jugadores){
        this.tipoDeRonda = new RondaColocacion(1) ;
        this.teg = teg;
        this.jugadores = jugadores;
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
        this.tipoDeRonda.inicializarRonda(this.jugadores,this.teg);
    }

    public void agregarJugador(String color){
        if(this.jugadores.size() < 6){
            this.jugadores.add(color);
        }
    }

    public void comenzarJuego(){
        this.teg.comenzarJuego(jugadores);
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
        this.tipoDeRonda.inicializarRonda(this.jugadores,this.teg);
    }

    public void atacar(String paisAtacante, String paisDefensor, int cantidad){
        if(this.tipoDeRonda.esAtaque()) {
            this.teg.atacar(jugadorActual,paisAtacante, paisDefensor, cantidad);
        }
    }

    public void reagrupar(String paisUno,String paisdos,int cant){
        if(this.tipoDeRonda.esAtaque()) {
            this.teg.reagrupar(paisUno,paisdos,cant);
        }
    }

    public void colocarEjercitos(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacion()) {
            this.teg.rondaInicialColocarFichas(jugadorActual,nombrePais,cantidad);
            if(!teg.jugadorTieneFichas(jugadorActual)) {this.avanzarTurno();}
        }
    }

    public void avanzarTurno(){
        if (this.iteradorJugadores.hasNext()) {
            this.jugadorActual = this.iteradorJugadores.next();
        }else {
            cambiarRonda();
        }
    }

    public void cambiarRonda(){
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();

        this.tipoDeRonda = this.tipoDeRonda.cambiarDeRonda();
        this.tipoDeRonda.inicializarRonda(this.jugadores,this.teg);
    }

    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }

    public void finAtaque(){
        this.teg.darCarta(this.jugadorActual);
        this.tipoDeRonda = this.tipoDeRonda.cambiarDeRonda();
    }

}

