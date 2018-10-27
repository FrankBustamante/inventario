package inventario.view;

import inventario.Inventario;
import inventario.logic.Products;
import inventario.logic.Reportes;
import inventario.model.Product;
import inventario.utilities.CodeRandom;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import inventario.utilities.Utils;
import javax.print.*;
/**
 *
 * @author Frank Bustamante
 */
public class ViewAdmin extends Stage {

    private Button btAddProduct, btReporte, btSave;

    private TextField txtName, txtType, txtLocation, txtPrice, txtQuantity, txtCategory, txtCode;

    private Pane root, paneNew;

    private VBox paneProducts, paneReport, vb;
    private HBox paneCH;

    private ComboBox day, month, year;

    private MenuBar mnBar;
    private Menu mnFile, mnProducts, mnReporte;
    private MenuItem miLogout, miView, miNew, miGenerar;

    private Alert alert;

    private final TableView<Product> TABLE = new TableView<>();
    private final ObservableList<Product> data = FXCollections.observableArrayList(Products.getProducts());

    private TableColumn<Product, String> colCode, colType, colPrice, colName, colQuantity, colActions;

    public ViewAdmin(Stage stage) {
        init();

        stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        stage.setMinHeight(500);
        stage.setMinWidth(900);
        Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.AQUA);

        stage.setScene(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add("/inventario/style/AdminStyle.css");
        stage.setResizable(true);
        stage.setTitle("ventas");
        stage.show();
        scene.setOnMouseMoved((e) -> {
            styles();
        });
        styles();
    }

    private void init() {
        //PANE
        root = new Pane();
        paneNew = new Pane();
        paneProducts = new VBox(10);
        paneReport = new VBox(10);
        paneCH = new HBox(10);

        //BUTTON
        btSave = new Button("Guardar cambios");
        btAddProduct = new Button("Guardar");
        btReporte = new Button("Generar reporte");

        //TEXTFIELD
        txtName = new TextField("");
        txtType = new TextField("");
        txtLocation = new TextField("");
        txtPrice = new TextField("");
        txtQuantity = new TextField("");
        txtCategory = new TextField("");
        txtCode = new TextField("");

        //CHOICEBOX
        day = new ComboBox();
        month = new ComboBox();
        year = new ComboBox();

        //CLOUMN
        colCode = new TableColumn("Código");
        colType = new TableColumn("Tipo");
        colPrice = new TableColumn("Precio");
        colName = new TableColumn("Nombre");
        colQuantity = new TableColumn("Cantidad");
        colActions = new TableColumn("Acción");

        //MENU
        miLogout = new MenuItem("Salir");
        miGenerar = new MenuItem("Generar");
        miView = new MenuItem("Ver o modificar");
        miNew = new MenuItem("Nuevo");
        mnFile = new Menu("Archivo", null, miLogout);
        mnProducts = new Menu("Producto", null, miNew, miView);
        mnReporte = new Menu("Reportes", null, miGenerar);
        mnBar = new MenuBar(mnFile, mnProducts, mnReporte);

        TABLE.setItems(data);
        confg();
        events();
    }

    private void confg() {
        //COMBOBOX
        day.setPromptText("-Seleccione un día-");
        month.setPromptText("-Seleccione un mes-");
        year.setPromptText("-Seleccine un año-");
        choiceItems();

        //VISIBLE
        paneNew.setVisible(false);
        paneReport.setVisible(false);
        paneProducts.setVisible(true);

        //COLUMN
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //PROMPTTEX
        txtCode.setPromptText("Codigo");
        txtCategory.setPromptText("Categoria");
        txtLocation.setPromptText("Ubicación");
        txtName.setPromptText("Nombre");
        txtPrice.setPromptText("Precio");
        txtQuantity.setPromptText("cantidad");
        txtType.setPromptText("Tipo");
        
        //ADD
        root.getChildren().addAll(mnBar, paneProducts, paneNew, paneReport);
        paneProducts.getChildren().addAll(btSave, TABLE);
        paneNew.getChildren().addAll(txtName, txtType, txtLocation, txtPrice, txtQuantity, txtCategory, txtCode, btAddProduct);
        paneCH.getChildren().addAll(day, month, year);
        vb = new VBox(20, paneCH, btReporte);

        paneReport.getChildren().addAll(vb);

        TABLE.getColumns().addAll(colCode, colName, colType, colPrice, colQuantity, colActions);
    }

