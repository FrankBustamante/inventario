package inventario.model;

import com.itextpdf.text.pdf.Barcode39;
import inventario.utilities.CodeRandom;
import inventario.utilities.PVent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author Frank Bustamante
 */
public class Vending implements Serializable{
    private String codeFact;
    private PVent[] products;
    private static final  String DATA_V = new Date().toLocaleString();
    private String total;

    public Vending(ObservableList<PVent> p,String  total) {
        this.products = new PVent[p.size()];
        for (int i = 0; i < p.size(); i++) {
            products[i] = p.get(i);
        }
        
        this.codeFact = "";
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
    public String getCodeFact() {
        return codeFact;
    }

    public void setCodeFact(String codeFact) {
        this.codeFact = codeFact;
    }

    public PVent[] getProducts() {
        
        return products;
    }

    public void setProducts(ObservableList<PVent> products) {
        
    }

    public static String getDATA_V() {
        return DATA_V;
    }
        
}
