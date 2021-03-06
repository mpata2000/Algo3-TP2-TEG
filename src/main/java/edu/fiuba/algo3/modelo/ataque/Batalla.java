package edu.fiuba.algo3.modelo.ataque;


import edu.fiuba.algo3.modelo.excepciones.AtaqueNoValidoException;
import edu.fiuba.algo3.modelo.tablero.Pais;

public class Batalla {

    private final Pais paisAtacante;
    private final Pais paisDefensor;

    public Batalla(Pais paisAtacante, Pais paisDefensor){
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
    }

    public boolean batallar(int cantidadDadosAtacante){

        if(!this.validarAtaque()) {throw new AtaqueNoValidoException();}

        Dados dadosAtacante = this.paisAtacante.tirarDados(cantidadDadosAtacante);
        Dados dadosDefensor = this.paisDefensor.tirarDados();

        int[] fichasPerdidas = dadosAtacante.compararDados(dadosDefensor);

        this.paisAtacante.perderFichas(fichasPerdidas[0]);
        return (this.paisDefensor.perderFichas(fichasPerdidas[1], this.paisAtacante));
    }

    private boolean validarAtaque() {
        return(!this.paisDefensor.esDeJugador(this.paisAtacante.getJugador()) && this.paisAtacante.esAdyacente(this.paisDefensor));
    }
}
