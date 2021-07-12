package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.EjercitoConUnaFichaNoPuedeAtacar;
import edu.fiuba.algo3.excepciones.EjercitoNoPuedeTirarEsaCantidadDeDados;
import edu.fiuba.algo3.excepciones.NoSePuedenCrearCeroDados;

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
        if (this.cantidadFichas < 1){
            this.cantidadFichas=0;
        }
        return this.cantidadFichas;
    }

    public void pasarFichasADe(Pais unPais,Jugador unJugador,int cantidadFichas){
        //Testear Si se pasan fichas de mas

        this.cantidadFichas -= cantidadFichas;
        unPais.agregarFichas(cantidadFichas,unJugador);
    }

    public Dados tirarDados() {
        return new Dados(Math.min(this.cantidadFichas,3));
    }

    public Dados tirarDados(int unaCantidadDeDados) throws EjercitoConUnaFichaNoPuedeAtacar, EjercitoNoPuedeTirarEsaCantidadDeDados, NoSePuedenCrearCeroDados {
        if(this.cantidadFichas < 2){
            throw new EjercitoConUnaFichaNoPuedeAtacar();
        }

        //TODO: Preguntar si tirar excepcion o limitarlo de una y que no pase una
        int cantidadDeDados = Math.min(unaCantidadDeDados,3);

        if(cantidadDeDados >= (this.cantidadFichas)){
            throw new EjercitoNoPuedeTirarEsaCantidadDeDados();
        }
        return new Dados(cantidadDeDados);
    }
}

