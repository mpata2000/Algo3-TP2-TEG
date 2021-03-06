package edu.fiuba.algo3.modelo.rondas;


import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public interface TipoRonda {

    String getNombre();

    boolean atacarACon(Teg teg, String paisAtacante, String paisDefensor, int cantidad);

    void pasarFichas(Teg teg,String paisOrigen,String paisDestino,int cant);

    void colocarFichas(Teg teg, String nombrePais, int cantidad);

    TipoRonda finEtapa(List<String> jugadores,Teg teg);

    String getJugadorActual();

    boolean darCartaPais(Teg teg);

    boolean hacerCanje(Teg teg);

}
