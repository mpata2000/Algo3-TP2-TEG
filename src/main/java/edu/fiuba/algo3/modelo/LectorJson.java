package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorJson {
    private LectorJson(){}

    /*
     * Lee el archivo enviado a traves de la ruta y lo
     * devuelve para posteriormente crear un Gson
     */
    public static Reader setReader(String pathArchivo){
        Reader jsonLeido = null;
        try {
            jsonLeido = Files.newBufferedReader(Paths.get(pathArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonLeido;
    }
}
