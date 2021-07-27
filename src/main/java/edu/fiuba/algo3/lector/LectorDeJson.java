package edu.fiuba.algo3.lector;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoConquista;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoDestruccion;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Continente;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LectorDeJson {
    private Tablero tablero;

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

    public List<ObjetivoTeg> lectorObjetivosConquista(String pathArchivo) {
        Reader jsonLeido = this.setReader(pathArchivo);
        Type datasetListType = new TypeToken<Collection<ObjetivoConquista>>() {}.getType();
        List<ObjetivoTeg> objetivos = new Gson().fromJson(jsonLeido, datasetListType);
        return objetivos;
    }

    public List<ObjetivoDestruccion> retornarObjetivosDestruccion(){
        List<ObjetivoDestruccion> objetivosDestruccion = new ArrayList<>();
        String[] colores =  new String[]  {"Verde","Rosa","Azul","Rojo","Rosa","Negro"};
        int i= 0;
        while (i<6){
            objetivosDestruccion.add(new ObjetivoDestruccion(colores[i]));
            i++;
        }
        return objetivosDestruccion;
    }
}









