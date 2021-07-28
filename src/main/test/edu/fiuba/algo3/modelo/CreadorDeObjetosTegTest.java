package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.cartas.CartaPais;
import edu.fiuba.algo3.modelo.objetivos.ObjetivoTeg;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreadorDeObjetosTegTest {
    @Test
    public void TableroSeCreaConCincuentaPaises(){
        Tablero tablero = CreadorDeObjetosTeg.lectorTablero("resources/Teg-Tablero.json");
        assertEquals(50,tablero.getPaises().size());
    }

    @Test
    public void TableroSeCreaConSeisContientes(){
        Tablero tablero = CreadorDeObjetosTeg.lectorTablero("resources/Teg-Tablero.json");
        assertEquals(6,tablero.getContinentes().size());
    }

    @Test
    public void SeCreanCincuentaCartasPais(){
        Tablero tablero = CreadorDeObjetosTeg.lectorTablero("resources/Teg-Tablero.json");
        List<CartaPais> cartasPais = CreadorDeObjetosTeg.lectorCartasPais("resources/Teg-Cartas.json",tablero);
        assertEquals(50,cartasPais.size());
    }

    @Test
    public void LasCartasPaisesTienenLaReferenciaCorrectaAPais(){
        Tablero tablero = CreadorDeObjetosTeg.lectorTablero("resources/Teg-Tablero.json");
        List<CartaPais> cartasPais = CreadorDeObjetosTeg.lectorCartasPais("resources/Teg-Cartas.json",tablero);
        for(CartaPais carta: cartasPais){
            assertEquals(tablero.getPais(carta.getNombrePais()),carta.getPais());
        }
    }

    @Test
    public void creacionDeObjetivosConquista(){
        List<ObjetivoTeg> objetivos = CreadorDeObjetosTeg.lectorObjetivosConquista("resources/Teg-Objetivos.json");

        assertEquals(9,objetivos.size());
    }
}