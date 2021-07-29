package edu.fiuba.algo3.modelo.cartas;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.LectorJson;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class CartasPais {
    private  CartasPais(){}

    public static List<CartaPais> create(String pathArchivo, Tablero tablero){
        Reader jsonLeido = LectorJson.setReader(pathArchivo);
        Type datasetListType = new TypeToken<Collection<CartaPais>>() {}.getType();
        List<CartaPais> cartasPais = new Gson().fromJson(jsonLeido, datasetListType);

        for(CartaPais carta: cartasPais){
            carta.setPais(tablero);
        }
        return cartasPais;
    }
}
