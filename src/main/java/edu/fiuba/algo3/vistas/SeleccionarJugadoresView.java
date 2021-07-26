package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.SeleccionarJugadoresController;
import edu.fiuba.algo3.controllers.VolverInicioController;
import edu.fiuba.algo3.vistas.botones.IniciarPartidaButton;
import edu.fiuba.algo3.vistas.botones.VolverInicioButton;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.texto.SeleccionarJugadorMessage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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

        VBox cajaJugadores = new VBox(10);
        cajaJugadores.setAlignment(Pos.CENTER);

        Label labelJugador1 = new Label("Ingrese el nombre del Jugador 1");
        labelJugador1.setFont(Font.font(FontSelection.TitleFontType,20));
        labelJugador1.setTextFill(Color.web(FontSelection.Negro));
        cajaJugadores.getChildren().add(labelJugador1);

        TextField campoNombreJugador1 = new TextField();
        campoNombreJugador1.setMaxWidth(300);
        cajaJugadores.getChildren().add(campoNombreJugador1);

        Label labelJugador2 = new Label("Ingrese el nombre del Jugador 2");
        labelJugador2.setFont(Font.font(FontSelection.TitleFontType,20));
        labelJugador2.setTextFill(Color.web(FontSelection.Negro));
        cajaJugadores.getChildren().add(labelJugador2);

        TextField campoNombreJugador2 = new TextField();
        campoNombreJugador2.setMaxWidth(300);
        cajaJugadores.getChildren().add(campoNombreJugador2);

        VBox botonConfirmado = new VBox(0);
        botonConfirmado.setAlignment(Pos.BOTTOM_CENTER);

        IniciarPartidaButton botonInicio = new IniciarPartidaButton(new SeleccionarJugadoresController(stage,campoNombreJugador1,campoNombreJugador2,contenedorPrincipal));
        botonConfirmado.getChildren().add(botonInicio);

        VolverInicioButton botonVolverInicio = new VolverInicioButton(new VolverInicioController(stage, contenedorPrincipal));
        botonConfirmado.getChildren().add(botonVolverInicio);

        grillaInicio.add(nombreJuego,0,0);
        grillaInicio.add(cajaJugadores,0,1);
        grillaInicio.add(botonConfirmado,0,2);

        super.getChildren().add(grillaInicio);
    }

}
