package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.Constantes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.CargadorDeEscena;

public class MenuInicioController{

    private Stage stage;

    public void comenzarPartida(){
        CargadorDeEscena.cargarEscena(Constantes.rutaAnadirJugador);
    }
}
