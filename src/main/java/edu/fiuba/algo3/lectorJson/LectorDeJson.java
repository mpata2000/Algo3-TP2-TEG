package edu.fiuba.algo3.lectorJson;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.Pais;

/*
public class LectorDeJson {

    public static void main(String[] args){
        try {
            Reader myReader = Files.newBufferedReader(Paths.get("paises/Teg-Fronteras.json"));
            Gson gson = new Gson();
            Map<?,?> userMap = gson.fromJson(myReader, Map.class);
            System.out.println(userMap.get("Pais"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
*/
public class LectorDeJson {
    public static void main(String[] args) {
        String json = "[{Pais:'Francia'},{Pais:'Gran Bretaña'}]";

        //Gson gson = new Gson();
        // create the type for the collection. In this case define that the collection is of type Dataset
        Type datasetListType = new TypeToken<ArrayList<Pais>>(){}.getType();
        List<Pais> datasets = new Gson().fromJson(json, datasetListType);
        for (Pais dataset : datasets) {
            System.out.println(dataset.getNombre());
            //System.out.println(dataset.getAlbum_id());
        }

    }
}