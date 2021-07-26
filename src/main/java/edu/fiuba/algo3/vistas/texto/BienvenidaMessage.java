package edu.fiuba.algo3.vistas.texto;

import edu.fiuba.algo3.vistas.colores.FontSelection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BienvenidaMessage extends Label {

    public BienvenidaMessage(String color){
        super("ALTEGO - Prueba Mensaje");
        super.setFont(Font.font(FontSelection.TitleFontType,30));
        super.setTextFill(Color.web(color));
        super.setWrapText(true);
        //super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, FontSelection.CURVATURA_BORDE, FontSelection.GROSOR_BORDE)));
        Background unFondo = new Background(new BackgroundFill(Color.web(FontSelection.Negro, FontSelection.ALPHA_BOTON_INACTIVO), new CornerRadii(0), new Insets(0)));
        super.setBackground(unFondo);
        super.setAlignment(Pos.CENTER);
    }

}
