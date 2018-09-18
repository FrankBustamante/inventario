package inventario.view;

import inventario.logic.Products;
import inventario.model.Product;
import inventario.utilities.CodeRandom;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */
public class ViewAdmin extends VBox{

    private Button btNewProduct, btAddProduct, btViewProducts;
    private TextField txtName, txtType, txtLocation, txtPrice, txtQuantity, txtCategory, txtCode;
    private Pane paneNew, paneProducts;
    private final TableView<Product> TABLE = new TableView<>();
    private final ObservableList<Product> data = FXCollections.observableArrayList();
    private TableColumn colCode, colType, colPrice, colName, colQuantity, colActions; 
    
    public ViewAdmin() {
        launch();
    }
    
    private void launch(){
        //INICIAR ELEMENTOS
        init();
        config();
        events();
        
        //TABLEVIEW
        TABLE.setItems(data);
        TABLE.getColumns().addAll(colCode, colName, colType,colPrice, colQuantity, colActions);

        //AGREGAR ELEMENTOS
        this.paneNew.getChildren().addAll(
                new HBox(new VBox(txtCode, txtLocation, txtName, txtPrice), 
                        new VBox(txtQuantity, txtType, txtCategory),
                        btAddProduct
        ));
        
        this.paneProducts.getChildren().addAll(TABLE);
        this.getChildren().addAll(new HBox(btNewProduct, btViewProducts),
                        paneNew,paneProducts
                
        );
    }
    
    private void init(){
        //BUTTON
        btAddProduct = new Button("Guardar");
        btNewProduct = new Button("Nuevo");
        btViewProducts = new Button("Productos");
        
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
        paneNew = new Pane();
        paneProducts = new Pane();
        
    }
    
    private void config(){
        //TEXTFIELD
        txtLocation.setPromptText("Ubicacion");
        txtName.setPromptText("Nombre");
        txtPrice.setPromptText("Precio");
        txtQuantity.setPromptText("Cantidad");
        txtType.setPromptText("Tipo");
        txtCategory.setPromptText("Categoria");
        
        //PANE
        paneProducts.setVisible(false);
        paneNew.setVisible(true);
        
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
    
    private void events(){
        CodeRandom c = new CodeRandom();
        //BUTTON
        btAddProduct.setOnAction((e)->{
            if(c.getCode(txtCode.getText())){
                
                Products.saveProduct(new Product(txtCode.getText(), txtName.getText(),txtCategory.getText(),
                    txtLocation.getText(), Integer.parseInt(txtQuantity.getText()),
                    Double.parseDouble(txtPrice.getText()), txtType.getText()));
            
                clean();
            }else{
                
            }
            
        });    
        
        btNewProduct.setOnAction((e)->{
            paneNew.setVisible(true);
            paneProducts.setVisible(false);
        });
        
        btViewProducts.setOnAction((e)->{
            paneNew.setVisible(false);
            paneProducts.setVisible(true); 
            data.clear();
            data.addAll(Products.getProducts());
        });
        
        //TEXTFIELD
        txtPrice.setOnKeyPressed((e) ->{
            System.out.println(e.getCode());
            
            

            
        });
    }
    
    private void clean(){
        txtName.setText("");
        txtCategory.setText("");
        txtLocation.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
        txtType.setText("");
        txtCode.setText("");
    }
    
}