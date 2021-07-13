package edu.fiuba.algo3.modelo;


public class Batalla {

    private Pais paisAtacante;
    private Pais paisDefensor;

    Batalla(Pais paisAtacante, Pais paisDefensor){
        this.paisAtacante = paisAtacante;
        this.paisDefensor = paisDefensor;
    }

    public boolean batallar(int cantidadDeDadosAtacante){

        if(!this.validarAtaque()) {throw new AtaqueNoValido();}

        Dados dadosAtacante = this.paisAtacante.tirarDados(cantidadDeDadosAtacante);
        Dados dadosDefensor = this.paisDefensor.tirarDados();

        int[] fichasPerdidas = dadosAtacante.comparaDados(dadosDefensor);

        this.paisAtacante.perderFichas(fichasPerdidas[0]);
        return (this.paisDefensor.perderFichas(fichasPerdidas[1], this.paisAtacante));
    }

    private boolean validarAtaque() {
        return (!this.paisDefensor.esDeJugador(this.paisAtacante.getJugador()));
        //Todo: validar si son adyacentes
    }
}
