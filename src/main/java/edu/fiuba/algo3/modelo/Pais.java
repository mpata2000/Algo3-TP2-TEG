package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.JugadorNoPoseePaisException;

public class Pais {
    private String Pais;
    private Ejercito ejercito;
    private Jugador jugador;

    public Pais(String nombrePais){
        this.ejercito = new Ejercito();
        this.Pais = nombrePais;
        this.jugador = null;
    }

    public String getNombre() {return this.Pais;}
    public Jugador getJugador(){
        return this.jugador;
    }

    public void agregarFichas(int cantidadFichas, Jugador unJugador) throws JugadorNoPoseePaisException {
        if(this.jugador == null) { this.jugador = unJugador; }

        if (this.esDeJugador(unJugador)) {
            this.ejercito.agregarFichas(cantidadFichas);
        } else {
            throw new JugadorNoPoseePaisException();
        }
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

        //TestearAdyacente

        this.ejercito.pasarFichasADe(unPais,this.jugador,cantidadFichas);
    }

    public boolean esDeJugador(Jugador jugador) {
        return jugador.esElMismoJugador(this.jugador);
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
