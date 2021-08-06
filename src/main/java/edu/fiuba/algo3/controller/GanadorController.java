package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GanadorController implements Initializable {

    @FXML
    public Label ganador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String color = Turnos.getInstance().getJugadorActual();
        StringBuilder style = new StringBuilder();
        if (color.equalsIgnoreCase("negro"))  style.append("-fx-text-fill:#fff;");
        style.append("-fx-background-color:").append(Constantes.COLORES.get(color));
        ganador.setStyle(style.toString());

        ganador.setText(color.toUpperCase());
    }

    public void cerrar(){
        CargadorDeEscena.cargarEscena(Constantes.MENU_INICIO, App.devolverEscena(),"ALTEGO");
    }
}
