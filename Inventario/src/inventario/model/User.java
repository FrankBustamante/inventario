/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.model;

import inventario.utilities.CodeRandom;

/**
 *
 * @author Frank Bustamante
 */
abstract class User {
    
    private String id;
    private String name;
    private String lastName;
    private String photo;
    private int doc;

    public User() {
        this.id = "";
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }
    
    
    
}
