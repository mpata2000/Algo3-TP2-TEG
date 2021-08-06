package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.LimiteDeJugadoresException;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static edu.fiuba.algo3.vistas.Constantes.RUTA_TABLERO;

public class AgregarJugadoresController {

    @FXML
    public CheckBox rojo;
    public CheckBox amarillo;
    public CheckBox verde;
    public CheckBox negro;
    public CheckBox magenta;
    public CheckBox azul;


    private Stage stage;

    public void jugar(){
        List<CheckBox> jugadores = List.of(rojo,amarillo,verde,negro,magenta,azul);
        List<String> jugadoresValidos = new ArrayList<>();


        for(CheckBox jugador:jugadores){
            if(jugador.isSelected()) jugadoresValidos.add(jugador.getId());
        }

        try{
            Turnos.getInstance().comenzarJuego(jugadoresValidos);
            CargadorDeEscena.cargarEscena(RUTA_TABLERO, App.devolverEscena(),"ALTEGO");
        }catch(LimiteDeJugadoresException e) {
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("No hay suficientes jugadores");
            insuficientesJugadores.setContentText("Se deben seleccionar un minimo de 2 jugadores!");
            insuficientesJugadores.show();
        }

    }

    public void volver() {
        CargadorDeEscena.cargarEscena(Constantes.MENU_INICIO,App.devolverEscena(),"ALTEGO");
    }
}
