package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.vistas.Constantes;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuInicioController implements Initializable {

    @FXML
    public AnchorPane bc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bc.setStyle("-fx-background-image: url('../images/logoYetem.png')");
    }

    public void comenzarPartida(){
        CargadorDeEscena.cargarEscena(Constantes.rutaAgregarJugador);
    }
}
