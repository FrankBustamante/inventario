/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.utilities;

import inventario.logic.Products;
import inventario.model.Product;

/**
 *
 * @author Frank Bustamante
 */
public class PVent extends Product {

    private String am;

    public PVent(int Code, String name, double price, int am) {
        super(Code, name, price);
        this.am = String.valueOf(am);
    }

    public String getAm() {
        return am;
    }

    public void setAm(int am) {
        this.am = String.valueOf(am);
    }
}
