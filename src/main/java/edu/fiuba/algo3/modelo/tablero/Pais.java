package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.ataque.Dados;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoPoseePaisException;
import edu.fiuba.algo3.modelo.excepciones.JugadorNoTieneSuficientesFichasException;
import edu.fiuba.algo3.modelo.excepciones.PaisNoEsLimitrofeException;

import java.util.List;

public class Pais {
    private final String nombrePais;
    private Ejercito ejercito;
    private Jugador jugador;
    private final List<String> paisesLimitrofes;

    public int getFichas() {
        return ejercito.getCantFichas();
    }

    public Pais(String nombrePais, List<String>paisesLimitrofes){
        this.nombrePais = nombrePais;
        this.paisesLimitrofes = paisesLimitrofes;
    }

    public String getNombre() {return this.nombrePais;}

    public Jugador getJugador(){
        return this.jugador;
    }

    public String getColorJugador(){
        return this.jugador.getColor().toUpperCase();
    }

    public boolean esAdyacente(Pais unPais){
        return this.paisesLimitrofes.stream().anyMatch((unPais.getNombre())::equalsIgnoreCase);
    }

    public void asignarJugador(Jugador unJugador){
        if(this.jugador != null){return;}
        this.jugador = unJugador;
        this.ejercito = new Ejercito();
    }

    public void agregarFichas(int cantidadFichas, Jugador unJugador) {
        if (!jugador.esElMismoJugador(unJugador)){
            throw new JugadorNoPoseePaisException();
        }
        if (!unJugador.puedeColocarFichas(cantidadFichas)) {
            throw new JugadorNoTieneSuficientesFichasException();
        }
        unJugador.sacarFichas(cantidadFichas);
        this.ejercito.agregarFichas(cantidadFichas);
    }

    /*
    * Le resta cantidadFichas a las fichas del ejercito
    * Devuelve la cantidad de fichas restantes.
    * */
    public int perderFichas(int cantidadFichas){
        return this.ejercito.perderFichas(cantidadFichas);
    }

    /*
    * Cambia el dueño del pais actual con el de unPais
    * y le pide que le pase una ficha
    */
    private void actualizarDuenio(Pais unPais){
        this.jugador = unPais.getJugador();

        unPais.pasarFichasA(this,1);
    }

    /*
    * Resta fichas al ejercito del pais, si el ejercito queda con 0 fichas
    * el pais se actualiza de dueño con el de pais atacante,se le pasa una ficha
    * y devuelve true
    * En caso contrario(no se conquistoel pais), devuelve false
    */
    public boolean perderFichas(int cantidadFichas, Pais paisAtacante){
        if(this.ejercito.perderFichas(cantidadFichas) > 0){
            return false;
        }
        this.actualizarDuenio(paisAtacante);
        return true;
    }

    public void pasarFichasA(Pais unPais, int cantidadFichas){
        if(!this.esAdyacente(unPais)){
            throw new PaisNoEsLimitrofeException();
        }
        if (!unPais.esDeJugador(this.jugador)){
            throw new JugadorNoPoseePaisException();
        }
        this.ejercito.pasarFichasADe(unPais,this.jugador,cantidadFichas);
    }

    public boolean esDeJugador(Jugador unJugador) {
        return unJugador.esElMismoJugador(this.jugador);
    }

    /*
    * Tira la misma cantidad de dados que fichas en el pais, hasta 3
    * Devuelve el conjunto de Dados
    */
    public Dados tirarDados(){
        return this.ejercito.tirarDados();
    }

    /*
    * Tira la uno dado menos que fichas en el pais, hasta 3
    * Devuelve el conjunto de Dados
    */
    public Dados tirarDados(int cantidadDeDados){
        return this.ejercito.tirarDados(cantidadDeDados);
    }


}
