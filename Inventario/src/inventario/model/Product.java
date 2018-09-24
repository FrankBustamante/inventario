/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.model;

import inventario.utilities.CodeRandom;
import java.io.Serializable;

/**
 *
 * @author Frank Bustamante
 */
public class Product implements Serializable{
    private String name;
    private String category;
    private String code;
    private String location;
    private String quantity;
    private String price;
    private String  type;

    
    public Product(int code, String name, double price) {
        this.code = String.valueOf(code);
        this.name = name;
        this.price = String.valueOf(price);
    }

    public Product(String code, String am) {
        this.code = null;
    }
    
    
    
    public Product(String code, String name, String category, String location, int quantity, double price, String  type) {
        this.name = name;
        this.category = category;
        this.code = code;
        this.location = location;
        this.quantity = String.valueOf( quantity);
        this.price = String.valueOf(price);
        this.type = (type);
    }

    
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return (this.quantity);
    }

    public void setQuantity(double quantity) {
        this.quantity = String.valueOf(quantity);
    }

    public String getPrice() {
        return (this.price);
    }

    public void setPrice(double price) {
        this.price = String.valueOf(price);
    }

    public String  getType() {
        return this.type;
    }

    public void setType(String  type) {
        this.type=(type);
    }
    
    

    
    
    
    
}
