package edu.fiuba.algo3.modelo;


import java.util.*;

public class ColeccionDeCartasPais {
    private List<CartaPais> cartasPais;

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
    
    private void pasarCartasIgualesCanje(String simbolo, ColeccionDeCartasPais cartasPais){
        int i = 0;
        int cantCartas = 3;
        while (i < this.cartasPais.size() && cantCartas > 0) {
            CartaPais carta = this.cartasPais.get(i);
            if (carta.esSimbolo(simbolo)) {
                this.cartasPais.remove(i);
                cartasPais.agregarCartaPais(carta);
                i--;
                cantCartas--;
            }
            i++;
        }
    }

    private boolean sePuedehacerCanjeDeTresCartasIguales(String simbolo){
        Iterator<CartaPais> iterator = this.cartasPais.listIterator();
        int contador = 0;
        while (iterator.hasNext() && contador < 3) {
            if (iterator.next().esSimbolo(simbolo)) {
                contador++;
            }
        }
        return(contador == 3);
    }

    private boolean canjearTresCartasIgules(List<String> simbolos,ColeccionDeCartasPais cartasPais){
        boolean pasoCanje = false;

        for(String simbolo: simbolos) {
            if (this.sePuedehacerCanjeDeTresCartasIguales(simbolo)) {
                pasoCanje = true;
                pasarCartasIgualesCanje(simbolo,cartasPais);
                break;
            }
        }
        return pasoCanje;
    }


    public boolean canjeDeCartas(Jugador unJugador,ColeccionDeCartasPais cartasPais){
        return canjearTresCartasIgules(List.of("Globo","Barco","Cañon"),cartasPais);
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
