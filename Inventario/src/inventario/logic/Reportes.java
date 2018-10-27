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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Frank Bustamante
 */
public class Reportes {
    private static final String enviro = "nombre de la hoja";
    private static final String FILE_PATH =  "src/inventario/persistence/local/reporte ";
    
    public static boolean newReporte(String date, Stage stage) throws InterruptedException{
        
        ArrayList<Vending> vendings = Vendings.getVendings(date);
        
        if(vendings.size() < 1 ){
            return false;
        }
        
        Workbook b = new XSSFWorkbook();
        Sheet s = b.createSheet(enviro);
        Row row = s.createRow(0);
        row.createCell((0)).setCellValue("Cod. Venta/Producto");
        row.createCell((1)).setCellValue("Nombre");
        row.createCell((2)).setCellValue("Precio");
        row.createCell((3)).setCellValue("Cantidad");
        int index = 1;
       
        for(int j = 0; j < vendings.size(); j++){
            Row row1;
            Vending v = vendings.get(j);
            s.createRow(index).createCell(0).setCellValue(v.getCodeFact());
            for (int i = 0; i < v.getProducts().length; i++) {
                row1 = s.createRow((index));
                row1.createCell((1)).setCellValue(v.getProducts()[i].getName());
                row1.createCell((2)).setCellValue(v.getProducts()[i].getPrice());
                row1.createCell((3)).setCellValue(v.getProducts()[i].getAm());
                index ++;
            }
            
            row1 = s.createRow((index));
            row1.createCell(2).setCellValue("TOTAL");
            row1.createCell(3).setCellValue(v.getTotal());
            index ++;
        }
        
       
        
        try {
            FileChooser fc = new FileChooser();
            fc.showSaveDialog(stage);
            try (FileOutputStream f = new FileOutputStream(FILE_PATH+date+".xlsx")) {
                b.write(f);
                f.close();
                return true;
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
