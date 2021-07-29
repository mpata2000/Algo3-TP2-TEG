package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.turnos.Turnos;
import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import edu.fiuba.algo3.vistas.MenuInicioView;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarJugadoresController implements EventHandler<ActionEvent> {

    private Stage stage;
    private List<CheckBox> listaBoxes;
    private List<String> listaColores;
    private ContenedorPrincipal contenedorPrincipal;

    public SeleccionarJugadoresController(Stage stage, ContenedorPrincipal contenedorPrincipal, List<CheckBox> listaBoxes, List<String> listaColores){
        this.stage = stage;
        this.listaBoxes = listaBoxes;
        this.listaColores = listaColores;
        this.contenedorPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        List<String> coloresJugador = new ArrayList<>();

        for (int i = 0; i < 6; i++){
            if(listaBoxes.get(i).isSelected()){
                coloresJugador.add(listaColores.get(i));
            }
        }

        if(coloresJugador.size() < 2){
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("No hay suficientes jugadores");
            insuficientesJugadores.setContentText("Se deben seleccionar un minimo de 2 jugadores!");
            insuficientesJugadores.show();
        } else {
            Turnos.getInstance().agregarJugadores(coloresJugador);
            Turnos.getInstance().comenzarJuego();
            contenedorPrincipal.setCentro(new TableroView(stage, contenedorPrincipal));
        }
    }

}
