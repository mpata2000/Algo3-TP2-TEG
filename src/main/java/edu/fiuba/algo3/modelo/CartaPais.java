package edu.fiuba.algo3.modelo;

public class CartaPais {
    private String nombrePais;
    private Pais pais;

    CartaPais(Pais pais){
        this.nombrePais = pais.getNombre();
    }
/**
    public void setearPais(Jugador jugador){
        Mapa mapa = new Mapa();
        Pais pais = mapa.buscarPais(this.nombrePais);
        pais.setearEjercito(jugador.ejercito());
        this.pais = pais;
    }

    public Pais devolverPais(){
        return this.pais;
    }
 **/
}
