package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class CargadorDeEscena {

    private static FXMLLoader loader;

    private  CargadorDeEscena() {
    }

    private static Parent cargarArchivo(String escena){
        URL root = App.class.getResource(escena);
        loader = new FXMLLoader(root);

        Parent mainNode = null;

        try{
            mainNode = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return mainNode;
    }

    public static void cargarEscena(String escena,Stage stage, String titulo){
        Scene scene = new Scene(CargadorDeEscena.cargarArchivo(escena));
        stage.setScene(scene);
        if(!titulo.isBlank()){
            stage.setTitle(titulo);
        }
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/logoYetem.png"))));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }
}