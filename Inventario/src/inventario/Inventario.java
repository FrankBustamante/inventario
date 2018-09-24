package inventario;

import inventario.utilities.CodeRandom;
import inventario.view.Login;
import inventario.view.LoginView;
import inventario.view.ViewMain;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Frank Bustamante
 */
public class Inventario extends Application{

    /**
     * @param args the command line arguments
     */
    //Login vm = new Login();
    private static Alert alert;
    
   static Scene scene;
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        alert = new Alert(Alert.AlertType.INFORMATION);
        
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        scene = new Scene(root);
        
       // stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 100.0);
        //stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 50.0);
        //stage.setMinHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        //stage.setMinWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        //stage.setScene(scene);
        LoginView lg =new LoginView(stage);
        //stage.setTitle("Invetario");
        //stage.setResizable(false);
        //stage.show();
        
    }
    
    public static void alerta(String t, String h, String c){
        Stage s = new Stage();
        alert.setTitle(t);
        alert.setHeaderText(h);
        alert.setContentText(c);
        alert.showAndWait();
    }
    
   
    
    public static Scene getScene(){
        return scene;
    }
    
}
