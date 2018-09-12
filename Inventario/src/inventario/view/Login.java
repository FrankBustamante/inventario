/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.view;

import inventario.Inventario;
import inventario.logic.Auth;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Frank Bustamante
 */

public class Login extends Pane {
    Auth auth = new Auth();
    private final String titel  = "Inicio de sesion";
    private TextField txtUser;
    private PasswordField txtPassword;
    private Label lUser;
    private Label lPasword;
    private Button btIngresar;
    
    public Login() {
        
        getConfig();
    }
    
    public String getTitle(){
        return this.titel;
    }
    
    private void getConfig(){
        //iniciar elementos
    	txtUser = new TextField();
    	txtPassword = new PasswordField();
    	lUser = new Label("Usuario");
    	lPasword = new Label("Contraseña");
    	btIngresar = new Button("Ingresar");
        
        //configurar elementos
        this.setPrefSize(400, 200);
        
        //agregar elementos
        this.getChildren().addAll(new VBox(lUser,txtUser,lPasword,txtPassword,btIngresar));
        
        //eventos
        getEvents();
    }
    
    private void getEvents(){
        btIngresar.setOnMouseClicked((MouseEvent e) -> {
            new Inventario().getScene().setRoot(new ViewAdmin());
           if(auth.logIn(txtUser.getText(),txtPassword.getText())){
              //new Inventario().getScene().setRoot(new ViewMain());
              new Inventario().getScene().setRoot(new ViewAdmin());
           }
        });
    }
}
