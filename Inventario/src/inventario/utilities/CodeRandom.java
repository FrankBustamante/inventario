package inventario.utilities;

import java.util.ArrayList;

/**
 *
 * @author Frank Bustamante
 */
public class CodeRandom {
   private ArrayList<String> codes = new ArrayList<>();
    

    public CodeRandom() {
    }

    public String getCode(String head) {   
        String code = String.valueOf(getRandom()+""+getRandom()+""+getRandom()
                +""+getRandom()+""+getRandom()+""+getRandom()+""+getRandom());
        
        if (this.codes.indexOf(head+code)!=-1) {
            System.out.println("encontrado");
             code = getCode(head);
        }
        return  head+code;
    }
    
    private int getRandom(){
        return (int) (Math.random()*9 +1);
    }
    
    public boolean validCode(String code){
        
        return true;
    }
    
    ArrayList getCodes(){
        return this.codes;
    }
    
    void setCodes(String code){
        this.codes.add(code);
    }
    
    void setCodes(ArrayList<String> codes){
        this.codes = codes;
    }
    
}
