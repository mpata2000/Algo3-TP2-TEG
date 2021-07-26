package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.IniciarJuegoController;
import edu.fiuba.algo3.vistas.botones.AyudaButton;
import edu.fiuba.algo3.vistas.botones.ComenzarPartidaButton;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.texto.BienvenidaMessage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuInicioView extends StackPane {

    private Stage stage;

    public MenuInicioView(Stage stagePrincipal, ContenedorPrincipal contenedorPrincipal) {
        this.stage = stagePrincipal;

        Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_inicio.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(fondoImagen);
        super.setBackground(fondo);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox contenedorVertical = new VBox(70);
        contenedorVertical.setAlignment(Pos.CENTER);

        BienvenidaMessage textoMensajeBienvenida = new BienvenidaMessage(FontSelection.Verde);
        contenedorVertical.getChildren().add(textoMensajeBienvenida);

        ComenzarPartidaButton botonComenzarPartida = new ComenzarPartidaButton(new IniciarJuegoController(stagePrincipal, contenedorPrincipal));
        contenedorVertical.getChildren().add(botonComenzarPartida);

        AyudaButton botonAyuda = new AyudaButton(stage);
        contenedorVertical.getChildren().add(botonAyuda);

        grillaInicio.add(contenedorVertical,0,1);
        super.getChildren().add(grillaInicio);
    }
}
