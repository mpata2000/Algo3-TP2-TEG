package edu.fiuba.algo3.lectorJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.Pais;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LectorDeJson {
    public static void lector() {
        //Aca simulamos con un Json
        String json = "{'nombre': 'Francia','continente': 'Europa'}";
        Map<?,?> userMap = new Gson().fromJson(json, Map.class);
        System.out.println(userMap.get("nombre"));
        //Reader jsonLeido = null;
        /*try {
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Fronteras.json"));
            //Type datasetListType = new TypeToken<Collection<Pais>>() {}.getType();
            Map<?,?> userMap = new Gson().fromJson(jsonLeido, Map.class);
            //List<Pais> datasets = new Gson().fromJson(jsonLeido, datasetListType);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*for (Pais dataset : datasets) {
            System.out.println(dataset.getNombre());
        }*/
    }
}

