/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */
public class LoginView extends Stage{

    private Pane root, paneMain;
    private HBox paneButtons;
    private TextField txtuser;
    private Button btUser, btAdmin;
    private Label lbBr;
    
    public LoginView(Stage stage) {
        init();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/inventario/style/LoginStyle.css");
        stage.setScene(scene);
        stage.setMaxHeight(640);
        stage.setMaxWidth(790);
        
        stage.setTitle("Inicio de sesion");
        stage.setResizable(false);
        stage.show();
    }
    
    private void init(){
        //PANE
        root = new Pane();
        paneMain = new Pane();
        paneButtons = new HBox(10);
        
        //BUTTON
        btUser = new Button("VENDEDOR");
        btAdmin = new Button("ADMINISTRADOR");
        
        //LABEL
        lbBr = new Label("-");
        
        confg();
        event();
    }
    private void confg(){
        //ID
        paneMain.setId("paneMain");
        root.setId("root");
        paneButtons.setId("paneButtons");
        btAdmin.setId("button");
        btUser.setId("button");
        lbBr.setId("br");
        
        
        //SIZE
        paneMain.setPrefSize(790, 460);
        paneMain.setMinSize(790, 640);
        paneButtons.setPrefSize(364, 53);
                
        //ADD
        root.getChildren().addAll(paneMain, paneButtons);
        paneButtons.getChildren().addAll(btUser, lbBr, btAdmin);
    }
    
    private void event(){
        btUser.setOnAction((e) ->{
            System.out.println("usuario");
         
            new ViewMain((Stage) ((Node) e.getSource()).getScene().getWindow());
        });
        
        btAdmin.setOnAction((e) ->{
            System.out.println("Admin");
        });
    }
}
