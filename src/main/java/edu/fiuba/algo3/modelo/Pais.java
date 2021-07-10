package edu.fiuba.algo3.modelo;

public class Pais {
    private  String nombre;
    private Ejercito ejercito;
    private Jugador jugador;

    public Pais(String nombre){
        this.ejercito = new Ejercito();
        this.nombre = nombre;
    }
    public  String nombre(){
        return this.nombre;
    }

    public void agregarTropas(int cant, Jugador jugador){
        if (this.esDeJugador(jugador)) {
            this.jugador = jugador;
            this.ejercito.agregarTropas(cant);
        }
    }

    public Jugador getJugador(){
        return this.jugador;
    }

   public boolean esDeJugador(Jugador jugador) {
       return jugador.esElMismoJugador(this.jugador);
   }

    public Ejercito devolverEjercito(){
        return this.ejercito;
    }
    public Boolean esDelJugador(Ejercito ejercito){
        return this.ejercito.esDelJugador(ejercito);
    }
}
