package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.AtacarPaisView;
import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ComenzarAtaqueController implements EventHandler<ActionEvent> {

    private Stage stage;
    private ContenedorPrincipal contenedorPrincipal;

    public ComenzarAtaqueController(Stage stage, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Turnos.getInstance();
        contenedorPrincipal.setCentro(new AtacarPaisView(stage, contenedorPrincipal));
    }
}
