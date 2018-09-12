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
public class Category {
    private final String code;
    private String name;

    public Category( String name) {
        this.code = new CodeRandom().getCode("04");
        this.name = name;
    }

    

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
