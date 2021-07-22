package edu.fiuba.algo3;

import edu.fiuba.algo3.lector.LectorDeJson;

public class Main {
    public static void main(String[] args){
        App.main(args);
        LectorDeJson lector = new LectorDeJson();

        lector.lectorTablero("resources/Teg-Tablero.json");
        lector.lectorCartasPais("resources/Teg-Cartas.json");
    }
}