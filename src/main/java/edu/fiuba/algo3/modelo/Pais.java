package edu.fiuba.algo3.modelo;

public class Pais {
    private String nombre;
    private Ejercito ejercito;
    private Jugador jugador;

    public Pais(String nombrePais){
        this.ejercito = new Ejercito();
        this.nombre = nombrePais;
        this.jugador = null;
    }

    public String getNombre() {return this.nombre;}
    public Jugador getJugador(){
        return this.jugador;
    }

    public void agregarFichas(int cantidadFichas, Jugador unJugador){
        if(this.jugador == null) { this.jugador = unJugador; }

        if (this.esDeJugador(unJugador)) {
            this.ejercito.agregarFichas(cantidadFichas);
        } else {
            throw new JugadorNoPoseePaisException();
        }
    }

    public boolean perderFichas(int cantidadFichas, Pais paisAtacante){
        if(this.ejercito.perderFichas(cantidadFichas) > 0){
            return false;
        }
        this.actualizarDuenio(paisAtacante);
        return true;
    }

    public void actualizarDuenio(Pais unPais){
        this.jugador = unPais.getJugador();

        unPais.pasarFichasA(this,1);
    }

    public void pasarFichasA(Pais unPais, int cantidadFichas){

        //TestearAdyacente

        this.ejercito.pasarFichasADe(unPais,this.jugador,cantidadFichas);
    }

   public boolean esDeJugador(Jugador jugador) {
       return jugador.esElMismoJugador(this.jugador);
   }
}
