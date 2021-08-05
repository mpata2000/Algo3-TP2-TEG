package edu.fiuba.algo3.vistas;
import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
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

        URL root = App.class.getResource(escena);
        loader = new FXMLLoader(root);

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

    public static void cargarPopEscena(String escena){

        URL root = App.class.getResource(escena);
        loader = new FXMLLoader(root);

        Parent mainNode = null;

        try{
            mainNode = loader.load();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        Scene scene = new Scene(mainNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


    public static void cargarEscena(String escena, ObjetivoTeg objetivo){
        /*CREAR ESCENA CON EL OBJETIVO*/
        URL root = App.class.getResource(escena);
        loader = new FXMLLoader(root);

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