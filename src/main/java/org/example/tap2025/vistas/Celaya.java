package org.example.tap2025.vistas;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.tap2025.componentes.Hilo;

public class Celaya extends Stage {

    private VBox vBox;
    private GridPane gdpCalles;
    private Button btnIniciar;
    private Label[] lblRutas;
    private ProgressBar[] pgbRutas;
    private Scene escena;
    private String[] strRutas = {"ruta 1", "ruta 2", "ruta 3", "ruta 4", "ruta 5"};
    private Hilo[] thrRutas;

    public Celaya() {
        vBox = new VBox();
        escena = new Scene(vBox, 400, 300);
        this.setTitle("Calles de Celaya");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        btnIniciar = new Button("Iniciar");
        pgbRutas = new ProgressBar[5];
        lblRutas = new Label[5];
        gdpCalles = new GridPane();
        thrRutas = new Hilo[5];
        for (int i = 0; i < pgbRutas.length; i++) {
            lblRutas[i] = new Label(strRutas[i]);
            pgbRutas[i] = new ProgressBar(0);
            thrRutas[i] = new Hilo(strRutas[i]);

            gdpCalles.add(lblRutas[i], 0, i);
            gdpCalles.add(pgbRutas[i], 1, i);

        }
        vBox = new VBox(gdpCalles);
        escena = new Scene(vBox, 300, 250);
    }
}