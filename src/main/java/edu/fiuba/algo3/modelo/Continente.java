package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class Continente {
    private String nombreContinente;
    private int fichas;
    private List<Pais> paises;

    public Continente(String nombreContinente, int fichas, List<Pais> paises) {
        this.nombreContinente = nombreContinente;
        this.fichas = fichas;
        this.paises = paises;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public int getFichas() {
        return fichas;
    }

    public String getNombreContinente() {
        return nombreContinente;
    }
}
