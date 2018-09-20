package inventario.logic;

import inventario.Inventario;
import inventario.model.Vending;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Frank Bustamante
 */
public class Reportes {
    private Date date = new Date();
    private static final String enviro = "nombre de la hoja";
    private static final String FILE_PATH =  "src/inventario/persistence/local/reporte ";
    
    public static boolean newReporte(String date){
        
        ArrayList<Vending> vendings = Vendings.getVendings(date);
        Workbook b = new XSSFWorkbook();
        Sheet s = b.createSheet(enviro);
        Row row = s.createRow(0);
        
        for (int i = 0; i < vendings.size(); i++) {
            for (int j = 0; j < vendings.get(i).getProducts().length; j++) {
                
            }
        }
       
        
        try {
            try (FileOutputStream f = new FileOutputStream(FILE_PATH+date+".xlsx")) {
                b.write(f);
                f.close();
            }
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
