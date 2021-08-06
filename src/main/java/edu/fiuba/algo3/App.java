package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.Constantes;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static Stage popup;
    private static App app;

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }

    public static Stage getPopUpStage(){
        return popup;
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.popup = new Stage();

        App.stage = stage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constantes.MENU_INICIO)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logoYetem.png"))));
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

    public void abrirReglas() throws URISyntaxException {
        URL url = getClass().getResource(Constantes.RUTA_REGLAS);
        File file = new File(url.toURI());

        HostServices hostServices = App.getInstance().getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

}