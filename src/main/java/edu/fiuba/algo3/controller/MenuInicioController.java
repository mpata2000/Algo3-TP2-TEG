package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.Constantes;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuInicioController implements Initializable {

    @FXML
    public AnchorPane bc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void comenzarPartida() {
        CargadorDeEscena.cargarEscena(Constantes.RUTA_AGREGAR_JUGADOR, App.devolverEscena(),"ALTEGO");
    }

    public void ayuda(){
        try{
            App.getInstance().abrirReglas();
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Ayuda");
            alert.setHeaderText("No se pudo abrir las reglas");
            alert.setContentText("Ocurrio un error al intentar de abrir las relgas pdf.");
            alert.show();
        }
    }


    public void acercaDe() {
        App.acercaDe();
    }

    public void cerrar() {
        App.devolverEscena().close();
    }

    public void backMusic() {
        ControladorDeAudio.getInstance().back();
    }

    public void playMusic() {
        ControladorDeAudio.getInstance().play();
    }

    public void skipMusic() {
        ControladorDeAudio.getInstance().skip();
    }
}
