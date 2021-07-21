package edu.fiuba.algo3;

import edu.fiuba.algo3.lector.LectorDeJson;

public class Main {
    public static void main(String[] args){
        App.main(args);
        LectorDeJson lector = new LectorDeJson();
        //Descomentar para ejecutar acciones de lectura

        lector.lectorTablero("resources/Teg-Tablero.json");
        lector.lectorCartasPais("resources/Teg-Cartas.json");
        //Teg nuevoTeg = new Teg(4);
    }
}