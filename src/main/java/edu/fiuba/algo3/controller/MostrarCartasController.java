package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MostrarCartasController implements Initializable {
    @FXML
    public ListView<String> listaCartas;
    public Button botonCanje;
    public Button volver;
    public Label carta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaCartas.getItems().addAll(Turnos.getInstance().getCartasJugador());

    }

    private void cerrarEscena(){
        Stage stage = (Stage)volver.getScene().getWindow();
        stage.close();
    }

    public  void hacerCanje(){
        Alert alert;
        String header;
        String mensaje;

        if(Turnos.getInstance().realizarCanje()){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            header = "Canje Confirmado";
            mensaje = "El canje se a completado correctamente";
            cerrarEscena();
            CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO,App.devolverEscena(),"Altego");
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
            header = "Error Canje";
            mensaje = "El canje no pudo ser completado, revise bien sus cartas.";
        }

        alert.setTitle("Canje");
        alert.setHeaderText(header);
        alert.setContentText(mensaje);
        alert.show();
    }


    public void volverAlTablero(){
        cerrarEscena();
        App.devolverEscena().toFront();
    }
}
