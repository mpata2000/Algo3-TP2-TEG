module edu.fiuba.algo3 {
    requires javafx.controls;
    requires com.google.gson;
    exports edu.fiuba.algo3;
    opens edu.fiuba.algo3.modelo;
    opens edu.fiuba.algo3.modelo.objetivos;
    opens edu.fiuba.algo3.modelo.cartas;
    opens edu.fiuba.algo3.modelo.rondas;
    opens edu.fiuba.algo3.modelo.tablero;
    opens edu.fiuba.algo3.modelo.ataque;
}