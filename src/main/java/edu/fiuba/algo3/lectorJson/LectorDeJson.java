package edu.fiuba.algo3.lectorJson;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.Pais;
import edu.fiuba.algo3.modelo.Tablero;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LectorDeJson {
    Tablero tablero;

    LectorDeJson(){
        this.tablero = new Tablero();
    }

    public static void lectorPaises() {
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/prueba.json"));
            //Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Fronteras.json"));
            Type datasetListType = new TypeToken<Collection<Pais>>() {}.getType();
            List<Pais> listaObjetosPais = new Gson().fromJson(jsonLeido, datasetListType);


            //LinkedList<Pais> listaPaises = new LinkedList<Pais>();
            for(Pais pais: listaObjetosPais){
                //Pais unPais = new Pais(pais.getPais(), pais.getPaisesLimitrofes());
                //TODO: Si se pasa tablero/Diccionario de continentes se puede agregar pais
                //listaPaises.add(unPais);

            }

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

    public static Tablero lectorTablero() {
        try {
            //Lectura del archivo Json
            Reader jsonLeido = Files.newBufferedReader(Paths.get("paises/Teg-Tablero.json"));
            Type datasetListType = new TypeToken<Collection<ObjetoTablero>>() {}.getType();
            List<ObjetoTablero> listaObjetosPais = new Gson().fromJson(jsonLeido, datasetListType);

            for(ObjetoTablero continente: listaObjetosPais){
                System.out.println(continente.getContinente());
                System.out.println(continente.getFichas());
                System.out.println(continente.getPaises());

                System.out.print("[");
                for(ObjetoFronteras pais: continente.getPaises()) {
                    System.out.print(pais.getPais());
                }
                System.out.println("]");
            }

            //DEVOLVER tablero

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}









