package edu.fiuba.algo3.vistas.texto;

import edu.fiuba.algo3.vistas.colores.FontSelection;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ReglasMessage extends Label {

    public ReglasMessage(){
        super("Reglas del Juego");

        super.setFont(Font.font(FontSelection.TitleFontType,30));
        super.setTextFill(Color.BLACK);
        super.setWrapText(true);
        super.setUnderline(true);

        super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), FontSelection.GROSOR_BORDE)));
        Background unFondo = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        super.setBackground(unFondo);
        super.setAlignment(Pos.CENTER);
    }
}
