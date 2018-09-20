package inventario;

import inventario.utilities.CodeRandom;
import inventario.view.Login;
import inventario.view.RenderView;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    Login vm = new Login();
    
   static Scene scene;
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(vm);
        
        stage.setMaxHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setMaxWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        stage.setMinHeight(java.awt.Toolkit.getDefaultToolkit().getScreenSize().height);
        stage.setMinWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width);
        stage.setScene(scene);
        stage.setTitle("");
        stage.show();
    }
    
    public Scene getScene(){
        return scene;
    }
    
}
