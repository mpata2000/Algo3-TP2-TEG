package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MostrarCartasController implements Initializable {
    @FXML
    public ListView listaCartas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaCartas.getItems().add(Turnos.getInstance().getCartasJugador());

    }

    public  void hacerCanje(){
        Turnos.getInstance().realizarCanje();
    }

    public void volverAlTablero(){
        CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
    }
}
