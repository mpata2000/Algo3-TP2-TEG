package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.ataque.EjercitoConUnaFichaNoPuedeAtacarException;
import edu.fiuba.algo3.modelo.ataque.EjercitoNoPuedeTirarEsaCantidadDeDadosException;
import edu.fiuba.algo3.modelo.ataque.NoSePuedenCrearCeroDadosException;
import edu.fiuba.algo3.modelo.ataque.Dados;
import edu.fiuba.algo3.modelo.Jugador;

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


    public void pasarFichasADe(Pais unPais, Jugador unJugador, int cantidadFichas){
        if(this.cantidadFichas <= cantidadFichas) {
            throw new PaisSinSuficientesFichasParaPasarException();
        }
        this.cantidadFichas -= cantidadFichas;
        unJugador.agregarFichas(cantidadFichas);
        unPais.agregarFichas(cantidadFichas,unJugador);
    }

    public Dados tirarDados() {
        return new Dados(Math.min(this.cantidadFichas,3));
    }

    public Dados tirarDados(int unaCantidadDeDados) throws EjercitoConUnaFichaNoPuedeAtacarException, EjercitoNoPuedeTirarEsaCantidadDeDadosException, NoSePuedenCrearCeroDadosException {
        if(this.cantidadFichas < 2){
            throw new EjercitoConUnaFichaNoPuedeAtacarException();
        }

        int cantidadDeDados = Math.min(unaCantidadDeDados,3);

        if(cantidadDeDados >= (this.cantidadFichas)){
            throw new EjercitoNoPuedeTirarEsaCantidadDeDadosException();
        }
        return new Dados(cantidadDeDados);
    }

}

