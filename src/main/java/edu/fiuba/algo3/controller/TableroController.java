package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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
    public Label labelErrores;
    public Label fichasDisponibles;
    public ListView textPaisesPorContinente;
    public TableView paisesJugador;
    public TableColumn<Pais,String> nombrePaisJugador;
    public TableColumn<Pais,String> fichasPaisJugador;
    public TableView paisesEnemigo;
    public TableColumn<Pais,String> nombrePaisEnemigo;
    public TableColumn<Pais,String> fichasPaisEnemigo;
    public TableColumn<Pais,String> jugadorEnemigo;

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

        textPaisesPorContinente.getItems().addAll(Turnos.getInstance().getPaisesPorContinente());

        nombrePaisJugador.setCellValueFactory(new PropertyValueFactory<Pais, String>("nombre"));
        fichasPaisJugador.setCellValueFactory(new PropertyValueFactory<Pais, String>("fichas"));
        paisesJugador.getItems().setAll(Turnos.getInstance().getPaisesJugador());

        nombrePaisEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("nombre"));
        fichasPaisEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("fichas"));
        jugadorEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("colorJugador"));
        paisesEnemigo.getItems().setAll(Turnos.getInstance().getPaisesEnemigos());


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
        CargadorDeEscena.cargarPopEscena("/vistas/mostrarObjetivo.fxml","Objetivo");
    }

    public void agarrarCarta(){
        try {
            if (Turnos.getInstance().darCartaPais()){ labelErrores.setText("Agarraste una carta!!!");}
            else{
                labelErrores.setText("No podes agarrar una carta");
            }
        } catch (Exception e){
            labelErrores.setText("OOps En esta ronda no podes agarrar la carta ");
        }
    }

    public void mostrarCartas(){
        CargadorDeEscena.cargarPopEscena("/vistas/mostrarCartas.fxml","Cartas");
    }

    private void seteador(){
            fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));
            this.fichas = Integer.parseInt(inputCantFichas.getText());
            this.paisDestino = inputPaisDestino.getText();
            this.paisOrigen = inputPaisOrigen.getText();
    }

}
