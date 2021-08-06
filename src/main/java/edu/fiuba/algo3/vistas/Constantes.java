package edu.fiuba.algo3.vistas;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constantes {

    public static final Map<String, String> colores = initMap();

    private static Map<String, String> initMap() {
        HashMap<String, String> colores = new HashMap();
        colores.put("negro", "#000000");
        colores.put("amarillo", "#ee7733");
        colores.put("azul", "#4169e1");
        colores.put("rojo", "#cc3311");
        colores.put("magenta", "#ee3377");
        colores.put("verde", "#009988");

        return Collections.unmodifiableMap(colores);
    }

    public static String rutaGanador = "/vistas/ganador.fxml";
    public static String rutaTablero = "/vistas/tablero.fxml";
    public static String rutaAgregarJugador = "/vistas/agregarJugadores.fxml";

}
