package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ElegirPaisesAtacarController;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.botones.AtacarButton;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.TableroMessage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class TableroView extends StackPane {

    public TableroView(Stage stage, ContenedorPrincipal contenedorPrincipal) {

        Image imagen = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_marron.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background unFondo = new Background(fondoImagen);
        super.setBackground(unFondo);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(1080, 720);

        VBox contenedorVertical = new VBox(30);
        contenedorVertical.setAlignment(Pos.CENTER);

        final ImageView selectedImage = new ImageView();
        Image imagenTablero = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/tableroTEG.png",500,300,false,false);
        selectedImage.setImage(imagenTablero);

        grillaInicio.add(selectedImage,0,0);

        HBox contenedorContinentes = new HBox(30);
        contenedorContinentes.setAlignment(Pos.CENTER);

        Collection<Continente> listaContinentes = Turnos.getInstance().getTeg().getTablero().getContinentes().values();
        for(Continente continente: Turnos.getInstance().getTeg().getTablero().getContinentes().values()){
            TableroMessage mensaje = new TableroMessage(continente.obtenerInfo());
            contenedorContinentes.getChildren().add(mensaje);
        }

        contenedorVertical.getChildren().add(contenedorContinentes);

        HBox elegirPaisesBox = new HBox(10);
        elegirPaisesBox.setAlignment(Pos.CENTER);

        TextField textoPaisAtacante = new TextField();
        textoPaisAtacante.setPromptText("Ingrese el pais atacante");
        elegirPaisesBox.getChildren().add(textoPaisAtacante);

        TextField textoPaisDefensor = new TextField();
        textoPaisDefensor.setPromptText("Ingrese el pais defensor");
        elegirPaisesBox.getChildren().add(textoPaisDefensor);

        TextField textoCantidadFichas = new TextField();
        textoCantidadFichas.setPromptText("Ingrese la cantidad de fichas");
        elegirPaisesBox.getChildren().add(textoCantidadFichas);

        VBox botonAtacarBox = new VBox(0);
        botonAtacarBox.setAlignment(Pos.BOTTOM_CENTER);

        AtacarButton botonAtacar = new AtacarButton(new ElegirPaisesAtacarController(stage, textoPaisAtacante, textoPaisDefensor, textoCantidadFichas,contenedorPrincipal));
        elegirPaisesBox.getChildren().add(botonAtacar);

        grillaInicio.add(contenedorVertical,0,1);
        grillaInicio.add(elegirPaisesBox,0,2);

        super.getChildren().add(grillaInicio);
    }
}
