package inventario.utilities;

import inventario.logic.Products;
import inventario.model.Product;
import java.util.ArrayList;

/**
 *
 * @author Frank Bustamante
 */
public class CodeRandom {
   private ArrayList<String> codes = new ArrayList<>();
    

    public CodeRandom() {
    }

    public boolean getCode(String code) {
        if(Products.getProducts().isEmpty()) return true;
        
        codes.clear();
        Products.getProducts().forEach((product) -> {
            codes.add(product.getCode());
       });
        
        return this.codes.indexOf(code)== -1;
    }
    
    private int getRandom(){
        return (int) (Math.random()*9 +1);
    }
    
    public boolean validCode(String code){
        
        return true;
    }
    
    ArrayList getCodes(){
        return this.codes;
    }
    
    void setCodes(String code){
        this.codes.add(code);
    }
    
    void setCodes(ArrayList<String> codes){
        this.codes = codes;
    }
    
}
