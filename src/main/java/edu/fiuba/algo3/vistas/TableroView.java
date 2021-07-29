package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.turnos.Turnos;
import edu.fiuba.algo3.vistas.grillas.MenuInicioGrid;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TableroView extends StackPane {

    public TableroView(Stage stage, ContenedorPrincipal contenedorPrincipal) {

        Image imagen = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/fondo_marron.png");
        BackgroundImage fondoImagen = new BackgroundImage(imagen, BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background unFondo = new Background(fondoImagen);
        super.setBackground(unFondo);

        final ImageView selectedImage = new ImageView();
        Image imagentablero = new Image("file:"+System.getProperty("user.dir") + "/src/main/java/edu/fiuba/algo3/resources/imagenes/tableroTEG.png");
        selectedImage.setImage(imagentablero);

        MenuInicioGrid grillaInicio = new MenuInicioGrid(720, 480);

        VBox contenedorVertical = new VBox(50);
        contenedorVertical.setAlignment(Pos.CENTER);

        grillaInicio.getChildren().addAll(selectedImage);
        grillaInicio.add(contenedorVertical,0,1);
        super.getChildren().add(grillaInicio);
    }
}
