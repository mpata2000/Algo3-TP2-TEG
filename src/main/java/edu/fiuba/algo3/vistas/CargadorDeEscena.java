package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;


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

    public static void cargarEscena(String escena){
        Scene scene = new Scene(CargadorDeEscena.cargarArchivo(escena));
        Stage stage = App.devolverEscena();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public static void cargarPopEscena(String escena, String titulo){
        Scene scene = new Scene(CargadorDeEscena.cargarArchivo(escena));
        Stage stage = App.getPopUpStage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}