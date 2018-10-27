/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.view;

import inventario.logic.Auth;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Frank Bustamante
 */
public class LoginView extends Stage {

    private Pane root, paneMain;
    private VBox paneForm;
    private HBox paneButtons;
    private TextField txtuser;
    private PasswordField txtPassword;
    private Button btUser, btAdmin, btLogin, btBack;
    private Label lbBy, lbFrase;

    public LoginView(Stage stage) {
        init();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/inventario/style/LoginStyle.css");
        stage.setScene(scene);
        stage.setMaxHeight(640);
        stage.setMaxWidth(790);
        stage.setMinHeight(640);
        stage.setMinWidth(790);

        stage.setTitle("Inicio de sesion");
        stage.setResizable(false);
        stage.show();
        //new ViewAdmin(stage);
    }

    private void init() {
        //PANE
        root = new Pane();
        paneMain = new Pane();
        paneButtons = new HBox(30);
        paneForm = new VBox(20);

        //BUTTON
        btUser = new Button("VENDEDOR");
        btAdmin = new Button("ADMINISTRADOR");
        btLogin = new Button("INGRESAR");
        btBack = new Button("Regresar");

        //TEXTFIELD
        txtPassword = new PasswordField();
        txtuser = new TextField();

        //LABEL
        lbBy = new Label("Powered By Frank Bustamante");
        lbFrase = new Label("Un mundo de herramientas a su alcance");

        confg();
        event();
    }

    private void confg() {
        //ID
        paneMain.setId("paneMain");
        root.setId("root");
        paneButtons.setId("paneButtons");
        btAdmin.setId("button1");
        btUser.setId("button");
        paneForm.setId("paneForm");
        txtPassword.setId("txtPass");
        txtuser.setId("txtUser");
        btLogin.setId("btLog");
        btBack.setId("btBack");
        lbFrase.setId("frase");

        //SIZE
        paneMain.setPrefSize(790, 460);
        paneMain.setMinSize(790, 640);
        paneButtons.setPrefSize(364, 53);

        paneForm.setAlignment(Pos.CENTER);

        //VISIBLE
        paneForm.setVisible(false);
        btBack.setVisible(false);

        txtuser.setPromptText("Usuario");
        txtPassword.setPromptText("Contraseña");

        //STYLE
        lbBy.setStyle(
                "-fx-min-width:790;"
                + "-fx-alignment:CENTER;"
                        + "-fx-translate-y:596;"
                        + "-fx-text-fill:grey;"
        );

        //ADD
        root.getChildren().addAll(paneMain, lbFrase, lbBy, btBack, paneButtons, paneForm);
        paneButtons.getChildren().addAll(btUser, btAdmin);
        
        paneForm.getChildren().addAll(txtuser, txtPassword, btLogin);
        
    }

    private void event() {

        btAdmin.setOnAction((e) -> {
            paneForm.setVisible(true);
            paneButtons.setVisible(false);
            lbFrase.setVisible(false);
            btBack.setVisible(true);
            btLogin.requestFocus();
        });

        btBack.setOnAction((e) -> {
            btBack.setVisible(false);
            paneForm.setVisible(false);
            lbFrase.setVisible(true);
            paneButtons.setVisible(true);
            
        });
        
        btLogin.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
             
                if (event.getEventType().getName().equals("MOUSE_PRESSED")) {
                    Auth auth = new Auth();
                    new ViewAdmin((Stage) ((Node) event.getSource()).getScene().getWindow());

                    if (auth.logIn(txtuser.getText(), txtPassword.getText())) {

                    }
                }
            }

        });

        btUser.addEventHandler(EventType.ROOT, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
               
                if (event.getEventType().getName().equals("MOUSE_PRESSED")) {
                    new ViewMain((Stage) ((Node) event.getSource()).getScene().getWindow());
                }
            }

        });
    }
}
