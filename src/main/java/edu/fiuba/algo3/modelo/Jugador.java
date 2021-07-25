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

    public void actualizarFichasActuales(int cantidadFichas){
        if(cantidadFichas > this.fichas){
            this.fichas = 0;
            return;
        }
        this.fichas = this.fichas -cantidadFichas;
    }

    public boolean jugadorPuedeColocarFichas(int cantidadFichas){
        return ((this.fichas - cantidadFichas) >= 0);
    }

    public void agregarFichas(int cantidadFichas){
        this.fichas += cantidadFichas;
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

    public int getFichas() {
        return fichas;
    }

    public void hacerCanje() {
        this.cartasPais.canjeDeCartas();
    }


    public void conquistoPais() {
        this.conquistoPais = true;
    }
}
