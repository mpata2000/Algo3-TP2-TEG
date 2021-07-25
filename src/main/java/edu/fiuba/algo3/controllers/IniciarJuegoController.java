package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class IniciarJuegoController implements EventHandler<ActionEvent> {

    private Stage stage;
    private ContenedorPrincipal contenedorPrincipal;

    public IniciarJuegoController(Stage stage, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.contenedorPrincipal = contenedorPrincipal;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        //contenedorPrincipal.setCentro(new VistaPedirNombres(stage,contenedorPrincipal));
    }
}
