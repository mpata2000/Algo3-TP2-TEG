package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.modelo.rondas.JugadorSigueTeniendoFichasException;
import edu.fiuba.algo3.modelo.rondas.NoSePuedeHacerEstaAccionEnEstaRondaException;
import edu.fiuba.algo3.modelo.rondas.RondaGanador;
import edu.fiuba.algo3.modelo.tablero.JugadorNoPoseePaisException;
import edu.fiuba.algo3.modelo.tablero.JugadorNoTieneSuficientesFichasException;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TableroController implements Initializable {

    @FXML
    public TextField inputPaisOrigen;
    public TextField inputPaisDestino;
    public TextField inputCantFichas;
    public Label textoJugadorActual;
    public Label textoTipoRonda;
    public Label labelErrores;
    public Label fichasDisponibles;
    public ListView<String> textPaisesPorContinente;
    public TableView paisesJugador;
    public TableColumn<Pais,String> nombrePaisJugador;
    public TableColumn<Pais,String> fichasPaisJugador;
    public TableView paisesEnemigo;
    public TableColumn<Pais,String> nombrePaisEnemigo;
    public TableColumn<Pais,String> fichasPaisEnemigo;
    public TableColumn<Pais,String> jugadorEnemigo;


    private String paisOrigen;
    private String paisDestino;
    private int fichas;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textoTipoRonda.setText(Turnos.getInstance().devolverRondaActual().getNombre());

        /* Infromacion de tablero Paises Jugador*/
        nombrePaisJugador.setCellValueFactory(new PropertyValueFactory<Pais, String>("nombre"));
        fichasPaisJugador.setCellValueFactory(new PropertyValueFactory<Pais, String>("fichas"));
        paisesJugador.getItems().setAll(Turnos.getInstance().getPaisesJugador());

        /* Infromacion de tablero Paises enemigos*/
        nombrePaisEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("nombre"));
        fichasPaisEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("fichas"));
        jugadorEnemigo.setCellValueFactory(new PropertyValueFactory<Pais, String>("colorJugador"));
        paisesEnemigo.getItems().setAll(Turnos.getInstance().getPaisesEnemigos());

        /*Infromacion de Paises por continente del Jugador*/
        textPaisesPorContinente.getItems().addAll(Turnos.getInstance().getPaisesPorContinentes());



        /* Infromacion del Jugador*/
        String jugadorActual = Turnos.getInstance().getJugadorActual();
        String colorStyle = "-fx-background-color:"+ Constantes.COLORES.get(jugadorActual);
        textoJugadorActual.setText(jugadorActual.toUpperCase());
        if(jugadorActual.equalsIgnoreCase("negro")) {
            colorStyle += ";-fx-text-fill: #fff";
        }
        fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));
        textoJugadorActual.setStyle(colorStyle);
    }

    public void colocar() {
        if (seteadorUnPaises()){
            try {
                Turnos.getInstance().colocarFichas(paisDestino, fichas);
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO);
            }catch (JugadorNoPoseePaisException e){
                labelErrores.setText(paisDestino + " no es tuyo");
            }catch(JugadorNoTieneSuficientesFichasException e){
                labelErrores.setText("No tenes " + fichas + " disponibles");
            }catch (NoSePuedeHacerEstaAccionEnEstaRondaException e){
                labelErrores.setText("No se puede colocar en " + Turnos.getInstance().devolverRondaActual().getNombre());
            }
        }
    }


    public void atacar(){
        if(seteadorDosPaises()) {
            Turnos.getInstance().atacarACon(paisOrigen, paisDestino, fichas);
            CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
        }
    }


    public void pasar(){
        if(seteadorDosPaises()) {

            Turnos.getInstance().pasarFichas(paisOrigen, paisDestino, fichas);
            CargadorDeEscena.cargarEscena("/vistas/tablero.fxml");
        }
    }

    public void pasarTurno(){
        try{
            Turnos.getInstance().finEtapa();
            if(Turnos.getInstance().devolverRondaActual() instanceof RondaGanador){
                CargadorDeEscena.cargarEscena(Constantes.RUTA_GANADOR);
            }else{
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO);
            }
        }catch (JugadorSigueTeniendoFichasException e){
            labelErrores.setText("Seguis teniendo fichas para colocar");
        }
    }

    public void mostrarObjetivo(){
        CargadorDeEscena.cargarPopEscena("/vistas/mostrarObjetivo.fxml","Objetivo");
    }

    public void agarrarCarta(){
        try {
            if (Turnos.getInstance().darCartaPais()){
                labelErrores.setText("Agarraste una carta!!!");
            } else{
                labelErrores.setText("No podes agarrar una carta");
            }
        } catch (Exception e){
            labelErrores.setText("Oops En esta ronda no podes agarrar la carta ");
        }
    }

    public void mostrarCartas(){
        CargadorDeEscena.cargarPopEscena("/vistas/mostrarCartas.fxml","Cartas");
    }

    private boolean getContenidoInput(){
        try{
            this.fichas = Integer.parseInt(inputCantFichas.getText());
            this.paisDestino = inputPaisDestino.getText();
            this.paisOrigen = inputPaisOrigen.getText();
        }catch(NumberFormatException e){
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("Error");
            insuficientesJugadores.setContentText("Debe ingresar un numero en la casilla de fichas");
            insuficientesJugadores.show();
            return true;
        }
        return false;
    }

    private boolean seteadorDosPaises(){
        fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));

        if(getContenidoInput()){
            return false;
        }

        if( paisOrigen == null || paisDestino == null || paisOrigen.isBlank() || paisDestino.isBlank() || fichas == 0){
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("Error");
            insuficientesJugadores.setContentText("Se debe rellenar las casillas de pais origen, pais destino y un numero fichas");
            insuficientesJugadores.show();
            return false;
        }
        return true;
    }

    private boolean seteadorUnPaises(){
        fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));

        if(getContenidoInput()){
            return false;
        }

        if(paisDestino == null || paisDestino.isBlank() || fichas == 0){
            Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
            insuficientesJugadores.setHeaderText("Error");
            insuficientesJugadores.setContentText("Se debe rellenar las casillas de pais destino y un numero fichas");
            insuficientesJugadores.show();
            return false;
        }
        return true;
    }


}
