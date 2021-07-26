package edu.fiuba.algo3.vistas.texto;

import edu.fiuba.algo3.vistas.colores.FontSelection;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SeleccionarJugadorMessage extends Label {

    public SeleccionarJugadorMessage(String color){
        super("Seleccione la cantidad de jugadores");
        super.setFont(Font.font(FontSelection.TitleFontType,50));
        super.setTextFill(Color.web(color));
        super.setWrapText(true);
    }
}
