package org.example.tap2025.vistas;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.scene.layout.Panel;
import org.kordamp.bootstrapfx.BootstrapFX;

public class VentasRestaurante extends Stage {

    private Pane pnIRestaurante;
    private Scene escena;

    public VentasRestaurante() {
        CreamUI();
        this.setTitle("Comidita");
        this.setScene(escena);
        this.show();
    }

    void CreamUI() {
        pnIRestaurante = new Panel("Tacos el Inge.");
        pnIRestaurante.getStyleClass().add("panel-primary");
        escena = new Scene(pnIRestaurante, 300, 200);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
    }
}