    private void events() {

        btReporte.setOnAction((e) -> {
            
            if(day.getSelectionModel().getSelectedItem() != null || month.getSelectionModel().getSelectedItem()
                    != null || year.getSelectionModel().getSelectedItem() != null){
                String date = day.getSelectionModel().getSelectedItem() + "-"
                        + month.getSelectionModel().getSelectedItem() + "-" + year.getSelectionModel().getSelectedItem();
                try {
                    if (Reportes.newReporte(date, this)) {
                        alert("Reporte", "Reporte generado", 0);
                    } else {
                        alert("Reporte", "No existen datos para la fecha escogida del reporte", 1);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(ViewAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                alert("Reporte", "Seleccione una fecha valida",1);
            }
        });
        btSave.setOnAction((e) -> {
            if (Products.updateProduct(data)) {
                Inventario.alerta("Información", "Actualización  de Productos", "Archivo actualizado con éxito");
            }
        });

        btAddProduct.setOnAction((e) -> {
            CodeRandom c = new CodeRandom();
            if (!txtCode.getText().equals("") && !txtCategory.getText().equals("") && !txtLocation.getText().equals("")
                    && !txtName.getText().equals("") && !txtPrice.getText().equals("") && !txtQuantity.getText().equals("")
                    && !txtType.getText().equals("")) {
                if (c.getCode(txtCode.getText())) {

                    Products.saveProduct(new Product(txtCode.getText(), txtName.getText(), txtCategory.getText(),
                            txtLocation.getText(), Integer.parseInt(txtQuantity.getText()),
                            Double.parseDouble(txtPrice.getText()), txtType.getText()));

                    clean();
                    alert("Agregar Productos", "Producto guardado con éxito", 0);
                    alert.close();

                } else {
                    alert("Agregar Productos", "Codigo del producto existente", 1);
                }
            } else {
                alert("Agregar Productos", "Llene todos los campos", 1);
            }
        });

        miGenerar.setOnAction((e) -> {
            paneNew.setVisible(false);
            paneProducts.setVisible(false);
            paneReport.setVisible(true);
        });

        miNew.setOnAction((e) -> {
            paneNew.setVisible(true);
            btAddProduct.requestFocus();
            paneProducts.setVisible(false);
            paneReport.setVisible(false);
        });

        miView.setOnAction((e) -> {
            paneNew.setVisible(false);
            paneProducts.setVisible(true);
            paneReport.setVisible(false);
            data.clear();
            data.addAll(Products.getProducts());
        });

        miLogout.setOnAction((e) -> {
            new LoginView((Stage) btSave.getScene().getWindow());
        });
        Utils utils = new Utils();

        utils.validateNumber(txtPrice, txtQuantity);
        utils.editColumns(data, colCode, colName, colPrice, colQuantity, colType);

    }

    private void styles() {
        double width = ((Stage) root.getScene().getWindow()).getWidth();
        double height = ((Stage) root.getScene().getWindow()).getHeight();
        double windowW = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;

        TABLE.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-max-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + "-fx-translate-y:100;"
                + "-fx-translate-x:0;"
        );

        vb.setStyle("-fx-background-color: rgba( 0, 2, 44,0.4);"
                + "-fx-min-width:500;"
                + "-fx-min-height:250;"
                + "-fx-max-width:500;"
                + "-fx-max-height:250;"
                + "-fx-translate-y:" + (height * -0.1) + ";"
                + "-fx-alignment:CENTER;");

        paneNew.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + "-fx-translate-x: 0;"
                + "-fx-translate-Y: 25;"
                + "-fx-padding:5;"
        );

        paneCH.setStyle(
                "-fx-alignment:CENTER;"
                + ""
        );

        paneProducts.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-max-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + "-fx-translate-x: 0;"
                + "-fx-translate-Y: 25;"
                + "-fx-padding:5;"
                + "-fx-alignment:CENTER;"
        );
        paneReport.setStyle(
                "-fx-min-width:" + width + ";"
                + "-fx-min-height:" + height + ";"
                + "-fx-translate-x: 0;"
                + "-fx-translate-Y: 25;"
                + "-fx-padding:5;"
                + "-fx-alignment:CENTER;"
        );

        mnBar.setStyle(
                "-fx-min-width:" + windowW + ";"
                + "-fx-translate-x:0;"
                + "-fx-translate-y:0;"
        );

        root.setStyle("-fx-background-color:rgba( 0, 2, 44,1);");

        txtCode.setStyle(
                "-fx-translate-x:" + (width * 0.39) + ";"
                + "-fx-translate-y:" + (height * 0.2) + ";"
                //+ "-fx-min-width:" + (width * 0.2) + ";"
        );
        txtName.setStyle(
                "-fx-translate-x:"+ (width*0.24) +";"
                + "-fx-translate-y:"+ (height* 0.32) +";"
        );
        txtQuantity.setStyle(
                "-fx-translate-x:"+ (width*0.55) +";"
                + "-fx-translate-y:"+ (height* 0.32) +";"
        );
        txtType.setStyle(
                "-fx-translate-x:"+ (width*0.24) +";"
                + "-fx-translate-y:"+ (height* 0.45) +";"
        );
        txtPrice.setStyle(
                "-fx-translate-x:"+ (width*0.55) +";"
                + "-fx-translate-y:"+ (height* 0.45) +";"
        );
        txtCategory.setStyle(
                "-fx-translate-x:"+ (width*0.24) +";"
                + "-fx-translate-y:"+ (height* 0.57) +";"
        );
        txtLocation.setStyle(
                "-fx-translate-x:"+ (width*0.55) +";"
                + "-fx-translate-y:"+ (height* 0.57) +";"
        );
        
        btAddProduct.setStyle(
                "-fx-translate-x:"+ (width*0.42) +";"
                + "-fx-translate-y:"+ (height*0.71) +";"
                        + "-fx-min-width:200;"
        );

        root.setPadding(new Insets(12));
        colName.setPrefWidth((width * 0.4));
        colCode.setPrefWidth(width * 0.11);
        colType.setPrefWidth(width * 0.18);
        colPrice.setPrefWidth(width * 0.13);
        colQuantity.setPrefWidth(width * 0.09);
    }

    private void alert(String h, String c, int tp) {
        if (tp == 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else if (tp == 1) {
            alert = new Alert(Alert.AlertType.WARNING);
        }

        alert.setTitle("Advertencia");
        alert.setHeaderText(h);
        alert.setContentText(c);
        alert.showAndWait();
    }

    private void choiceItems() {
        day.getItems().clear();
        month.getItems().clear();
        year.getItems().clear();

        for (int i = 1; i < 32; i++) {
            day.getItems().add(i);
        }

        for (int i = 1; i < 13; i++) {
            month.getItems().add(i);
        }

        int y = (Calendar.getInstance().get(Calendar.YEAR));

        for (int i = 2018; i <= y; i++) {
            year.getItems().add(i);
        }

    }

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
