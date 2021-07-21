package edu.fiuba.algo3.modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

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
}
