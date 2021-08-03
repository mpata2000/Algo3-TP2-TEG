package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.ReglasView;
import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class IniciarReglasController implements EventHandler<ActionEvent> {

    private Stage stage;
    private ContenedorPrincipal contenedorPrincipal;

    public IniciarReglasController(Stage stage, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        contenedorPrincipal.setCentro(new ReglasView(stage, contenedorPrincipal));
    }
}
