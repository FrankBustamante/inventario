package inventario.model;

import java.util.Date;

/**
 *
 * @author Frank Bustamante
 */
public class Client extends User{
    private String joined = new Date().toLocaleString();
    private int numComp;
    
    Client(String name){
        this.setName(name);
    }

    public int getNumComp() {
        return numComp;
    }

    public void setNumComp(int numComp) {
        this.numComp = numComp;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getJoined() {
        return this.joined;
    }
    
    
    Client(){
        
    }
}
