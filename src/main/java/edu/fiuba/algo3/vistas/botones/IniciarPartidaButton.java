package edu.fiuba.algo3.vistas.botones;

import edu.fiuba.algo3.vistas.colores.FontSelection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class IniciarPartidaButton extends Button {

    public IniciarPartidaButton(EventHandler<ActionEvent> controlador){
        super.setText("Comenzar Partida");

        super.setFont(Font.font(FontSelection.SubtitleFontType, 30));
        super.setPadding(new Insets(10));
        super.setTextFill(Color.BLACK);
        super.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, FontSelection.CURVATURA_BORDE, FontSelection.GROSOR_BORDE)));
        Background unFondo = new Background(new BackgroundFill(Color.web(FontSelection.Gris, FontSelection.ALPHA_BOTON_INACTIVO), FontSelection.CURVATURA_BORDE, new Insets(0)));
        super.setBackground(unFondo);
        super.setAlignment(Pos.CENTER);

        super.setOnAction(controlador);
    }
}
