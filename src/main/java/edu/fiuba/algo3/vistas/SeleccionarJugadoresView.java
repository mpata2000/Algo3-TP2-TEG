package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.SeleccionarJugadoresController;
import edu.fiuba.algo3.vistas.botones.IniciarPartidaButton;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.SeleccionarJugadorMessage;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SeleccionarJugadoresView extends StackPane {

    private Stage stage;

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

        List<CheckBox> listaBoxes = new ArrayList<>();

        Label l = new Label("This is a check box");
        List<String> listaColores = List.of("Azul","Rojo","Amarillo","Verde","Magenta","Negro");
        botonesColores.getChildren().add(l);
        for (int i = 0; i < listaColores.size(); i++) {
            CheckBox c = new CheckBox(listaColores.get(i));
            botonesColores.getChildren().add(c);
            listaBoxes.add(c);
        }

        VBox botonesMenu = new VBox(0);
        botonesMenu.setAlignment(Pos.BOTTOM_CENTER);

        IniciarPartidaButton botonIniciarPartida = new IniciarPartidaButton(new SeleccionarJugadoresController(stage, contenedorPrincipal, listaBoxes, listaColores));
        botonesMenu.getChildren().add(botonIniciarPartida);

        grillaInicio.add(nombreJuego,0,0);
        grillaInicio.add(botonesColores,0,1);
        grillaInicio.add(botonesMenu,0,2);

        super.getChildren().add(grillaInicio);
    }
}
