package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.vistas.Constantes;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.event.ActionEvent;
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

    }

    public void comenzarPartida() {
        CargadorDeEscena.cargarEscena(Constantes.RUTA_AGREGAR_JUGADOR, App.devolverEscena(),"ALTEGO");
    }

    public void ayuda(){
        try{
            App.getInstance().abrirReglas();
        }catch(Exception e){

        }
    }


    public void acercaDe() {
        App.acercaDe();
    }

    public void cerrar() {
        App.devolverEscena().close();
    }

    public void backMusic(ActionEvent actionEvent) {
    }

    public void playMusic(ActionEvent actionEvent) {
    }

    public void skipMusic(ActionEvent actionEvent) {
    }
}
