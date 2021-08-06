package edu.fiuba.algo3;

import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage stage;
    private static App app;

    public static App getInstance(){
        if(app == null){
            app = new App();
        }
        return app;
    }


    @Override
    public void start(Stage stage){
        App.stage = stage;
        CargadorDeEscena.cargarEscena(Constantes.MENU_INICIO,stage,"ALTEGO");

    }

    public static Stage devolverEscena(){
        return stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public void abrirReglas() throws URISyntaxException {
        URL url = getClass().getResource(Constantes.RUTA_REGLAS);
        assert url != null;

        File file = new File(url.toURI());
        HostServices hostServices = App.getInstance().getHostServices();
        hostServices.showDocument(file.getAbsolutePath());
    }

    public static void acercaDe(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de...");
        alert.setHeaderText("Acerca de la aplicacion");
        String mensaje = "ALTEGO es un juego que recrea al juego de mesa Teg en Java, creado para el TP2 de Algoritmos 3\n\n" +
                " Integrantes: \n" +
                "\t\t\t> Martin Pata Fraile de Manerola \n" +
                "\t\t\t> Andrés Tomás Kübler \n" +
                "\t\t\t> Sofía Marchesini\n" +
                "\t\t\t> Santiago Vaccarelli";
        alert.setContentText(mensaje);
        alert.show();
    }

}