package edu.fiuba.algo3.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.CargadorDeEscena;

public class MenuInicioController{

    private Stage stage;

    public void comenzarPartida(){
        CargadorDeEscena.cargarEscena("/vistas/añadirJugadores.fxml");
    }
}
