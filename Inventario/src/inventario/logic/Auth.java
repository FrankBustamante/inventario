/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.logic;

/**
 *
 * @author Frank Bustamante
 */
public class Auth {
    private static boolean isAuth = false;
    
    public boolean isAuth(){
        return isAuth;
    }
    
    public boolean logIn(String user, String pass){
        if ("user".equals(user) && "pass".equals(pass)) {
            return isAuth = true;
        }
     
        return false;
    }
}
