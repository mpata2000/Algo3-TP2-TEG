package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartasPaisTeg;
import edu.fiuba.algo3.modelo.cartas.CartasPais;
import edu.fiuba.algo3.modelo.cartas.MazoDeCartasPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.objetivos.Objetivos;
import edu.fiuba.algo3.modelo.tablero.ConstructorTablero;
import edu.fiuba.algo3.modelo.tablero.Pais;
import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.*;


public class Teg {
    private final Tablero tablero;
    private Map<String, Jugador> jugadores = new LinkedHashMap<>();
    private final CartasPaisTeg cartas;
    private final List<ObjetivoTeg> objetivos = new ArrayList<>();
    public static final String PATHJSON = "src/main/resources/json/";

    public Teg() {
        this.tablero = ConstructorTablero.create(PATHJSON.concat("Teg-Tablero.json"));
        this.cartas = new MazoDeCartasPais(CartasPais.create(PATHJSON.concat("Teg-Cartas.json"), this.tablero));
        this.objetivos.addAll(Objetivos.objetivosConquista(PATHJSON.concat("Teg-Objetivos.json")));
    }

    public Teg(Tablero tablero, Map<String, Jugador> jugadores, MazoDeCartasPais mazoDeCartasPais) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.cartas = mazoDeCartasPais;
    }

    private Jugador getJugador(String jugador){
        return jugadores.get(jugador.toUpperCase());
    }

    public void comenzarJuego(List<String> colores) {
        for (String color : colores) {
            this.jugadores.put(color.toUpperCase(), new Jugador(color));
        }
        this.cartas.asignarPaises(new ArrayList<>(this.jugadores.values()));
        this.objetivos.addAll(Objetivos.objetivososDestruccion(new ArrayList<>(this.jugadores.values())));
        this.asignarObjetivos();
    }

    private void asignarObjetivos() {

        Collections.shuffle(this.objetivos);
        int i = 0;
        for (Jugador jugador : jugadores.values()) {
            jugador.darObjetivo(this.objetivos.get(i));
            i++;
        }
    }

    public void colocarFichas(String colorJugador, String nombrePais, int cant) {
        this.tablero.agregarFichas(nombrePais, this.getJugador(colorJugador), cant);
    }

    public boolean atacarConA(String colorJugador, String paisAtacante, String paisDefensor, int cantidad) {
        Jugador jugador = this.getJugador(colorJugador);
        if (tablero.atacar(jugador, paisAtacante, paisDefensor, cantidad)) {
            jugador.conquistoPais();
            return true;
        }
        return false;
    }

    public void sacarConquistaDePaisAJugadores() {
        for (Jugador jugador : jugadores.values()) {
            jugador.sacarConquista();
        }
    }

    public void pasarFichas(String colorJugador, String paisUno, String paisDos, int cant) {
        this.tablero.pasarFichas(this.getJugador(colorJugador), paisUno, paisDos, cant);
    }

    public void agregarFichasDisponiblesA(String colorJugador) {
        Jugador jugador = this.getJugador(colorJugador);
        jugador.activarCartas();
        this.tablero.agregarFichasA(jugador);
    }

    public void agregarFichasA(String colorJugador, int cantidadFichas) {
        this.getJugador(colorJugador).agregarFichas(cantidadFichas);
    }

    public boolean hacerCanjeJugador(String colorJugador) {
        return this.getJugador(colorJugador).hacerCanje(this.cartas);
    }

    public boolean darCartaPaisA(String colorJugador) {
        return this.cartas.darCartaA(this.getJugador(colorJugador));
    }

    public int cantidadDePaisesJugador(String colorJugador) {
        return this.tablero.cantidadDePaisesJugador(this.getJugador(colorJugador));
    }

    public int cantidadDePaisesJugadorEnContinente(String continente, String jugador) {
        return this.tablero.cantidadDePaisesJugadorEnContinente(continente, this.getJugador(jugador));
    }

    public boolean continenteEsDeJugador(String continente, String jugador) {
        return this.tablero.continenteEsDeJugador(continente, this.getJugador(jugador));
    }

    public boolean hayGanador() {
        return this.jugadores.values().stream().anyMatch(j -> j.gano(this));
    }

    public boolean jugadorTieneFichas(String colorJugador) {
        return this.getJugador(colorJugador).tieneFichas();
    }

    public String getGanador() {
        return this.jugadores.entrySet().stream()
                .filter(e -> e.getValue().gano(this))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }


    public int getFichas(String jugadorActual) {
        return this.getJugador(jugadorActual).getFichas();
    }


    public List<String> getPaisesPorContinentes(String jugador) {
        return this.tablero.getPaisesPorContinentes(this.getJugador(jugador));
    }

    public List<String> getCartasJugador(String jugadorActual) {
        return this.getJugador(jugadorActual).getCartas();
    }

    public List<Pais> getPaisesJugador(String jugadorActual) {
       return tablero.getPaisesJugador(this.getJugador(jugadorActual));
    }

    public List<Pais> getPaisesEnemigos(String jugadorActual) {
        return tablero.getPaisesEnemigos(this.getJugador(jugadorActual));
    }

    public String textoObjetivo(String jugadorActual) {
        return this.getJugador(jugadorActual).textoObjetivo();
    }
}
