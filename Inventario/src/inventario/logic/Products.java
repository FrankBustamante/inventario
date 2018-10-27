package inventario.logic;

import inventario.model.Product;
import inventario.persistence.local.ObjectOutputStreamL;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Frank Bustamante
 * 
 */
public class Products {
    
     private static final ArrayList<Product> products = new ArrayList<>();
     private static final String FILE_PAHT = "src/inventario/persistence/local/products.dat";
    
    public static ArrayList<Product> getProducts(){
        try {
            File file = new File(FILE_PAHT);
            
            if (file.exists()) {
                ObjectInputStream get = new ObjectInputStream(new FileInputStream(FILE_PAHT));
                products.clear();
                Object product = get.readObject();
                
                while (product != null) {
                    if (product instanceof Product) {
                        Products.products.add((Product) product);
                    }
                    product = get.readObject();
                }
                get.close();
            }else{
                System.out.println("no hay productos guardados");
            }
            
        }
        catch (ClassNotFoundException ex) {
             Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("eror: "+ex);
        }catch(EOFException f){
             System.out.println("fin del fichero");
        }
        catch(Exception er){
             System.out.println("error: "+er);
        }
        
        return Products.products;        
    }
    
    public static Product getProduct(String code){
        getProducts();
         for(Product p : products) {
             if(p.getCode().equals(code)){
                 return p;
             }
        }
        return null;
    }
    
    public static boolean saveProduct(Product p){
        boolean state = false;
                
         try {
            File file = new File(FILE_PAHT);
            
            if(!file.exists()){
                ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(FILE_PAHT));
                Product pro = p;
                save.writeObject(p);
                save.close();
            }else{
                ObjectOutputStreamL save = new ObjectOutputStreamL(new FileOutputStream(FILE_PAHT, true));
                Product pro = p;
                save.writeUnshared(p);
                save.close();
            }
            
         } catch (FileNotFoundException ex) {
             System.out.println("error: "+ex);
             Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         }catch(IOException ioEx){
             System.out.println("Error: "+ioEx);
         }
        
        return state;
    }
    
    public static boolean updateProduct(ObservableList<Product> products){
        try {
            File file = new File(FILE_PAHT);
            
            if(file.exists()){
                ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(file));
                
                products.forEach(p ->{
                    try {
                        save.writeObject(p);
                    } catch (IOException ex) {
                        Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                save.close();
                return true;
            }else{
                
            }
            
         } catch (FileNotFoundException ex) {
             System.out.println("error: "+ex);
             Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
         }catch(IOException ioEx){
             System.out.println("Error: "+ioEx);
         }
        
        
        return false;
    }
    
    public static boolean deleteProduct(Product p){
        boolean state = false;
        
        return state;
    }
    
    
            
            
}
