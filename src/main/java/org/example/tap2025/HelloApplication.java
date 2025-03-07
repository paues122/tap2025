package org.example.tap2025;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.vistas.Calculadora;
import org.example.tap2025.vistas.VentasRestaurante;
import org.example.tap2025.vistas.RompecabezasF;

import java.io.IOException;
// import org.example.tap2025.modelos.conexion; // Conexión a la base de datos (comentado temporalmente)
// import java.sql.Connection; // Importación de conexión (comentado temporalmente)

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1;
    private MenuItem mitCalculadora, mitRestaurante, mitRompecabezas;
    private Scene escena;

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitRestaurante = new MenuItem("Restaurante");
        mitRompecabezas = new MenuItem("Rompecabezas");

        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante.setOnAction(event -> new VentasRestaurante());
        mitRompecabezas.setOnAction(event -> new RompecabezasF());

        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestaurante, mitRompecabezas);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().add(menCompentencia1);

        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox, 600, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {
        // conexion.createConnection(); // Conexión a la base de datos (comentado temporalmente)
        CrearUI();
        stage.setTitle("Menu Principal");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
}
