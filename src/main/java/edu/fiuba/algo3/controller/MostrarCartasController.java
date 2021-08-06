package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Turnos;
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

    public  void hacerCanje(){
        Alert alert;
        if(Turnos.getInstance().realizarCanje()){
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }else{
            alert = new Alert(Alert.AlertType.ERROR);
        }
        alert.show();
    }

    public void volverAlTablero(){
        Stage stage = (Stage)volver.getScene().getWindow();
        stage.close();
        App.devolverEscena().toFront();
    }
}
