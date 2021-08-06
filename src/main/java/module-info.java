module edu.fiuba.algo3 {
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;
    requires javafx.media;

    exports edu.fiuba.algo3;
    opens edu.fiuba.algo3.modelo;
    opens edu.fiuba.algo3.modelo.objetivos;
    opens edu.fiuba.algo3.modelo.cartas;
    opens edu.fiuba.algo3.modelo.rondas;
    opens edu.fiuba.algo3.modelo.tablero;
    opens edu.fiuba.algo3.modelo.ataque;
    opens edu.fiuba.algo3.controller to javafx.fxml;
    opens edu.fiuba.algo3.modelo.excepciones;
}