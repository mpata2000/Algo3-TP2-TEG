package edu.fiuba.algo3.modelo;

public class Ejercito {
    private int cantFichas;

    public Ejercito(){
        this.cantFichas = 0;
    }
    public Boolean esDelJugador(Ejercito ejercito){
        return true;
    }
    public void agregarTropas(int cant){ this.cantFichas = cant + this.cantFichas;}
    public int getCantFichas(){
        return this.cantFichas;
    }

}

