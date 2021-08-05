package edu.fiuba.algo3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Stage popup;

    public static Stage getPopUpStage(){
        return popup;
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.popup = new Stage();

        App.stage = stage;
        Parent root = FXMLLoader.load(App.class.getResource("/vistas/menuInicio.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/logoYetem.png")));
        stage.setResizable(false);
        stage.setTitle("ALTEGO");
        stage.centerOnScreen();
        stage.show();

    }

    public static Stage devolverEscena(){
        return stage;
    }
    public static void main(String[] args) {
        launch();
    }

}