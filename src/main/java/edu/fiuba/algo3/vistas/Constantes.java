package edu.fiuba.algo3.vistas;

import java.util.Map;

public class Constantes {

    private Constantes(){}

    public static final Map<String, String> COLORES = initMap();
    public static final String RUTA_REGLAS ="/pdfs/ReglasTeg.pdf";
    public static final String RUTA_GANADOR = "/vistas/ganador.fxml";
    public static final String RUTA_TABLERO = "/vistas/tablero.fxml";
    public static final String RUTA_AGREGAR_JUGADOR = "/vistas/agregarJugadores.fxml";
    public static final String RUTA_OBJETIVO = "/vistas/mostrarObjetivo.fxml";
    public static final String MENU_INICIO = "/vistas/menuInicio.fxml";

    private static Map<String, String> initMap() {

        return Map.of("negro", "#000000", "amarillo", "#FFE100", "azul", "#4169e1", "rojo", "#cc3311", "magenta", "#ff00ff", "verde", "#018047");
    }


}
