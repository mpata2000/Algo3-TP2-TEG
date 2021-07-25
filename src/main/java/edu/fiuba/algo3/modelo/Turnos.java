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
        this.tipoDeRonda.inicializarRonda(this.jugadorActual,this.teg);
    }

    public String getJugadorActual() {
        return jugadorActual;
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
        this.tipoDeRonda.inicializarRonda(this.jugadorActual,this.teg);
    }

    public void atacar(String paisAtacante, String paisDefensor, int cantidad){
        if(this.tipoDeRonda.esAtaque()) {
            this.teg.atacar(jugadorActual,paisAtacante, paisDefensor, cantidad);
            //Todo: Preguntar a jugador cuantas fichas pasar
        }
    }

    public void pasarFichas(String paisUno,String paisdos,int cant){
        if(this.tipoDeRonda.esAtaque()) {
            this.teg.pasarFichas(paisUno,paisdos,cant);
        }
    }

    public void colocarEjercitos(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacion()) {
            this.teg.colocarFichas(jugadorActual,nombrePais,cantidad);
            if(!teg.jugadorTieneFichas(jugadorActual)) {this.avanzarTurno();}
        }
    }

    private void avanzarTurno(){
        if (this.iteradorJugadores.hasNext()) {
            this.jugadorActual = this.iteradorJugadores.next();
            this.tipoDeRonda.inicializarRonda(this.jugadorActual,teg);
        }else {
            cambiarRonda();
        }
    }

    private void cambiarRonda(){
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();

        this.tipoDeRonda = this.tipoDeRonda.cambiarDeRonda();
        this.tipoDeRonda.inicializarRonda(this.jugadorActual,this.teg);
    }

    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }

    public void finAtaque(){
        if(this.tipoDeRonda.esAtaque()){
            this.teg.darCarta(this.jugadorActual);
            this.tipoDeRonda = this.tipoDeRonda.cambiarDeRonda();
        }
    }

    public void finReagrupacion(){
        if(this.tipoDeRonda.esReagrupacion()) {
            this.avanzarTurno();
        }
    }

}

