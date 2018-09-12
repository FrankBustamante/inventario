/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.model;

import inventario.utilities.CodeRandom;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Frank Bustamante
 */
public class Product {
    private String name;
    private String category;
    private  String code;
    private String location;
    private int quantity;
    private double price;
    private String  type;

    
    public Product(String name, double price, String type) {
        this.code = name;
        
        this.name = name;
        this.price = price;
        this.type = (type);
        
    }

    public Product(String code, String am) {
        this.code = null;
    }
    
    
    
    public Product(String name, String category, String location, int quantity, double price, String  type) {
        this.name = name;
        this.category = category;
        this.code = new CodeRandom().getCode("01");
        this.location = location;
        this.quantity = quantity;
        this.price = price;
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
        
    }
    
    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String  getType() {
        return this.type;
    }

    public void setType(String  type) {
        this.type=(type);
    }
    
    

    
    
    
    
}
