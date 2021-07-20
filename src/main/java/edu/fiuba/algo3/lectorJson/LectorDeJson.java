package edu.fiuba.algo3.lectorJson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.CartaPais;
import edu.fiuba.algo3.modelo.Continente;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.Tablero;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LectorDeJson {
    private Tablero tablero;

    public LectorDeJson(){

    }

    private Reader setReader(String pathArchivo){
        Reader jsonLeido = null;
        try {
            jsonLeido = Files.newBufferedReader(Paths.get(pathArchivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonLeido;
    }

    public List<CartaPais> lectorCartasPais(String pathArchivo){
        Reader jsonLeido = this.setReader(pathArchivo);

        Type datasetListType = new TypeToken<Collection<CartaPais>>() {}.getType();
        List<CartaPais> cartasPais = new Gson().fromJson(jsonLeido, datasetListType);

        for(CartaPais carta: cartasPais){
            carta.setPais(tablero);
        }
        return cartasPais;
    }

    public Tablero lectorTablero(String pathArchivo) {
        this.tablero = null;
        Reader jsonLeido = this.setReader(pathArchivo);
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









