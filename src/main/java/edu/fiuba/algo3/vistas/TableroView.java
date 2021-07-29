package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.turnos.Turnos;
import edu.fiuba.algo3.vistas.colores.FontSelection;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import edu.fiuba.algo3.vistas.mensajes.BienvenidaMessage;
import edu.fiuba.algo3.vistas.mensajes.TableroMessage;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Map;

public class TableroView extends StackPane {

    public TableroView(Stage stage, ContenedorPrincipal contenedorPrincipal) {

        Image imagen = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_marron.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background unFondo = new Background(fondoImagen);
        super.setBackground(unFondo);

        final ImageView selectedImage = new ImageView();
        Image imagentablero = new Image("file:" + System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/tableroTEG.png");
        selectedImage.setImage(imagentablero);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox contenedorVertical = new VBox(50);
        contenedorVertical.setAlignment(Pos.CENTER);


        final Map<String, Continente> continentes = Turnos.getInstance().getTeg().getTablero().getContinentes();

        HBox contenedorContinentes = new HBox(50);

        for (Map.Entry<String, Continente> entry : continentes.entrySet()) {

            TableroMessage tableroMessage = new TableroMessage(entry.getKey());
            contenedorContinentes.getChildren().add(tableroMessage);
            }

        grillaInicio.getChildren().addAll(selectedImage);
        grillaInicio.add(contenedorContinentes, 0, 2);
        grillaInicio.add(contenedorVertical,0,1);
        super.getChildren().add(grillaInicio);

    }
}
