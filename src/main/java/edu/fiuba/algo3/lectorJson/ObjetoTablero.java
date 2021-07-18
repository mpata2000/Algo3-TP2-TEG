package edu.fiuba.algo3.lectorJson;

import java.util.ArrayList;

public class ObjetoTablero {
    private String Continente;
    private int Fichas;
    private ArrayList<ObjetoFronteras> Paises;

    public ArrayList<ObjetoFronteras> getPaises() {
        return Paises;
    }

    public int getFichas() {
        return Fichas;
    }

    public String getContinente() {
        return Continente;
    }
}
