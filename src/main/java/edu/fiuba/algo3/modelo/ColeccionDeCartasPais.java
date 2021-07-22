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

    private boolean canjearTresCartasIgules(String simbolo){
        Iterator<CartaPais> iterator = this.cartasPais.listIterator();
        int contador = 0;
        boolean pasoCanje = false;

        while (iterator.hasNext() && contador < 3) {
            if(iterator.next().esSimbolo(simbolo)){
                contador++;
            }
        }

        if(contador == 3){
            pasoCanje = true;
            int i =0;
            while (i < this.cartasPais.size() && contador > 0) {
                if(this.cartasPais.get(i).esSimbolo(simbolo)){
                    this.cartasPais.remove(i);
                    i--;
                    contador--;
                }
                i++;
            }
        }
        return pasoCanje;
    }


    public boolean canjeDeCartas(){
        return canjearTresCartasIgules("Globo") || canjearTresCartasIgules("Barco") || canjearTresCartasIgules("Cañon");
    }
}
