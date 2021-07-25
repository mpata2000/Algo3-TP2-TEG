package edu.fiuba.algo3.modelo;


public class Jugador {
    private final String color;
    private final ColeccionDeCartasPais cartasPais = new ColeccionDeCartasPais();
    private int fichas;
    private boolean conquistoPais;

    public Jugador(String unColor){
        this.color = unColor;
        this.conquistoPais = false;
        this.fichas = 0;
    }

    /*
    * Le saca una
     */
    public int sacarFichas(int cantidadFichas){
        if(this.puedeColocarFichas(cantidadFichas)){
            this.fichas = this.fichas - Math.abs(cantidadFichas);
        }else{
            this.fichas = 0;
        }
        return this.fichas;
    }

    public boolean puedeColocarFichas(int cantidadFichas){
        return ((this.fichas - Math.abs(cantidadFichas)) >= 0);
    }

    public void agregarFichas(int cantidadFichas){

        this.fichas += Math.abs(cantidadFichas);
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
        return (this.fichas > 0);
    }

    public void hacerCanje(ColeccionDeCartasPais cartasPaisTeg) {
        this.cartasPais.canjeDeCartas(this,cartasPaisTeg);
    }


    public void conquistoPais() {
        this.conquistoPais = true;
    }
}
