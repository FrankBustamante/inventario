/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.model;

import inventario.utilities.CodeRandom;
import java.util.ArrayList;

/**
 *
 * @author Frank Bustamante
 */
public class TypeEmployeed {
    private final String id;
    private String name;
    private ArrayList<String> permission = new ArrayList<>();

    public TypeEmployeed(String name, String permission) {
        this.id ="";
        this.name = name;
        this.permission.add(permission);
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission.add(permission);
    }
    
    
    
}
