package edu.fiuba.algo3.modelo;

public class CartaPais {
    private final String nombrePais;
    private final String simbolo;
    private Pais pais;

    CartaPais(String nombrePais,String simbolo){
        this.nombrePais = nombrePais;
        this.simbolo = simbolo;
    }

    public void asignarPaisA(Jugador unJugador){
        this.pais.asignarJugadro(unJugador);
    }

}
