package edu.fiuba.algo3.modelo;

public class CartaPais {
    private final String nombrePais;
    private final String simbolo;
    private Pais pais;

    CartaPais(String nombrePais,String simbolo){
        this.nombrePais = nombrePais;
        this.simbolo = simbolo;
    }

    public void asignarPaisA(Jugador unJugador){
        this.pais.asignarJugador(unJugador);
    }

    public void setPais(Tablero tablero) {
        this.pais = tablero.getPais(this.nombrePais);
    }

    public Pais getPais() {
        return this.pais;
    }

    public String getNombrePais() {
        return this.nombrePais;
    }

    public String getSimbolo() {
        return this.simbolo;
    }
}
