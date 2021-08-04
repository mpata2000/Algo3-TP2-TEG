package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
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
    private TextField textoCantidadFichas;
    private ContenedorPrincipal contenedorPrincipal;

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }



    public ElegirPaisesAtacarController(Stage stage, TextField textoPaisAtacante, TextField textoPaisDefensor,TextField textoCantidadFichas,ContenedorPrincipal contenedorPrincipal){
        this.stage = stage;
        this.textoPaisAtacante = textoPaisAtacante;
        this.textoPaisDefensor = textoPaisDefensor;
        this.textoCantidadFichas = textoCantidadFichas;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String nombrePaisAtacante = textoPaisAtacante.getText();
        String nombrePaisDefensor = textoPaisDefensor.getText();
        if ( isNumeric(textoCantidadFichas.getText())){
            Alert cantidadFichasNoNumerico = new Alert(Alert.AlertType.ERROR);
            cantidadFichasNoNumerico.show();
        }
        int cantidadFichas  = Integer.parseInt(textoCantidadFichas.getText());
        textoCantidadFichas.setText(String.valueOf(cantidadFichas));
        if(nombrePaisAtacante.isEmpty() || nombrePaisDefensor.isEmpty() ){
            Alert nombresSinCompletar = new Alert(Alert.AlertType.ERROR);
            nombresSinCompletar.setHeaderText("No completo los dos paises");
            nombresSinCompletar.setContentText("Los dos campos deben de tener un nombre!");
            nombresSinCompletar.show();
        }
        else{

        }
    }
}
