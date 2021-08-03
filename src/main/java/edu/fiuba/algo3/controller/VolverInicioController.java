package edu.fiuba.algo3.controller;


import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import edu.fiuba.algo3.vistas.MenuInicioView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class VolverInicioController implements EventHandler<ActionEvent> {
    private Stage stage;
    private ContenedorPrincipal contenedorPrincipal;

    public VolverInicioController(Stage stage, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        contenedorPrincipal.setCentro(new MenuInicioView(stage, contenedorPrincipal));
    }
}
