package edu.fiuba.algo3.modelo.turnos;


import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public interface TipoRonda {

    boolean esColocacion();

    boolean esAtaque();

    boolean esReagrupacion();

    void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad);

    void pasarFichas(Teg teg,String paisUno,String paisdos,int cant);

    void colocarEjercitos(Teg teg,String nombrePais, int cantidad);

    TipoRonda finEtapa(List<String> jugadores);
}
