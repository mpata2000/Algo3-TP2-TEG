package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.AyudaView;
import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class IniciarMenuAyudaController implements EventHandler<ActionEvent> {

    private Stage stage;
    private ContenedorPrincipal contenedorPrincipal;

    public IniciarMenuAyudaController(Stage stage, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        contenedorPrincipal.setCentro(new AyudaView(stage, contenedorPrincipal));
    }
}
