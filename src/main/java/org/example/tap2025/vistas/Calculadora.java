package org.example.tap2025.vistas;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Stack;

public class Calculadora extends Stage {
    private Scene escena;
    private TextField txtDisplay;
    private VBox vBox;
    private GridPane gdpTeclado;
    private Button[][] arBtnTeclado;
    private String[] strTeclas = {"7", "8", "9", "*", "4", "5", "6", "/", "1", "2", "3", "+", "=", "0", ".", "-"};

    public Calculadora() {
        CrearUi();
        this.setScene(escena);
        this.setTitle("Calculadora");
        this.show();
    }

    private void CrearUi() {
        CrearKeyboard();
        txtDisplay = new TextField("0");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        vBox = new VBox(10, txtDisplay, gdpTeclado);
        escena = new Scene(vBox, 200, 250);
        escena.getStylesheets().add(getClass().getResource("/styles/calcu.css").toExternalForm());
    }

    private void CrearKeyboard() {
        arBtnTeclado = new Button[4][4];
        gdpTeclado = new GridPane();
        gdpTeclado.setHgap(5);
        gdpTeclado.setVgap(5);

        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arBtnTeclado[i][j] = new Button(strTeclas[pos]);
                String tecla = strTeclas[pos];
                arBtnTeclado[i][j].setOnAction(event -> EventoTeclado(tecla));
                arBtnTeclado[i][j].setPrefSize(45, 45);
                gdpTeclado.add(arBtnTeclado[i][j], j, i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String strTecla) {
        if (strTecla.equals("=")) {
            try {
                String resultado = evaluarExpresion(txtDisplay.getText());
                txtDisplay.setText(resultado);
            } catch (Exception e) {
                txtDisplay.setText("Error");
            }
            return;
        }
        if (txtDisplay.getText().equals("0")) {
            txtDisplay.setText(strTecla);
        } else {
            txtDisplay.appendText(strTecla);
        }
    }

    private String evaluarExpresion(String expresion) {
        try {
            Stack<Double> numeros = new Stack<>();
            Stack<Character> operadores = new Stack<>();

            for (int i = 0; i < expresion.length(); i++) {
                char c = expresion.charAt(i);

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder sb = new StringBuilder();
                    while (i < expresion.length() && (Character.isDigit(expresion.charAt(i)) || expresion.charAt(i) == '.')) {
                        sb.append(expresion.charAt(i++));
                    }
                    i--;
                    numeros.push(Double.parseDouble(sb.toString()));
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    while (!operadores.isEmpty() && tieneOtro(c, operadores.peek())) {
                        numeros.push(aplicarOperacion(operadores.pop(), numeros.pop(), numeros.pop()));
                    }
                    operadores.push(c);
                }
            }

            while (!operadores.isEmpty()) {
                numeros.push(aplicarOperacion(operadores.pop(), numeros.pop(), numeros.pop()));
            }

            return String.valueOf(numeros.pop());
        } catch (Exception e) {
            return "Error";
        }
    }

    private boolean tieneOtro(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        } else {
            return true;
        }
    }

    private double aplicarOperacion(char operador, double b, double a) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("División por cero");
                return a / b;
            default:
                return 0;
        }
    }
}
