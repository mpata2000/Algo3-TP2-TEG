package edu.fiuba.algo3.modelo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Turnos {
    private int turno = 0;
    private ArrayList<Jugador> rondaJugadores = new ArrayList<Jugador>();
    private TipoRonda tipoDeRonda = new RondaColocacionInicial() ;
    private Teg teg;

    Turnos(Map<String, Jugador> jugadores,Teg teg) {
        jugadores.forEach((color, jugador) -> rondaJugadores.add(jugador));
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
        if (this.rondaJugadores.get((this.turno)+1) != null) this.turno += 1;
        else cambiarRonda();
    }

    public void cambiarRonda(){
        this.turno = 0;
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
        return this.rondaJugadores.get(this.turno);
    }

}

