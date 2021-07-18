package edu.fiuba.algo3.modelo;


import edu.fiuba.algo3.excepciones.PaisNoEsLimitrofe;

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

        int[] fichasPerdidas = dadosAtacante.comparadorDeDados(dadosDefensor);

        this.paisAtacante.perderFichas(fichasPerdidas[0]);
        return (this.paisDefensor.perderFichas(fichasPerdidas[1], this.paisAtacante));
    }

    private boolean validarAtaque() {
        if(this.paisAtacante.esAdyacente(this.paisDefensor)){
            throw new PaisNoEsLimitrofe();
        }
        return (!this.paisDefensor.esDeJugador(this.paisAtacante.getJugador()));

    }
}
