package edu.fiuba.algo3.lectorJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LectorDeJson {
    public static void lector() {
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Fronteras.json"));
            Type datasetListType = new TypeToken<ArrayList<Object>>() {}.getType();
            List<Object> listaObjetosPais = new Gson().fromJson(jsonLeido, datasetListType);

            //Imprimir Lista de Object
            for (int i = 0; i < listaObjetosPais.size(); i++) {
                System.out.println(listaObjetosPais.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

