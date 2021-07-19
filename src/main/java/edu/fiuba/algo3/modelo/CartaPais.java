package edu.fiuba.algo3.modelo;

public class CartaPais {
    private String nombrePais;
    private String simbolo;
    private Pais pais;

    CartaPais(String nombrePais){
        this.nombrePais = nombrePais;
    }

    public void asignarPaisA(Jugador unJugador){
        this.pais.asignarJugadro(unJugador);
    }
/**
    public void setearPais(Tablero tablero){
        this.pais = tablero.getPais(this.nombrePais);
    }

    public Pais devolverPais(){
        return this.pais;
    }
 **/
}
