package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ContenedorPrincipal extends BorderPane {

    public ContenedorPrincipal(Stage stage) {

        Button botonEnviar = new Button();
        botonEnviar.setText("BotonDePrueba");
        botonEnviar.setAlignment(Pos.CENTER);
        this.setTop(botonEnviar);
    }

    public void setCentro(StackPane centro){
        this.setCenter(centro);
    }
}