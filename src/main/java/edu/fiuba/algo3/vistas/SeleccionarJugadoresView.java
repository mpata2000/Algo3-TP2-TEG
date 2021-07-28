package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.SeleccionarJugadoresController;
import edu.fiuba.algo3.controllers.VolverInicioController;
import edu.fiuba.algo3.vistas.botones.ColorPickerButton;
import edu.fiuba.algo3.vistas.botones.VolverInicioButton;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.SeleccionarJugadorMessage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SeleccionarJugadoresView extends StackPane {

    public SeleccionarJugadoresView(Stage stage, ContenedorPrincipal contenedorPrincipal) {

        Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_seleccion_jugadores.jpeg");
        BackgroundImage fondoImagen = new BackgroundImage(imagen,null,null, BackgroundPosition.CENTER,null);
        Background fondo = new Background(fondoImagen);
        super.setBackground(fondo);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox nombreJuego = new VBox(0);
        nombreJuego.setAlignment(Pos.TOP_CENTER);
        SeleccionarJugadorMessage textoAlgoHootInicio = new SeleccionarJugadorMessage(FontSelection.Negro);
        nombreJuego.getChildren().add(textoAlgoHootInicio);

        VBox botonesColores = new VBox(0);
        botonesColores.setAlignment(Pos.BOTTOM_CENTER);
        botonesColores.setSpacing(10);


        ColorPickerButton botonAmarillo = new ColorPickerButton(new SeleccionarJugadoresController(stage, contenedorPrincipal), FontSelection.Amarillo);
        botonesColores.getChildren().add(botonAmarillo);

        ColorPickerButton botonMagenta = new ColorPickerButton(new SeleccionarJugadoresController(stage, contenedorPrincipal), FontSelection.Magenta);
        botonesColores.getChildren().add(botonMagenta);

        ColorPickerButton botonNegro = new ColorPickerButton(new SeleccionarJugadoresController(stage, contenedorPrincipal), FontSelection.Negro);
        botonesColores.getChildren().add(botonNegro);

        ColorPickerButton botonVerde = new ColorPickerButton(new SeleccionarJugadoresController(stage, contenedorPrincipal), FontSelection.Verde);
        botonesColores.getChildren().add(botonVerde);

        ColorPickerButton botonAzul = new ColorPickerButton(new SeleccionarJugadoresController(stage, contenedorPrincipal), FontSelection.Azul);
        botonesColores.getChildren().add(botonAzul);

        VBox botonesMenu = new VBox(0);
        botonesMenu.setAlignment(Pos.BOTTOM_CENTER);

        VolverInicioButton botonVolverInicio = new VolverInicioButton(new VolverInicioController(stage, contenedorPrincipal));
        botonesMenu.getChildren().add(botonVolverInicio);

        grillaInicio.add(nombreJuego,0,0);
        grillaInicio.add(botonesColores,0,1);
        grillaInicio.add(botonesMenu,0,2);

        super.getChildren().add(grillaInicio);
    }

}
