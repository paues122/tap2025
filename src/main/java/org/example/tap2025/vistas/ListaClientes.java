package org.example.tap2025.vistas;


import javafx.scene.control.*;
import javafx.util.Callback;
import org.example.tap2025.componentes.ButtonCell;
import org.example.tap2025.modelos.ClientesDao;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaClientes extends Stage {

    private ToolBar tlbMenu;
    private TableView<ClientesDao> tbvClientes;
    private VBox vBox;
    private Scene escena;
    private Button btnAgregar;
    public ListaClientes(){
        CrearUI();
        this.setTitle("Listado de Clientes :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvClientes = new TableView<>();
        btnAgregar = new Button();
        btnAgregar.setOnAction(event -> new Cliente(tbvClientes, null));
        ImageView imv = new ImageView(getClass().getResource("/images/person_add_icon.png").toString());
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        btnAgregar.setGraphic(imv);
        tlbMenu = new ToolBar(btnAgregar);
        CreateTable();
        vBox = new VBox(tlbMenu,tbvClientes);
        escena = new Scene(vBox, 800, 600);
    }

    private void CreateTable() {
        ClientesDao objC = new ClientesDao();
        TableColumn<ClientesDao,String> tbcNomCte = new TableColumn<>("Nombre");
        tbcNomCte.setCellValueFactory(new PropertyValueFactory<>("nomCte"));
        TableColumn<ClientesDao,String> tbcDireccion = new TableColumn<>("Dirección");
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        TableColumn<ClientesDao,String> tbcTel = new TableColumn<>("Telefono");
        tbcTel.setCellValueFactory(new PropertyValueFactory<>("telCte"));
        TableColumn<ClientesDao,String> tbcEmail = new TableColumn<>("Email");
        tbcEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        TableColumn<ClientesDao,String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClientesDao, String>, TableCell<ClientesDao, String>>() {
            @Override
            public TableCell<ClientesDao, String> call(TableColumn<ClientesDao, String> param) {
                return new ButtonCell("Editar");
            }
        });
        TableColumn<ClientesDao,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setCellFactory(new Callback<TableColumn<ClientesDao, String>, TableCell<ClientesDao, String>>() {
            @Override
            public TableCell<ClientesDao, String> call(TableColumn<ClientesDao, String> param) {
                return new ButtonCell("Eliminar");
            }
        });

        tbvClientes.getColumns().addAll(tbcNomCte,tbcDireccion,tbcTel,tbcEmail,tbcEditar,tbcEliminar);
        tbvClientes.setItems(objC.SELECT());
    }
}