package edu.fiuba.algo3;

import edu.fiuba.algo3.lectorJson.LectorDeJson;

public class Main {
    public static void main(String[] args){
        App.main(args);

        //Descomentar para ejecutar acciones de lectura
        LectorDeJson.lectorPaises();
        //LectorDeJson.lectorTablero();
        //LectorDeJson.lectorCartasPais();
    }
}