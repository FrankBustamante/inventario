/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.utilities;

import inventario.model.Product;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Frank Bustamante
 */
public class Utils {
 
    public void validateNumber(TextField ...tf){
        for(TextField field: tf){
            field.setOnKeyTyped((KeyEvent t) -> {
                char ch = t.getCharacter().charAt(0);

                if (Character.isLetter(ch)) {
                    t.consume();
                }
            });
        }
    }
    
    public void editColumns(ObservableList<Product> data, TableColumn ...tbc){
        for(TableColumn cl: tbc){
            cl.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Product, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Product, String> t) {
                ((Product) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())).setQuantity(Double.parseDouble(t.getNewValue()));
                System.out.println("nuevo Valor: " + t.getNewValue());
                System.out.println(data.get(t.getTablePosition().getRow()).getName());
            }
        }
        );
        }
    }
}
