package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AñadirJugadoresController {

    private Stage stage;

    public void handle(ActionEvent actionEvent) {
        List<String> coloresJugador = new ArrayList<>();

        if (coloresJugador.size() < 2) {
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("No hay suficientes jugadores");
            insuficientesJugadores.setContentText("Se deben seleccionar un minimo de 2 jugadores!");
            insuficientesJugadores.show();
        } else {
            Turnos.getInstance().agregarJugadores(coloresJugador);
            Turnos.getInstance().comenzarJuego();
            CargadorDeEscena.cargarEscena("src/main/resources/vistas/tablero.fxml");

        }
    }
}
