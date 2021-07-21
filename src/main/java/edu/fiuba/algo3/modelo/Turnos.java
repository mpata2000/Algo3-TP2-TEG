package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Turnos {
    private ArrayList<Jugador> rondaJugadores = new ArrayList<Jugador>();
    private TipoRonda tipoDeRonda = new RondaColocacionInicial() ;
    private Teg teg;
    private ListIterator<Jugador> iter;
    private Jugador jugadorActual;

    Turnos(Map<String, Jugador> jugadores,Teg teg) {
        jugadores.forEach((color, jugador) -> rondaJugadores.add(jugador));
        this.iter = this.rondaJugadores.listIterator();
        this.jugadorActual = this.iter.next();
        this.teg = teg;
    }

    /*public boolean esTurnoDe(Pais unPais){
        return (unPais.esDeJugador(this.rondaJugadores.get(this.turno)));
    }*/

    public boolean atacar(String paisAtacante, String paisDefensor, int cantidad){
        if(this.tipoDeRonda.esRondaAtaque()) {
            return this.teg.atacar(paisAtacante, paisDefensor, cantidad);
        }
        return false;
    }

    public void reagrupar(String paisUno,String paisdos,int cant){
        if(this.tipoDeRonda.esRondaAtaque()) {
            this.teg.reagrupar(paisUno,paisdos,cant);
            this.avanzarRonda();
        }
    }

    public void colocarEjercitosEnRondaInicial(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacionInicial()) {
            Jugador jugador = this.devolverDeQuienEsTurno();
            this.teg.rondaInicialColocarEjercitos(jugador,nombrePais,cantidad);
            if(jugador.devolverFichas()== 0) {this.avanzarRonda();}
        }
    }

    public void colocarEjercitos(String nombrePais, int cantidad){
        if(this.tipoDeRonda.esColocacion()){
            Jugador jugador = this.devolverDeQuienEsTurno();
            teg.rondaColocarEjercitos(jugador,nombrePais,cantidad);
        }
    }

    public void avanzarRonda(){
        if (this.iter.hasNext()) this.jugadorActual = this.iter.next();
        else cambiarRonda();
    }

    public void cambiarRonda(){
        ListIterator<Jugador> iter = this.rondaJugadores.listIterator();
        if (this.tipoDeRonda.esColocacionInicial()) this.tipoDeRonda = new RondaAtaque() ;
        if (this.tipoDeRonda.esRondaAtaque()) this.tipoDeRonda = new RondaColocacion();
        if(this.tipoDeRonda.esColocacion()) this.tipoDeRonda = new RondaAtaque();
    }

    public ArrayList <Jugador> getJugadores(){
        return this.rondaJugadores;
    }

    public TipoRonda devolverRondaActual(){
        return this.tipoDeRonda;
    }

    public Jugador devolverDeQuienEsTurno(){
        return jugadorActual;
    }

    public void finAtaque(){
        this.avanzarRonda();
    }

}

