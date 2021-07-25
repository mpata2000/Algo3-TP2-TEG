package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.ContenedorPrincipal;
import edu.fiuba.algo3.vistas.VistaInicio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
        //App.main(args); //Esto ya estaba aca
    }

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("ALTEGO");

        ContenedorPrincipal contenedorPrincipal = new ContenedorPrincipal(stage);
        contenedorPrincipal.setCentro(new VistaInicio(stage,contenedorPrincipal));

        //var label = new Label("Bienvenido al juego ALTEGO");
        var scene = new Scene(contenedorPrincipal, 640, 480);
        stage.setScene(scene);

        stage.show();
    }
}