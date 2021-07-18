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
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LectorDeJson {
    Tablero tablero;

    LectorDeJson(){
        this.tablero = new Tablero();
    }

    public static void lectorCartasPais(){
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Cartas.json"));
            Type datasetListType = new TypeToken<Collection<ObjetoCartas>>() {}.getType();
            List<ObjetoCartas> listaObjetosCartas = new Gson().fromJson(jsonLeido, datasetListType);

            /*
            //Imprimir Lista de Object
            for (int i = 0; i < listaObjetosCartas.size(); i++) {
                System.out.println((listaObjetosCartas.get(i)).getPais());
                System.out.println((listaObjetosCartas.get(i)).getSimbolo());
            }
            */

            //DEVOLVER listaObjetosCartas

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Tablero lectorTablero() {
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Tablero.json"));
            Type datasetListType = new TypeToken<Collection<Continente>>() {}.getType();
            List<Continente> continentes = new Gson().fromJson(jsonLeido, datasetListType);

            for(Continente continente: continentes){
                System.out.println(continente.getNombreContinente());
                System.out.println(continente.getFichas());
                System.out.println(continente.getPaises());
            }

            //DEVOLVER tablero

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}









