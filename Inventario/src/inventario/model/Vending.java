/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.model;

import com.itextpdf.text.pdf.Barcode39;
import inventario.utilities.CodeRandom;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Frank Bustamante
 */
public class Vending {
    private String codeFact;
    private ArrayList<Product> products;
    private Client client;
    private static final  String dataV = new Date().toLocaleString();
    private Employeed employeed;

    public Vending(ArrayList<Product> products, Client client, Employeed employeed) {
        this.products = products;
        this.client = client;
        this.employeed = employeed;
        
        Barcode39 code = new Barcode39();
        
        this.codeFact = (new CodeRandom().getCode("02"));
        
    }

   
    
    
    
}
