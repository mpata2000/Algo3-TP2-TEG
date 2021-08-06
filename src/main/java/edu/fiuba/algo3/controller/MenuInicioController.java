package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.Constantes;
import edu.fiuba.algo3.vistas.CargadorDeEscena;

public class MenuInicioController{

    public void comenzarPartida(){
        CargadorDeEscena.cargarEscena(Constantes.rutaAgregarJugador);
    }
}
