package edu.fiuba.algo3.modelo;


public class Jugador {
    private final String color;
    private final ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais();
    private int fichasIniciales ;
    private boolean conquistoPais;

    public Jugador(String unColor){
        this.color = unColor;
        this.conquistoPais = false;
        this.fichasIniciales = 0;
    }

    public void actualizarFichasActuales(int cantidadFichas){
        if(cantidadFichas > this.fichasIniciales){
            this.fichasIniciales = 0;
            return;
        }
        this.fichasIniciales = this.fichasIniciales-cantidadFichas;
    }

    public boolean jugadorPuedeColocarFichas(int cantidadFichas){
        return ((this.fichasIniciales - cantidadFichas) >= 0);
    }

    public void agregarFichas(int cantidadFichas){
        this.fichasIniciales += cantidadFichas;
    }

    public boolean darCartaPais(CartaPais carta){
        if(this.conquistoPais) {
            cartasPais.agregarCartaPais(carta);
            this.conquistoPais = false;
            return true;
        }

        return false;
    }

    public boolean esElMismoJugador(Jugador unJugador){
        return this == unJugador;
    }

    public boolean tieneFichas() {
        return (this.fichasIniciales > 0);
    }

    public void hacerCanje() {
        this.cartasPais.canjeDeCartas();
    }


    public void conquistoPais() {
        this.conquistoPais = true;
    }
}
