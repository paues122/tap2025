package org.example.tap2025.vistas;


import org.example.tap2025.modelos.ClientesDao;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Cliente extends Stage {

    private Button btnGuardar;
    private TextField txtNomCte, txtDireccion, txtTelCte, txtEmail;
    private VBox vBox;
    private Scene escena;
    private ClientesDao objC;
    private TableView<ClientesDao> tbvClientes;

    public Cliente(TableView<ClientesDao> tbvCte){
        this.tbvClientes = tbvCte;
        objC = new ClientesDao();
        CrearUI();
        this.setTitle("Registrar Cliente");
        this.setScene(escena);
        this.show();
    }
    private void CrearUI(){
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelCte = new TextField();
        txtEmail = new TextField();
        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> {
            objC.setNomCte(txtNomCte.getText());
            objC.setDireccion(txtDireccion.getText());
            objC.setTelCte(txtTelCte.getText());
            objC.setEmailCte(txtEmail.getText());
            objC.INSERT();
            tbvClientes.setItems(objC.SELECT());
            tbvClientes.refresh();
            this.close();
        });
        vBox = new VBox(txtNomCte,txtDireccion,txtTelCte,txtEmail,btnGuardar);
        escena = new Scene(vBox,120,150);
    }


}