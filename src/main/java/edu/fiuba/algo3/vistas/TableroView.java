package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.TableroMessage;
import javafx.geometry.Pos;
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

        final ImageView selectedImage = new ImageView();
        Image imagenTablero = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/tableroTEG.png");
        selectedImage.setImage(imagenTablero);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox contenedorVertical = new VBox(50);
        contenedorVertical.setAlignment(Pos.CENTER);

        HBox contenedorContinentes = new HBox(50);
        contenedorContinentes.setAlignment(Pos.CENTER);

        HBox contenedorContinentes2 = new HBox(50);
        contenedorContinentes2.setAlignment(Pos.CENTER);

        Collection<Continente> listaContinentes = Turnos.getInstance().getTeg().getTablero().getContinentes().values();
        for(Continente continente: Turnos.getInstance().getTeg().getTablero().getContinentes().values()){
            TableroMessage mensaje = new TableroMessage(continente.obtenerInfo());
            contenedorContinentes.getChildren().add(mensaje);
        }
        //boxes.get(i%5).getChildren().add(tableroMessage);

        grillaInicio.getChildren().addAll(selectedImage);
        grillaInicio.add(contenedorVertical,0,1);
        grillaInicio.add(contenedorContinentes,0,2);

        super.getChildren().add(grillaInicio);

    }
}
