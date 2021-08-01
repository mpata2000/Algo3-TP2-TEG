package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.IniciarJuegoController;
import edu.fiuba.algo3.controllers.IniciarReglasController;
import edu.fiuba.algo3.vistas.botones.AyudaButton;
import edu.fiuba.algo3.vistas.botones.IniciarPartidaButton;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.BienvenidaMessage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuInicioView extends StackPane {

    private Stage stage;

    public MenuInicioView(Stage stage, ContenedorPrincipal contenedorPrincipal) {
        this.stage = stage;

        Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_inicio.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background unFondo = new Background(fondoImagen);
        super.setBackground(unFondo);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        BienvenidaMessage textoMensajeBienvenida = new BienvenidaMessage(FontSelection.Negro);
        grillaInicio.add(textoMensajeBienvenida,0, 0);

        VBox contenedorBotones = new VBox(30);
        contenedorBotones.setAlignment(Pos.CENTER);

        IniciarPartidaButton botonIniciarPartida = new IniciarPartidaButton(new IniciarJuegoController(stage, contenedorPrincipal));
        contenedorBotones.getChildren().add(botonIniciarPartida);

        AyudaButton botonAyuda = new AyudaButton(new IniciarReglasController(stage, contenedorPrincipal));
        contenedorBotones.getChildren().add(botonAyuda);

        grillaInicio.add(contenedorBotones,0,1);
        super.getChildren().add(grillaInicio);
    }
}
