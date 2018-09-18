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
        System.out.println("obteniendo...");
        try {
            File file = new File(FILE_PAHT);
            
            if (file.exists()) {
                ObjectInputStream get = new ObjectInputStream(new FileInputStream(FILE_PAHT));
                products.clear();
                Object product = get.readObject();
                
                while (product != null) {
                    System.out.println("leido");
                    if (product instanceof Product) {
                        System.out.println("obtenido "+((Product) product).getQuantity());
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
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getCode().equals(code)){
                return products.get(i);
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
    
    public static boolean updateProduct(Product p){
        boolean state = false;
        
        return state;
    }
    
    public static boolean deleteProduct(Product p){
        boolean state = false;
        
        return state;
    }
    
    
            
            
}
