package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import edu.fiuba.algo3.vistas.MenuInicioView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage){
        stage.setTitle("ALTEGO");

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage);
        contenedorPrincipal.setCentro(new MenuInicioView(stage,contenedorPrincipal));

        var scene = new Scene(contenedorPrincipal, 1000, 700);
        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}