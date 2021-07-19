package edu.fiuba.algo3.lectorJson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
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

    public void lectorCartasPais(String pathArchivo){
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get(pathArchivo));
            Type datasetListType = new TypeToken<Collection<ObjetoCartas>>() {}.getType();
            List<ObjetoCartas> listaObjetosCartas = new Gson().fromJson(jsonLeido, datasetListType);



            //DEVOLVER listaObjetosCartas

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tablero lectorTablero(String pathArchivo) {
        this.tablero = null;
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get(pathArchivo));
            Type datasetListType = new TypeToken<Collection<Continente>>() {}.getType();
            List<Continente> continentes = new Gson().fromJson(jsonLeido, datasetListType);

            ArrayList<Pais> paises = new ArrayList<>();
            for(Continente continente: continentes){
                paises.addAll(continente.getPaises());
            }

            tablero = new Tablero(continentes,paises);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tablero;
    }
}









