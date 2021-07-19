package edu.fiuba.algo3;

import edu.fiuba.algo3.lectorJson.LectorDeJson;
import edu.fiuba.algo3.modelo.Teg;

public class Main {
    public static void main(String[] args){
        App.main(args);
        LectorDeJson lector = new LectorDeJson();
        //Descomentar para ejecutar acciones de lectura
        //LectorDeJson.lectorPaises();
        lector.lectorTablero("recurso/Teg-Tablero.json");
        //LectorDeJson.lectorCartasPais("recursos/Teg-Cartas.json");
        //Teg nuevoTeg = new Teg(4);
    }
}