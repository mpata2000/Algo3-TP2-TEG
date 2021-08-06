package edu.fiuba.algo3.vistas;

import java.util.Collections;
import java.util.HashMap;
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
        HashMap<String, String> colores = new HashMap<>();
        colores.put("negro", "#000000");
        colores.put("amarillo", "#FFE100");
        colores.put("azul", "#4169e1");
        colores.put("rojo", "#cc3311");
        colores.put("magenta", "#ff00ff");
        colores.put("verde", "#018047");

        return Collections.unmodifiableMap(colores);
    }


}
