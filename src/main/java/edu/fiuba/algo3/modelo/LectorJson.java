package edu.fiuba.algo3.modelo;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectorJson {
    private LectorJson(){}

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
