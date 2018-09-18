package inventario.model;

import com.itextpdf.text.pdf.Barcode39;
import inventario.utilities.CodeRandom;
import inventario.utilities.PVent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Frank Bustamante
 */
public class Vending implements Serializable{
    private String codeFact;
    private ArrayList<PVent> products;
    private static final  String DATA_V = new Date().toLocaleString();
    private String total;

    public Vending(ArrayList<PVent> products,String  total) {
        this.products = products;
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

    public ArrayList<PVent> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<PVent> products) {
        this.products = products;
    }

    public static String getDATA_V() {
        return DATA_V;
    }
        
}
