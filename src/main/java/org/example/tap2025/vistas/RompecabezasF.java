package org.example.tap2025.vistas;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.*;


public class RompecabezasF extends Stage {
    private Scene escena;
    private GridPane grid;
    private List<ImageView> piezas;
    private int tamaño;
    private long tiempoInicio;
    private Label lblTiempo;
    private Timer timer;
    private ImageView piezaSeleccionada = null;
    private boolean juegoFinalizado = false;

    public RompecabezasF() {
        this.setTitle("Rompecabezas");
        mostrarPantallaSeleccion();
    }

    private void mostrarPantallaSeleccion() {
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Selecciona el tamaño del rompecabezas:");
        Button btn3x3 = new Button("Chico");
        Button btn4x4 = new Button("Mediano");
        Button btn5x5 = new Button("Grande");

        btn3x3.setOnAction(e -> iniciarJuego(3));
        btn4x4.setOnAction(e -> iniciarJuego(4));
        btn5x5.setOnAction(e -> iniciarJuego(5));

        vbox.getChildren().addAll(lblTitulo, btn3x3, btn4x4, btn5x5);
        escena = new Scene(vbox, 300, 250);
        this.setScene(escena);
        this.show();
    }

    private void iniciarJuego(int tam) {
        this.tamaño = tam;
        this.juegoFinalizado = false;

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(2);
        grid.setVgap(2);

        lblTiempo = new Label("Tiempo: 0s");
        Button btnTerminar = new Button("Fin");
        btnTerminar.setOnAction(e -> finalizarJuego());

        cargarPiezas();

        VBox contenedor = new VBox(10, lblTiempo, grid, btnTerminar);
        contenedor.setAlignment(Pos.CENTER);

        escena = new Scene(contenedor, 600, 650);
        this.setScene(escena);
        this.show();

        iniciarTemporizador();
    }

    private void cargarPiezas() {
        piezas = new ArrayList<>();
        Image imagen = new Image("file:/C:/Users/paues/Downloads/descarga.jpg");
        double width = imagen.getWidth() / tamaño;
        double height = imagen.getHeight() / tamaño;

        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                ImageView pieza = new ImageView(imagen);
                pieza.setViewport(new javafx.geometry.Rectangle2D(j * width, i * height, width, height));
                pieza.setFitWidth(width);
                pieza.setFitHeight(height);

                pieza.setOnMouseClicked(event -> seleccionarPieza(pieza));
                piezas.add(pieza);
            }
        }

        Collections.shuffle(piezas);
        actualizarGrid();
    }

    private void actualizarGrid() {
        grid.getChildren().clear();
        int index = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                grid.add(piezas.get(index++), j, i);
            }
        }
    }

    private void seleccionarPieza(ImageView pieza) {
        if (piezaSeleccionada == null) {
            piezaSeleccionada = pieza;
        } else {
            intercambiarPiezas(piezaSeleccionada, pieza);
            piezaSeleccionada = null;
        }
    }

    private void intercambiarPiezas(ImageView p1, ImageView p2) {
        int index1 = piezas.indexOf(p1);
        int index2 = piezas.indexOf(p2);
        Collections.swap(piezas, index1, index2);
        actualizarGrid();
    }

    private void iniciarTemporizador() {
        tiempoInicio = System.currentTimeMillis();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (!juegoFinalizado) {
                        long tiempoTranscurrido = (System.currentTimeMillis() - tiempoInicio) / 1000;
                        lblTiempo.setText("Tiempo: " + tiempoTranscurrido + "s");
                    }
                });
            }
        }, 0, 1000);
    }

    private void detenerTemporizador() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private void finalizarJuego() {
        if (juegoFinalizado) return;
        juegoFinalizado = true;

        detenerTemporizador();
        long tiempoTotal = (System.currentTimeMillis() - tiempoInicio) / 1000;

        boolean esCorrecto = verificarCompletado();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Juego terminado");
        alert.setHeaderText(null);
        alert.setContentText("Tiempo total: " + tiempoTotal + " segundos.\n" + (esCorrecto ? "¡Rompecabezas completado correctamente!" : "Aún no está completo."));
        alert.showAndWait();

        this.close();
    }

    private boolean verificarCompletado() {
        for (int i = 0; i < piezas.size(); i++) {
            if (grid.getChildren().get(i) != piezas.get(i)) {
                return false;
            }
        }
        return true;
    }
}
