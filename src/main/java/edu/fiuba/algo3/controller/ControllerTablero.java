package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import javafx.stage.Stage;

public class ControllerTablero {
    public String fichas;
    public String paisOrigen;
    public String paisDestino;


    public void colocar(){
        Turnos.getInstance().colocarFichas(paisDestino,Integer.parseInt(fichas));
        CargadorDeEscena.cargarEscena("/vistas/añadirJugadores.fxml");
    }


    public void atacar(){
        Turnos.getInstance().atacarACon(paisOrigen,paisDestino,Integer.parseInt(fichas));
        CargadorDeEscena.cargarEscena("/vistas/añadirJugadores.fxml");
    }


    public void pasar(){
        Turnos.getInstance().pasarFichas(paisOrigen,paisDestino,Integer.parseInt(fichas));
        CargadorDeEscena.cargarEscena("/vistas/añadirJugadores.fxml");
    }

    public void pasarRonda(){
        Turnos.getInstance().finEtapa();
        CargadorDeEscena.cargarEscena("/vistas/añadirJugadores.fxml");
    }

    public void mostrarObjetivo(){
        Turnos.getInstance().mostrarObjetivo();
        /*CrearVistaConEseObjetivo*/
    }
}
