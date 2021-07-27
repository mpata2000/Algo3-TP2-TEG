package edu.fiuba.algo3.modelo.cartas;


import edu.fiuba.algo3.modelo.Jugador;

import java.util.*;

public class ColeccionDeCartasPais {
    private List<CartaPais> cartasPais;
    private Canje numeroDecanje = new PrimerosCanjes();

    public ColeccionDeCartasPais(List<CartaPais> cartaPais) {
        this.cartasPais = cartaPais;
    }

    public ColeccionDeCartasPais() {
        this.cartasPais = new ArrayList<>();
    }

    public int cantidadDeCartas() {
       return cartasPais.size();
    }

    public void agregarCartaPais(CartaPais cartaPais) {
        this.cartasPais.add(cartaPais);
    }

    public void asignarPaises(List<Jugador> jugadores) {
        Collections.shuffle(this.cartasPais);
        ListIterator<CartaPais> cartas = this.cartasPais.listIterator();
        int i = 0;
        while (cartas.hasNext()) {
            cartas.next().asignarPaisA(jugadores.get(i % jugadores.size()));
            i++;
        }
    }

    public void agregarCartasPais(List<CartaPais> cartas) {
        this.cartasPais.addAll(cartas);
    }

    private void pasarCartasCanje(List<CartaPais> cartas, ColeccionDeCartasPais cartasPais){
        this.cartasPais.removeAll(cartas);
        cartasPais.agregarCartasPais(cartas);
    }


    private List<CartaPais> listaDecartasIguales(String simbolo){
        Iterator<CartaPais> iterator = this.cartasPais.listIterator();
        List<CartaPais> cartas = new ArrayList<>();

        while (iterator.hasNext() && cartas.size() < 3) {
            CartaPais carta = iterator.next();
            if (carta.esSimbolo(simbolo)) {
                cartas.add(carta);
            }
        }
        return cartas;
    }

    private boolean canjearTresCartasIgules(List<String> simbolos,ColeccionDeCartasPais cartasPais){
        boolean pasoCanje = false;

        for(String simbolo: simbolos) {
            List<CartaPais> cartas = this.listaDecartasIguales(simbolo);
            if (cartas.size() > 2) {
                pasoCanje = true;
                pasarCartasCanje(cartas,cartasPais);
                break;
            }
        }
        return pasoCanje;
    }

    private boolean canjearTresCartasDistintas(List<String> simbolos,ColeccionDeCartasPais cartasPais){
        List<CartaPais> cartas = new ArrayList<>();
        CartaPais carta;
        for(String simbolo: simbolos) {
            carta = this.cartasPais.stream().filter(cartaPais -> cartaPais.esSimbolo(simbolo))
                    .findFirst().orElse(null);

            if(carta != null){
                cartas.add(carta);
                this.cartasPais.remove(carta);
            }
        }

        this.cartasPais.addAll(cartas);

        if(cartas.size() > 2){
            pasarCartasCanje(cartas,cartasPais);
            return true;
        }
        return false;
    }


    public boolean canjeDeCartas(Jugador unJugador,ColeccionDeCartasPais cartasPais){
        if(this.cartasPais.size() < 3){
            return false;
        }
        List<String> simbolos = List.of("Globo","Barco","Cañon");

        if (canjearTresCartasIgules(simbolos,cartasPais) || canjearTresCartasDistintas(simbolos,cartasPais)){
            this.numeroDecanje = this.numeroDecanje.hacerCanje(unJugador);
            return true;
        }
        return false;
    }

    public void darCartaA(Jugador jugador) {
        if(cartasPais.isEmpty()){
            return;
        }

        if(jugador.darCartaPais(cartasPais.get(0))) {
            cartasPais.remove(0);
        }
    }
}
