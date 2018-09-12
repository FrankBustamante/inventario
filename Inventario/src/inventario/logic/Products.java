package inventario.logic;

import inventario.model.Product;
import inventario.persistence.local.ObjectOutputStream;
import inventario.utilities.PVent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frank Bustamante
 * 
 */
public class Products {
    
     private static final ArrayList<Product> products = new ArrayList<>();
     private static final String FILE_PAHT = "src/inventario/persistence/local/products.dat";
    
    public static ArrayList<Product> getProducts(){
        
        return Products.products;        
    }
    
    public static Product getProduct(String code){
        products.add(new Product(code, 244, "nuevo"));
         int c=-1 ;
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getCode().equals(code)){
                c = i;
                System.out.println("true");
            }
        }
       
        if (c != -1) {
            System.out.println("code: "+code);
            return Products.products.get(c);
        }
        
        return null;
    }
    
    public static boolean saveProduct(Product p){
        boolean state = false;
                
         try {
            File file = new File(FILE_PAHT);
            ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(FILE_PAHT));
            
            if(!file.exists()){
                save.writeObject(p);
            }else{
                save.writeUnshared(p);
            }
            
         } catch (FileNotFoundException ex) {
             System.out.println("error: "+ex);
             Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         }catch(IOException ioEx){
             System.out.println("Error: "+ioEx);
         }
        
        return state;
    }
    
    public static boolean updateProduct(Product p){
        boolean state = false;
        
        return state;
    }
    
    public static boolean deleteProduct(Product p){
        boolean state = false;
        
        return state;
    }
    
    
            
            
}
