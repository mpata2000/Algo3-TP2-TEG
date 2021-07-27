package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.cartas.CartasPaisJugador;
import edu.fiuba.algo3.modelo.cartas.CartasPaisTeg;
import edu.fiuba.algo3.modelo.cartas.ColeccionDeCartasPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;

public class Jugador {
    private final String color;
    private final CartasPaisJugador cartasPais = new ColeccionDeCartasPais();
    private int fichas;
    private boolean conquistoPais;
    private ObjetivoTeg objetivoTeg;

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

    public void hacerCanje(CartasPaisTeg cartasPaisTeg) {
        this.cartasPais.canjeDeCartas(this,cartasPaisTeg);
    }

    public void conquistoPais() {
        this.conquistoPais = true;
    }

    public void darObjetivo(ObjetivoTeg objetivoTeg) {
        this.objetivoTeg = objetivoTeg;
    }

    public boolean gano(Teg teg){
        return this.objetivoTeg.cumplioObjetivo(teg,this);
    }

    public  void activarCartas(){
        this.cartasPais.activarCartas(this);
    }

    public String devolverColor(){
        return this.color;
    }
}
