package edu.fiuba.algo3.controller;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.Turnos;
import edu.fiuba.algo3.modelo.excepciones.*;
import edu.fiuba.algo3.modelo.rondas.RondaGanador;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.vistas.CargadorDeEscena;
import edu.fiuba.algo3.vistas.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    public TableView<Pais> paisesJugador;
    public TableColumn<Pais,String> nombrePaisJugador;
    public TableColumn<Pais,String> fichasPaisJugador;
    public TableView<Pais> paisesEnemigo;
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
        nombrePaisJugador.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fichasPaisJugador.setCellValueFactory(new PropertyValueFactory<>("fichas"));
        paisesJugador.getItems().setAll(Turnos.getInstance().getPaisesJugador());

        /* Infromacion de tablero Paises enemigos*/
        nombrePaisEnemigo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fichasPaisEnemigo.setCellValueFactory(new PropertyValueFactory<>("fichas"));
        jugadorEnemigo.setCellValueFactory(new PropertyValueFactory<>("colorJugador"));
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

    private void alertError(String mensajeError, String header){
        Alert insuficientesJugadores = new Alert(Alert.AlertType.ERROR);
        insuficientesJugadores.setHeaderText(header);
        insuficientesJugadores.setContentText(mensajeError);
        insuficientesJugadores.show();
    }

    private void  mensajeError(String mensajeError){
        labelErrores.setText(mensajeError);
    }

    public void colocar() {
        if (seteadorUnPaises()){
            try {
                Turnos.getInstance().colocarFichas(paisDestino, fichas);
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO,App.devolverEscena(),"");
            }catch (JugadorNoPoseePaisException e){
                mensajeError(paisDestino + " no es tuyo");
            }catch(JugadorNoTieneSuficientesFichasException e){
                mensajeError("No tenes " + fichas + " disponibles");
            }catch (NoSePuedeHacerEstaAccionEnEstaRondaException e){
                mensajeError("No se puede colocar en " + Turnos.getInstance().devolverRondaActual().getNombre());
            }
        }
    }


    public void atacar(){
        if(seteadorDosPaises()) {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infromacion Ataque");
                alert.setHeaderText("Resultado Batalla");
                if(Turnos.getInstance().atacarACon(paisOrigen, paisDestino, fichas)){
                    alert.setContentText("Capturaste " + paisDestino + " con " + paisOrigen + " se a pasado " +
                            "una ficha y podes pasar mas.");
                }else {
                    alert.setContentText("No lograste capturar " + paisDestino + " con " + paisOrigen + " y perdio fichas.");
                }
                alert.show();
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO, App.devolverEscena(), "");
            }catch(NoSePuedeHacerEstaAccionEnEstaRondaException e){
                mensajeError("No se puede atacar en " + Turnos.getInstance().devolverRondaActual().getNombre());
            }catch (AtaqueNoValidoException e){
                mensajeError("No se puede atacar con " + paisOrigen + " a "+ paisDestino);
            }catch (JugadorNoPoseePaisException e){
                mensajeError("El pais "+ paisOrigen + " no es tuyo.");
            }catch (EjercitoConUnaFichaNoPuedeAtacarException e){
                mensajeError(paisOrigen + "tiene solo una ficha y no puede atacar");
            }catch(EjercitoNoPuedeTirarEsaCantidadDeDadosException e){
                mensajeError("El pais seleccionado no puede atacar con "+ Math.min(Math.abs(fichas),3) + " fichas");
            }
        }
    }


    public void pasar(){
        if(seteadorDosPaises()) {
            try {
                Turnos.getInstance().pasarFichas(paisOrigen, paisDestino, fichas);
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO, App.devolverEscena(), "");
            }catch(NoSePuedeHacerEstaAccionEnEstaRondaException e){
                mensajeError("No se puede pasar fichas en " + Turnos.getInstance().devolverRondaActual().getNombre());
            }catch (PaisNoEsLimitrofeException e){
                mensajeError(paisOrigen + " y " + paisDestino + " no son limitrofes!");
            }catch (JugadorNoPoseePaisException e){
                mensajeError("No podes pasar fichas entre paises que no son tuyos");
            }catch (PaisSinSuficientesFichasParaPasarException e){
                mensajeError(paisOrigen + " no tiene " + fichas + " para pasar a " + paisDestino);
            }
        }
    }

    public void pasarTurno(){
        try{
            Turnos.getInstance().finEtapa();
            if(Turnos.getInstance().devolverRondaActual() instanceof RondaGanador){
                CargadorDeEscena.cargarEscena(Constantes.RUTA_GANADOR,App.devolverEscena(),"");
            }else{
                CargadorDeEscena.cargarEscena(Constantes.RUTA_TABLERO,App.devolverEscena(),"");
            }
        }catch (JugadorSigueTeniendoFichasException e){
            mensajeError("Seguis teniendo fichas para colocar");
        }
    }

    public void mostrarObjetivo(){
        CargadorDeEscena.cargarEscena(Constantes.RUTA_OBJETIVO,new Stage(),"Objetivo");
    }

    public void agarrarCarta(){
        try {
            if (Turnos.getInstance().darCartaPais()){
                labelErrores.setText("Agarraste una carta!!!");
            } else{
                mensajeError("No podes agarrar una carta");
            }
        } catch (Exception e){
            mensajeError("Oops En esta ronda no podes agarrar la carta ");
        }
    }

    public void mostrarCartas(){
        CargadorDeEscena.cargarEscena("/vistas/mostrarCartas.fxml",new Stage(),"Cartas");
    }

    private boolean getContenidoInput(){
        try{
            this.fichas = Integer.parseInt(inputCantFichas.getText());
            this.paisDestino = inputPaisDestino.getText();
            this.paisOrigen = inputPaisOrigen.getText();
        }catch(NumberFormatException e){
            alertError("Debe ingresar un numero en la casilla de fichas","Error Input");
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
            alertError("Se debe rellenar las casillas de pais origen, pais destino y un numero fichas","Error Input");
            return false;
        }
        /*Arreglo el formato del texto*/
        paisDestino = paisDestino.substring(0,1).toUpperCase() + paisDestino.substring(1).toLowerCase();
        paisOrigen = paisOrigen.substring(0,1).toUpperCase() + paisOrigen.substring(1).toLowerCase();
        return true;
    }

    private boolean seteadorUnPaises(){
        fichasDisponibles.setText(Integer.toString(Turnos.getInstance().getFichas()));

        if(getContenidoInput()){
            return false;
        }

        if(paisDestino == null || paisDestino.isBlank() || fichas == 0){
            alertError("Se debe rellenar las casillas de pais destino y un numero fichas","Error Input");
            return false;
        }
        /*Arreglo el formato del texto*/
        paisOrigen = paisOrigen.substring(0,1).toUpperCase() + paisOrigen.substring(1).toLowerCase();
        return true;
    }


    public void acercaDe() {
        App.acercaDe();
    }

    public void volverAMenu() {
        Turnos.reset();
        CargadorDeEscena.cargarEscena(Constantes.MENU_INICIO,App.devolverEscena(),"");
    }

    public void ayuda() {
        try {
            App.getInstance().abrirReglas();
        }catch(Exception e){
            alertError("Se a producido un error al abrir la pruebas","Error Ayuda");
        }
    }

    public void skipMusic() {
        ControladorDeAudio.getInstance().skip();
    }

    public void playMusic() {
        ControladorDeAudio.getInstance().play();
    }

    public void backMusic() {
        ControladorDeAudio.getInstance().back();
    }
}
