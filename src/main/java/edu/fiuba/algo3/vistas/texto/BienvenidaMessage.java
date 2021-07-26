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
        super("ALTEGO");
        super.setFont(Font.font(FontSelection.TitleFontType,70));
        super.setTextFill(Color.web(color));
        super.setUnderline(true);
        super.setWrapText(true);
        Background unFondo = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        super.setBackground(unFondo);
        super.setAlignment(Pos.CENTER);
    }

}
