package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaPaisTest {
    ArrayList<Pais> paises = new ArrayList<>();
    Tablero tablero;

    @BeforeEach
    void setUp() {
        paises.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paises.add(new Pais("Sumatra", List.of("Australia","India")));
        paises.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paises.add(new Pais("Java", List.of("Australia")));
        tablero = new Tablero(new ArrayList<>(),paises);
    }

    @Test
    public void cartaPaisSeCreaConElNombreCorrecto(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        assertEquals("Borneo",carta.getNombrePais());
    }

    @Test
    public void cartaPaisSeCreaConElSimboloCorrecto(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        assertEquals("Barco",carta.getSimbolo());
    }

    @Test
    public void cartaPaisSeAsignaBienElPais(){
        CartaPais carta = new CartaPais("Borneo","Barco");
        carta.setPais(tablero);
        assertEquals(tablero.getPais("Borneo"),carta.getPais());
    }



}
