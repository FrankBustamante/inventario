package inventario.model;

import java.util.Date;


/**
 *
 * @author Frank Bustamante 3014234167
 */

public class Employeed extends User{
    private String createAt;
    private boolean allow;
    private String lastSign;
    private String password;
    private TypeEmployeed typeE;

    public Employeed( boolean allow, String password, TypeEmployeed typeE) {
        this.allow = allow;
        this.password = password;
        this.typeE = typeE;
        createAt = new Date().toLocaleString();
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public String getLastSign() {
        return lastSign;
    }

    public void setLastSign(String lastSign) {
        this.lastSign = lastSign;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeEmployeed getTypeE() {
        return typeE;
    }

    public void setTypeE(TypeEmployeed typeE) {
        this.typeE = typeE;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    
    public String getCreateAt() {
        return createAt;
    }
    
    
    
    
    
    
}
