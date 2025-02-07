package org.example.tap2025;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tap2025.vistas.Calculadora;

import java.io.IOException;

public class HelloApplication extends Application {
    private VBox vBox;
    private MenuBar mnbPrincipal;
    private Menu menCompetencia1;
    private MenuItem mitCalculadora;
    private Scene escena;

    void CrearUi() {
        mitCalculadora = new MenuItem("Calculadora");
        mitCalculadora.setOnAction(actionEvent -> new Calculadora());
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia1.getItems().addAll(mitCalculadora);

        mnbPrincipal = new MenuBar();
        mnbPrincipal.getMenus().addAll(menCompetencia1);
        vBox = new VBox(mnbPrincipal);
    }

    @Override
    public void start(Stage stage) throws IOException {
        CrearUi();
        stage.setTitle("Mundo de eventos");
        stage.setScene(new Scene(vBox, 200, 200));
        stage.show();
        stage.setMaximized(true);


    }

    public static void main(String[] args) {
        launch();
    }

    void clicEvent() {
        System.out.println("Evento con método");
    }
}
