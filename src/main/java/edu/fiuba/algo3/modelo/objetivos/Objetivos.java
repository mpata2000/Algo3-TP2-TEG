package edu.fiuba.algo3.modelo.objetivos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.LectorJson;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Objetivos {
    private Objetivos(){}

    public static List<ObjetivoTeg> lectorObjetivosConquista(String pathArchivo) {
        Reader jsonLeido = LectorJson.setReader(pathArchivo);
        Type datasetListType = new TypeToken<Collection<ObjetivoConquista>>() {}.getType();
        return new Gson().fromJson(jsonLeido, datasetListType);
    }

    public static List<ObjetivoTeg> creadorDeObjetivososDestruccion(List<Jugador> jugadoresEnOrden){
        List<ObjetivoTeg> objetivosDestruccion = new ArrayList<>();
        for(String color: List.of("Verde","Rosa","Azul","Rojo","Rosa","Negro")){
            objetivosDestruccion.add(new ObjetivoDestruccion(color,jugadoresEnOrden));
        }
        return objetivosDestruccion;
    }
}
