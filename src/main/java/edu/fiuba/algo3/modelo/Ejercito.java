package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.EjercitoConUnaFichaNoPuedeAtacar;
import edu.fiuba.algo3.excepciones.EjercitoNoPuedeTirarEsaCantidadDeDados;
import edu.fiuba.algo3.excepciones.NoSePuedenCrearCeroDados;
import edu.fiuba.algo3.excepciones.PaisSinSuficientesFichasParaPasar;

public class Ejercito {
    private int cantidadFichas;

    public Ejercito(){
        this.cantidadFichas = 1;
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
        if(this.cantidadFichas <= cantidadFichas) {
            throw new PaisSinSuficientesFichasParaPasar();
        }
        this.cantidadFichas -= cantidadFichas;
        unJugador.agregarFichas(cantidadFichas);
        unPais.agregarFichas(cantidadFichas,unJugador);
    }

    public Dados tirarDados() {
        return new Dados(Math.min(this.cantidadFichas,3));
    }

    public Dados tirarDados(int unaCantidadDeDados) throws EjercitoConUnaFichaNoPuedeAtacar, EjercitoNoPuedeTirarEsaCantidadDeDados, NoSePuedenCrearCeroDados {
        if(this.cantidadFichas < 2){
            throw new EjercitoConUnaFichaNoPuedeAtacar();
        }

        int cantidadDeDados = Math.min(unaCantidadDeDados,3);

        if(cantidadDeDados >= (this.cantidadFichas)){
            throw new EjercitoNoPuedeTirarEsaCantidadDeDados();
        }
        return new Dados(cantidadDeDados);
    }

}

