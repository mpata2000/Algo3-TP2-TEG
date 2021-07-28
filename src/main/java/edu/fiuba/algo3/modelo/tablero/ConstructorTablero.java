package edu.fiuba.algo3.modelo.tablero;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.LectorJson;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConstructorTablero {
    private  ConstructorTablero(){}

    public static Tablero create(String pathArchivo) {
        Tablero tablero;
        Reader jsonLeido = LectorJson.setReader(pathArchivo);
        Type datasetListType = new TypeToken<Collection<Continente>>() {}.getType();
        List<Continente> continentes = new Gson().fromJson(jsonLeido, datasetListType);

        ArrayList<Pais> paises = new ArrayList<>();
        for(Continente continente: continentes){
            paises.addAll(continente.getPaises());
        }
        tablero = new Tablero(continentes,paises);

        return tablero;
    }
}
