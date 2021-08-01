package edu.fiuba.algo3.vistas.grillas;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class MenuInicioGrid extends GridPane {
    private static int GAP_FILAS = 15;

    public MenuInicioGrid(double screenWidth, double screenHeight) {
        //super.setGridLinesVisible(true);
        super.setMinSize(screenWidth + 50, screenHeight);
        super.setMaxSize(screenWidth + 50, screenHeight);
        super.setAlignment(Pos.CENTER);
        super.setVgap(GAP_FILAS);
    }
}
