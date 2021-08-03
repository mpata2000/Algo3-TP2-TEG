package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.App;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class CargadorDeEscena {

    private static FXMLLoader loader;

    private  CargadorDeEscena() {
    }

    public static void cargarEscena(String escena){

        URL path = App.class.getResource("/vista/" + escena + ".fxml");
        loader = new FXMLLoader(path);

        Parent mainNode = null;

        try{
            mainNode = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(mainNode);
        Stage stage = App.devolverEscena();
        stage.setScene(scene);
        stage.show();

    }
}