package org.example.tap2025;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.componentes.Hilo;
import org.example.tap2025.vistas.Calculadora;
import org.example.tap2025.vistas.Celaya;
import org.example.tap2025.vistas.VentasRestaurante;
import org.example.tap2025.vistas.RompecabezasF;
import java.io.IOException;
import org.example.tap2025.modelos.conexion;
import java.sql.Connection;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompentencia1, menCompentencia2;
    private MenuItem mitCalculadora, mitRestaurante, mitRompecabezas, mithilos;
    private Scene escena;

    void CrearUI(){
        mitCalculadora = new MenuItem("Calculadora");
        mitRestaurante = new MenuItem("Restaurante");
        mitRompecabezas = new MenuItem("Rompecabezas");
        mithilos = new MenuItem("Celaya");

        mitCalculadora.setOnAction(event -> new Calculadora());
        mitRestaurante.setOnAction(event -> new VentasRestaurante());
        mitRompecabezas.setOnAction(event -> new RompecabezasF());
        mithilos.setOnAction(event -> new Celaya());

        menCompentencia1 = new Menu("Competencia 1");
        menCompentencia1.getItems().addAll(mitCalculadora, mitRestaurante, mitRompecabezas);
        menCompentencia2 = new Menu("Competencia 2");
        menCompentencia2.getItems().add(mithilos);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompentencia1, menCompentencia2);

        vBox = new VBox(mnbPrincipal);
        escena = new Scene(vBox, 600, 400);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());
    }

    @Override
    public void start(Stage stage) throws IOException {

       /* new Hilo("Ruta 1").start();
        new Hilo("Ruta 2").start();
        new Hilo("Ruta 3").start();
        new Hilo("Ruta 4").start();
        new Hilo("Ruta 5 ").start();
        */

        conexion.createConnection();
        CrearUI();
        stage.setTitle("Hola Mundo de Eventos :)");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }
    void clickEvent(){
        System.out.println("Evento desde un metodo :)");
    }
}