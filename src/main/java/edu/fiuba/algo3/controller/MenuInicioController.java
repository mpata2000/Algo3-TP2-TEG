package edu.fiuba.algo3.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.CargadorDeEscena;

public class MenuInicioController implements EventHandler<ActionEvent> {

    private Stage stage;

    public void handle(ActionEvent actionEvent){
        CargadorDeEscena.cargarEscena("src/main/resources/vistas/añadirJugadores.fxml");
    }
}
