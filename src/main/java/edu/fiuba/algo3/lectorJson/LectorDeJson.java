package edu.fiuba.algo3.lectorJson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.Pais;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LectorDeJson {
    /*
    public class LectorDeJson {
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
    public static void lector(String[] args) {
        String json = "[{'Pais': 'Francia'},{'Pais': 'Gran Bretaña'}]";

        // create the type for the collection. In this case define that the collection is of type Dataset
        Type paisListType = new TypeToken<ArrayList<Pais>>(){}.getType();
        List<Pais> datasets = new Gson().fromJson(json, paisListType);
        for (Pais dataset : datasets) {
            System.out.println(dataset.getNombre());
            //System.out.println(dataset.getAlbum_id());
        }
    }
}

