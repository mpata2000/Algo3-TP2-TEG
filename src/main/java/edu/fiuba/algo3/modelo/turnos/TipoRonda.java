package edu.fiuba.algo3.modelo.turnos;


import edu.fiuba.algo3.modelo.Teg;

import java.util.List;

public interface TipoRonda {

    void atacar(Teg teg,String paisAtacante, String paisDefensor, int cantidad) throws NoSePuedeHacerEstaAccionEnEstaRondaException;

    void pasarFichas(Teg teg,String paisUno,String paisdos,int cant)throws NoSePuedeHacerEstaAccionEnEstaRondaException;

    void colocarFichas(Teg teg, String nombrePais, int cantidad)throws NoSePuedeHacerEstaAccionEnEstaRondaException;

    TipoRonda finEtapa(List<String> jugadores,Teg teg);

    String getJugadorActual();
}
