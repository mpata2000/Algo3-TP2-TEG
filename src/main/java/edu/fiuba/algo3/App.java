package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import edu.fiuba.algo3.vistas.MenuInicioView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

import edu.fiuba.algo3.vistas.CargadorDeEscena;

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

        var scene = new Scene(contenedorPrincipal, 1080, 800);
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