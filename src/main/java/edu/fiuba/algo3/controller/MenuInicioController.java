package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.Constantes;
import edu.fiuba.algo3.vistas.CargadorDeEscena;

public class MenuInicioController {

    public void comenzarPartida() {
        CargadorDeEscena.cargarEscena(Constantes.RUTA_AGREGAR_JUGADOR);
    }

    public void ayuda(){
        try{
            App.getInstance().abrirReglas();
        }catch(Exception e){

        }
    }


    public void acercaDe() {
    }
}
