package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TableroController implements Initializable {

    @FXML
    public TextField inputPaisOrigen;
    public TextField inputPaisDestino;
    public TextField inputCantFichas;
    public Label textoJugadorActual;
    public Label textoTipoRonda;
    public Label fichasDisponibles;
    public ListView textPaisJugador;
    public ListView textPaises;
    public ListView textPaisesPorContinente;

    private static HashMap<String, String> colores= new HashMap();

    private String paisOrigen;
    private String paisDestino;
    private int fichas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colores.put("negro", "#000000");
        colores.put("amarillo", "#ee7733");
        colores.put("azul", "#4169e1");
        colores.put("rojo", "#cc3311");
        colores.put("magenta", "#ee3377");
        colores.put("verde", "#009988");
        textoTipoRonda.setText(Turnos.getInstance().devolverRondaActual().getNombre());

        String jugadorActual = Turnos.getInstance().getJugadorActual();
        String colorStyle = "-fx-background-color:"+ colores.get(jugadorActual);

        textPaisJugador.getItems().add(Turnos.getInstance().paisesjugador());
        textPaises.getItems().add(Turnos.getInstance().getTodosLosPaises());
        textPaisesPorContinente.getItems().add(Turnos.getInstance().getPaisesPorContinente());

        fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));
        textoJugadorActual.setText(jugadorActual.toUpperCase());
        if(jugadorActual.equalsIgnoreCase("negro"))colorStyle += ";-fx-text-fill: #fff";
        textoJugadorActual.setStyle(colorStyle);
    }

    public void colocar() {

        seteador();
        if (paisDestino.isBlank() || fichas == 0) {
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("No hay suficientes jugadores");
            insuficientesJugadores.setContentText("Se deben seleccionar un minimo de 2 jugadores!");
            insuficientesJugadores.show();
        }else{
            Turnos.getInstance().colocarFichas(paisDestino, fichas);
            CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
        }
    }


    public void atacar(){
        seteador();
        Turnos.getInstance().atacarACon(paisOrigen,paisDestino,fichas);
        CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
    }


    public void pasar(){
        seteador();
        Turnos.getInstance().pasarFichas(paisOrigen,paisDestino,fichas);
        CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
    }

    public void pasarTurno(){
        Turnos.getInstance().finEtapa();
        CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
    }

    public void mostrarObjetivo(){
        CargadorDeEscena.cargarEscena("/vistas/mostrarObjetivo.fxml");
    }



    private void seteador(){
        /**/
            fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));
            this.fichas = Integer.parseInt(inputCantFichas.getText());
            this.paisDestino = inputPaisDestino.getText();
            this.paisOrigen = inputPaisOrigen.getText();
    }

}
