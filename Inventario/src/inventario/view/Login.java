/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.view;

import inventario.Inventario;
import inventario.logic.Auth;
import inventario.logic.Products;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */

public class Login implements Initializable{
    Auth auth = new Auth();
    private final String titel  = "Inicio de sesion";
    
    @FXML
    private TextField txtUser;
    
    @FXML
    private PasswordField txtPassword;

    @FXML
    private ToggleButton btUser, btAdmin;

    @FXML
    private Pane paneAdmin;

    @FXML
    private Button btLogin;
            
    public Login() { 
    }

    @FXML
    private void logIn(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("ViewAdmin.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(new Scene(root));
        appStage.show();
    }
    
    @FXML
    private void eventUser(ActionEvent event) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("ViewMain.fxml"));
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(new Scene(root));
        appStage.show();
    }

    @FXML
    private void eventAdmin(ActionEvent event){
        if (paneAdmin.isVisible()) {
            paneAdmin.setVisible(false);
        }else{
            paneAdmin.setVisible(true);

        }
    }
    
    public String getTitle(){
        return this.titel;
    }
    
    private void getConfig(){
        //iniciar elementos
    	// lUser = new Label("Usuario");
    	// lPasword = new Label("Contraseña");
    	// btIngresar = new Button("Ingresar");
     //    bt = new ToggleButton("es togle");
        
        //configurar elementos
        //this.setPrefSize(400, 200);
        
        //agregar elementos
        //this.getChildren().addAll(new VBox(lUser,txtUser,lPasword,txtPassword,btIngresar,bt));
        
        //eventos
        getEvents();
    }
    
    private void getEvents(){
        // btIngresar.setOnMouseClicked((MouseEvent e) -> {
            
        //     //Inventario.getScene().setRoot();
        //    if(auth.logIn(txtUser.getText(),txtPassword.getText())){
        //       //new Inventario().getScene().setRoot(new ViewMain());
        //       //new Inventario().getScene().setRoot(new ViewAdmin());
        //    }
        // });       
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }
}
