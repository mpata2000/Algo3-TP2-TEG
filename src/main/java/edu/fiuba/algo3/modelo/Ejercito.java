package edu.fiuba.algo3.modelo;

public class Ejercito {
    private int cantidadFichas;

    public Ejercito(){
        this.cantidadFichas = 0;
    }
    public void agregarFichas(int cant){ this.cantidadFichas = cant + this.cantidadFichas;}
    public int getCantFichas(){
        return this.cantidadFichas;
    }

    public int perderFichas(int cantidadFichas){
        this.cantidadFichas -= cantidadFichas;
        if (this.cantidadFichas == 0){
            this.cantidadFichas=0;
        }
        return this.cantidadFichas;
    }

    public void pasarFichasADe(Pais unPais,Jugador unJugador,int cantidadFichas){
        //Testear Si se pasan fichas de mas

        this.cantidadFichas -= cantidadFichas;
        unPais.agregarFichas(cantidadFichas,unJugador);
    }
}

