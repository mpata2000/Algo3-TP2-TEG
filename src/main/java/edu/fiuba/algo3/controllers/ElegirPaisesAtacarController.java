package edu.fiuba.algo3.controllers;

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
            Turnos.getInstance().atacarACon(nombrePaisAtacante,nombrePaisDefensor,cantidadFichas);
            /*try
                AlgoHoot.getInstance().inicializarJuego(nombreJugador1,nombreJugador2,new CriterioDesorden());
            }
            catch(ArchivoNoEncontradoException ex) {
                ex.printStackTrace();
                Alert archivoNoEncontrado = new Alert(Alert.AlertType.ERROR);
                archivoNoEncontrado.setHeaderText("Archivo no Encontrado");
                archivoNoEncontrado.setContentText("Por favor revise que existe el archivo \n" + System.getProperty("user.dir") + ex.getMessage() + "\n y tiene el formato correcto");
                archivoNoEncontrado.show();
            }*/

            //contenedorPrincipal.setCentro(new VistaTransicionPregunta(stage,contenedorPrincipal));
        }
    }
}
