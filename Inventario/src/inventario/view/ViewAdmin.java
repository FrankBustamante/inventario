package inventario.view;

import inventario.Inventario;
import inventario.logic.Products;
import inventario.logic.Reportes;
import inventario.model.Product;
import inventario.utilities.CodeRandom;
import inventario.utilities.PVent;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */
public class ViewAdmin implements Initializable{

    @FXML
    private Button btNewProduct, btAddProduct, btViewProducts, btReporte, btLogout, btSave;

    @FXML
    private TextField txtName, txtType, txtLocation, txtPrice, txtQuantity, txtCategory, txtCode;

    @FXML
    private Pane paneHome, paneProducts, paneReport;
    
    @FXML
    private ChoiceBox day, month, year;
   
    
    @FXML
    private final TableView<Product> TABLE = new TableView<>();
    private final ObservableList<Product> data = FXCollections.observableArrayList(Products.getProducts());
    
    @FXML
    private TableColumn<Product, String> colCode, colType, colPrice, colName, colQuantity, colActions;

    public ViewAdmin() {
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void eventReport(ActionEvent event){
        
        String date = day.getSelectionModel().getSelectedItem() + "-" +
                month.getSelectionModel().getSelectedItem() + "-" + year.getSelectionModel().getSelectedItem();
        Reportes.newReporte(date);   
    }
 /*   
    private void launch() {
        //INICIAR ELEMENTOS
        init();
        config();
        events();

        //TABLEVIEW
        TABLE.setItems(data);
        TABLE.getColumns().addAll(colCode, colName, colType, colPrice, colQuantity, colActions);

        //AGREGAR ELEMENTOS
        this.paneHome.getChildren().addAll(
                new HBox(new VBox(txtCode, txtLocation, txtName, txtPrice),
                        new VBox(txtQuantity, txtType, txtCategory),
                        btAddProduct
                ));

        this.paneProducts.getChildren().addAll(TABLE, btSave);
    }

    private void init() {
        //BUTTON
        btSave = new Button("actualizar");
        btAddProduct = new Button("Guardar");
        btNewProduct = new Button("Nuevo");
        btViewProducts = new Button("Productos");
        btReporte = new Button("Reportes");
        btLogout = new Button("Salir");

        //TEXTFIELD
        txtName = new TextField();
        txtLocation = new TextField();
        txtType = new TextField();
        txtPrice = new TextField();
        txtQuantity = new TextField();
        txtCategory = new TextField();
        txtCode = new TextField();

        //TABELCOLUNMS
        colCode = new TableColumn("Codigo");
        colName = new TableColumn("Nombre");
        colType = new TableColumn("Tipo");
        colQuantity = new TableColumn("Cantidad");
        colActions = new TableColumn("Sup");
        colPrice = new TableColumn("Precio");

        //PANE
        paneHome = new Pane();
        paneProducts = new Pane();

    }

    private void config() {
        //TEXTFIELD
        txtLocation.setPromptText("Ubicacion");
        txtName.setPromptText("Nombre");
        txtPrice.setPromptText("Precio");
        txtQuantity.setPromptText("Cantidad");
        txtType.setPromptText("Tipo");
        txtCategory.setPromptText("Categoria");

        //PANE
        paneProducts.setVisible(false);
        paneHome.setVisible(true);

        //TABLE
        TABLE.setEditable(true);

        //TABLECOLUMNS
        colType.setCellValueFactory(
                new PropertyValueFactory<>("type"));
        colCode.setCellValueFactory(
                new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        colQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
        colQuantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        colActions.setCellValueFactory(
                new PropertyValueFactory<>("Eliminar"));
        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));

    }

    private void events() {
        CodeRandom c = new CodeRandom();
        //BUTTON
        btSave.setOnAction((e) ->{
            if (Products.updateProduct(data)) {
                Inventario.alerta("Informacion", "Actualizacion de Productos", "Archivo actualizado con exito");
            }
          
        });
        
        btLogout.setOnAction((e) -> {
           // new Inventario().getScene().setRoot(new Login());
        });

        btReporte.setOnAction((e) -> {
            
        });

        btAddProduct.setOnAction((e) -> {
            if (c.getCode(txtCode.getText())) {

                Products.saveProduct(new Product(txtCode.getText(), txtName.getText(), txtCategory.getText(),
                        txtLocation.getText(), Integer.parseInt(txtQuantity.getText()),
                        Double.parseDouble(txtPrice.getText()), txtType.getText()));

                clean();
            } else {

            }
        });

        btNewProduct.setOnAction((e) -> {
            paneHome.setVisible(true);
            paneProducts.setVisible(false);
        });

        btViewProducts.setOnAction((e) -> {
            paneHome.setVisible(false);
            paneProducts.setVisible(true);
            data.clear();
            data.addAll(Products.getProducts());
        });

        //TEXTFIELD
        txtQuantity.setOnKeyTyped((KeyEvent t) -> {
            char ch = t.getCharacter().charAt(0);

            if (Character.isLetter(ch)) {
                t.consume();
            }
        });

        txtPrice.setOnKeyTyped((KeyEvent t) -> {
            char ch = t.getCharacter().charAt(0);

            if (Character.isLetter(ch)) {
                t.consume();
            }
        });

        //COLUMNS
        colQuantity.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Product, String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setQuantity(Double.parseDouble(t.getNewValue()));
                System.out.println("nuevo Valor: " + t.getNewValue());
                System.out.println(data.get(t.getTablePosition().getRow()).getName());
            }
        }
        );

    }
*/
    private void clean() {
        txtName.setText("");
        txtCategory.setText("");
        txtLocation.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtType.setText("");
        txtCode.setText("");
    }

 



}
