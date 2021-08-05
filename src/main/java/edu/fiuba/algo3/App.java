package edu.fiuba.algo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
        Parent root = FXMLLoader.load(App.class.getResource("/vistas/menuInicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



             /* Parent root = FXMLLoader.load(getClass().getResource("/vistas/menuInicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/

        /*Parent rootTablero = FXMLLoader.load(getClass().getResource("/vistas/tablero.fxml"));

        Scene sceneTablero = new Scene(rootTablero);
        stage.setScene(sceneTablero);
        stage.show();
        /*stage.setTitle("ALTEGO");

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage);
        contenedorPrincipal.setCentro(new MenuInicioView(stage,contenedorPrincipal));

        var scene = new Scene(contenedorPrincipal, 1000, 700);
        stage.setScene(scene);

        stage.show();*/

    }

    public static Stage devolverEscena(){
        return stage;
    }
    public static void main(String[] args) {
        launch();
    }

}