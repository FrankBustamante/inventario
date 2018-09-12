package inventario.view;

import inventario.logic.Products;
import inventario.model.Category;
import inventario.model.Product;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */
public class ViewAdmin extends VBox{

    private Button btNewProduct, btAddProduct;
    private TextField txtName, txtType, txtLocation, txtPrice, txtQuantity, txtCategory;
    
    public ViewAdmin() {
        launch();
    }
    
    private void launch(){
        //INICIAR ELEMENTOS
        init();
        config();
        events();
        
        //AGREGAR ELEMENTOS
        this.getChildren().addAll(new HBox(btAddProduct, btNewProduct),
                new HBox(new VBox(txtLocation, txtName, txtPrice), 
                        new VBox(txtQuantity, txtType, txtCategory)
                )
            );
    }
    
    private void init(){
        //BUTTON
        btAddProduct = new Button("Guardar");
        btNewProduct = new Button("Nuevo");
        
        //TEXTFIELD
        txtName = new TextField();
        txtLocation = new TextField();
        txtType = new TextField();
        txtPrice = new TextField();
        txtQuantity = new TextField();
        txtCategory = new TextField();
        
    }
    
    private void config(){
        txtLocation.setPromptText("Ubicacion");
        txtName.setPromptText("Nombre");
        txtPrice.setPromptText("Precio");
        txtQuantity.setPromptText("Cantidad");
        txtType.setPromptText("Tipo");
        txtCategory.setPromptText("Categoria");
        
    }
    
    private void events(){
        
        btAddProduct.setOnAction((e)->{
            Products.saveProduct(new Product(txtName.getText(),txtCategory.getText(),
            txtLocation.getText(),Integer.parseInt(txtQuantity.getText()),
            Double.parseDouble(txtPrice.getText()), txtType.getText()));
            
        });
                
    }
    
            
    
    
}
