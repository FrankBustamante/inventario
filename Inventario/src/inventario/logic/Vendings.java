package inventario.logic;

import inventario.model.Product;
import inventario.model.Vending;
import inventario.persistence.local.ObjectOutputStreamL;
import inventario.utilities.PVent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Frank Bustamante
 *
 */
public class Vendings {

    private static final String FILE_PAHT = "src/inventario/persistence/local/vendings ";

    public static ArrayList<Vending> getVendings(String date) {
        ArrayList<Vending> Vendings = new ArrayList<>();

        try {
            File file = new File(FILE_PAHT + date + ".dat");

            if (file.exists()) {
                ObjectInputStream get = new ObjectInputStream(new FileInputStream(file));
                Vendings.clear();
                Object vending = get.readObject();

                while (vending != null) {
                    System.out.println("leido");
                    if (vending instanceof Vending) {
                        Vendings.add((Vending) vending);
                    }
                    vending = get.readObject();
                }
                get.close();
            } else {
                System.out.println("no hay ventas guardadas para esa fecha");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("eror: " + ex);
        } catch (EOFException f) {
            System.out.println("fin del fichero");
        } catch (Exception er) {
            System.out.println("error: " + er);
        }

        return Vendings;
    }

    public static Vending getVending(String code) {
        Vending v = null;

        return v;
    }

    public static boolean saveVending(Vending v, ObservableList<PVent> pv) {

        try {
            ArrayList<Product> prs = Products.getProducts();
            

            pv.forEach(pr -> {
                String cod = pr.getCode();
                int val = (int) Double.parseDouble(prs.get(prs.indexOf(
                        Products.getProduct(cod))).getQuantity()) - Integer.parseInt(pr.getAm());
                prs.get(prs.indexOf(Products.getProduct(pr.getCode()))).setQuantity(val);
            });
            
            ObservableList<Product> products = FXCollections.observableArrayList(prs);
            if (Products.updateProduct(products)) {
                Calendar calendar = Calendar.getInstance();
                String dateString = calendar.get(Calendar.DATE) + "-" + 
                        (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR) + ".dat";

                File file = new File(FILE_PAHT + dateString);

                if (!file.exists()) {
                    ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(file));
                    save.writeObject(v);
                    save.close();
                    System.out.println("escribio ...");
                    return true;

                } else {
                    ObjectOutputStreamL save = new ObjectOutputStreamL(new FileOutputStream(file, true));
                    save.writeUnshared(v);
                    System.out.println("agrego...");
                    save.close();
                    return true;
                }
            }else{ return false; }
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("error: " + ex);
            Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioEx) {
            System.out.println("Error: " + ioEx);
        }
        return false;
    }

    public boolean updateVending(Vending v) {
        boolean state = false;

        return state;
    }

    public boolean deleteVending(Vending v) {
        boolean state = false;

        return state;
    }

}
