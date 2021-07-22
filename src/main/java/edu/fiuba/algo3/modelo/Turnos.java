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
        this.tipoDeRonda = new RondaColocacionInicial() ;
        this.teg = new Teg();
    }

    Turnos(Teg teg, List<String> jugadores){
        this.tipoDeRonda = new RondaColocacionInicial() ;
        this.teg = teg;
        this.jugadores = jugadores;
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
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
    }

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        if(this.tipoDeRonda.esAtaqueReagrupacion()) {
            return this.teg.atacar(jugadorActual,paisAtacante, paisDefensor, cantidad);
        }
        return false;
    }

    public void reagrupar(String paisUno,String paisdos,int cant){
        if(this.tipoDeRonda.esAtaqueReagrupacion()) {
            this.teg.reagrupar(paisUno,paisdos,cant);
            this.avanzarRonda();
        }
    }

    public void colocarEjercitosEnRondaInicial(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacionInicial()) {
            this.teg.rondaInicialColocarFichas(jugadorActual,nombrePais,cantidad);
            if(!teg.jugadorTieneFichas(jugadorActual)) {this.avanzarRonda();}
        }
    }

    public void colocarEjercitos(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacion()){
            teg.rondaColocarFichas(jugadorActual,nombrePais,cantidad);
        }
    }

    public void avanzarRonda(){
        if (this.iteradorJugadores.hasNext()) this.jugadorActual = this.iteradorJugadores.next();
        else cambiarRonda();
    }

    public void cambiarRonda(){
        this.iteradorJugadores = this.jugadores.listIterator();
        this.jugadorActual = this.iteradorJugadores.next();
        this.tipoDeRonda = this.tipoDeRonda.cambiarDeRonda();
    }

    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }

    public String devolverDeQuienEsTurno(){
        return jugadorActual;
    }

    public void finAtaque(){
        teg.calcularFichasDisponiblesDe(jugadorActual);
        this.avanzarRonda();
    }

}

