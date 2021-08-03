package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ElegirPaisesAtacarController implements EventHandler<ActionEvent> {

    private Stage stage;
    private TextField textoPaisAtacante;
    private TextField textoPaisDefensor;
    private ContenedorPrincipal contenedorPrincipal;

    public ElegirPaisesAtacarController(Stage stage, TextField textoPaisAtacante, TextField textoPaisDefensor, ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.textoPaisAtacante = textoPaisAtacante;
        this.textoPaisDefensor = textoPaisDefensor;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String nombrePaisAtacante = textoPaisAtacante.getText();
        String nombrePaisDefensor = textoPaisDefensor.getText();
        if(nombrePaisAtacante.isEmpty() || nombrePaisDefensor.isEmpty()){
            Alert nombresSinCompletar = new Alert(Alert.AlertType.ERROR);
            nombresSinCompletar.setHeaderText("No completo los dos paises");
            nombresSinCompletar.setContentText("Los dos campos deben de tener un nombre!");
            nombresSinCompletar.show();
        }
        else{

        }
    }
}
