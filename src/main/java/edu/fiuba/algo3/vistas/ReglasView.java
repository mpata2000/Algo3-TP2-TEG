package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.VolverInicioController;
import edu.fiuba.algo3.vistas.botones.VolverInicioButton;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.ReglasMessage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReglasView extends StackPane {

    public ReglasView(Stage stage, ContenedorPrincipal contenedorPrincipal) {

        Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_inicio.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background unFondo = new Background(fondoImagen);
        super.setBackground(unFondo);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox contenedorVertical = new VBox(50);
        contenedorVertical.setAlignment(Pos.CENTER);

        ReglasMessage mensajeAyuda = new ReglasMessage();
        contenedorVertical.getChildren().add(mensajeAyuda);

        VolverInicioButton botonVolverInicio = new VolverInicioButton(new VolverInicioController(stage, contenedorPrincipal));
        contenedorVertical.getChildren().add(botonVolverInicio);

        grillaInicio.add(contenedorVertical,0,1);
        super.getChildren().add(grillaInicio);
    }
}
