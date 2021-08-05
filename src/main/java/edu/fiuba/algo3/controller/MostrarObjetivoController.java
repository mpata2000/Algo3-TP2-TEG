package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MostrarObjetivoController implements Initializable {

    @FXML
    public Label objetivo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        objetivo.setText(Turnos.getInstance().textoDeObjetivo());
    }

    public void volverAlTablero(){
        App.getPopUpStage().close();
    }
}
