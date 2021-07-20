package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.lectorJson.LectorDeJson;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LectorDeJsonTest {
    @Test
    public void TableroSeCreaConCincuentaPaises(){
        LectorDeJson lector = new LectorDeJson();
        Tablero tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        assertEquals(50,tablero.getPaises().size());
    }

    @Test
    public void TableroSeCreaConSeisContientes(){
        LectorDeJson lector = new LectorDeJson();
        Tablero tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        assertEquals(6,tablero.getContinentes().size());
    }

    @Test
    public void SeCreanCincuentaCartasPais(){
        LectorDeJson lector = new LectorDeJson();
        lector.lectorTablero("resources/Teg-Tablero.json");
        List<CartaPais> cartasPais = lector.lectorCartasPais("resources/Teg-Cartas.json");
        assertEquals(50,cartasPais.size());
    }

    @Test
    public void LasCartasPaisesTienenLaReferenciaCorrectaAPais(){
        LectorDeJson lector = new LectorDeJson();
        Tablero tablero = lector.lectorTablero("resources/Teg-Tablero.json");
        List<CartaPais> cartasPais = lector.lectorCartasPais("resources/Teg-Cartas.json");
        for(CartaPais carta: cartasPais){
            assertEquals(tablero.getPais(carta.getNombrePais()),carta.getPais());
        }
    }
}