package edu.fiuba.algo3.modelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContinenteTest {
    ArrayList<Pais> paises= new ArrayList<>();
    Jugador jugadorUno;
    Jugador jugadorDos;
    Continente continente;

    @BeforeEach
    void setUp() {
        paises.add(new Pais("Borneo", List.of("Australia","Malasia")));
        paises.add(new Pais("Sumatra", List.of("Australia","India")));
        paises.add(new Pais("Australia", List.of("Chile","Sumatra","Java","Borneo")));
        paises.add(new Pais("Java", List.of("Australia")));
        continente = new Continente("Oceania",2,paises);
        jugadorUno = new Jugador("Julian");
        jugadorDos = new Jugador("Sofia");
    }

    @Test
    public void contineteSeCreaConElNombreCorrecto(){
        assertEquals("Oceania",continente.getNombre());
    }

    @Test
    public void contineteSeCreaConLaCantidadDeFichasExtraCorrecta(){

        assertEquals(2,continente.getFichas());
    }

    @Test
    public void contineteSeCreaConLosPaisesCorrectos(){

        assertEquals(paises,continente.getPaises());
    }
}
