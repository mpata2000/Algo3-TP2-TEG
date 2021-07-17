package edu.fiuba.algo3.lectorJson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class LectorDeJson {
    public static void lectorPaises() {
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Fronteras.json"));
            Type datasetListType = new TypeToken<Collection<ObjetoFronteras>>() {}.getType();
            List<ObjetoFronteras> listaObjetosPais = new Gson().fromJson(jsonLeido, datasetListType);

            /*
            //Imprimir Lista de Object
            for (int i = 0; i < listaObjetosPais.size(); i++) {
                System.out.println((listaObjetosPais.get(i)).getPais());
                System.out.println((listaObjetosPais.get(i)).getContinente());
                System.out.println((listaObjetosPais.get(i)).getPaisesLimitrofes());
            }
            */

            //DEVOLVER listaObjetosPais

        } catch (IOException e) {
            e.printStackTrace();
        }
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
}









