/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.utilities;
import inventario.logic.Products;
import inventario.model.Product;

/**
 *
 * @author Frank Bustamante
 */
public class PVent extends Product{
    
    private String amount;
    private String cod;
    static Product p;

    public PVent(String code, String am) {
        super(code, am);
        this.cod= code;
        this.amount=(am);
        c();
    }
    
    private void c(){
        p = Products.getProduct(cod);  
        
        this.setName(p.getName());
        this.setType(p.getType());
        
        
        this.setPrice(p.getPrice());
    }
    
    
        
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String getCode() {
        return cod;
    }

    @Override
    public void setCode(String code) {
        this.cod = code;
    }
    
    
    
    
    
    
   
    
}
