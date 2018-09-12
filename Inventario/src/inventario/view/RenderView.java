/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario.view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 *
 * @author Frank Bustamante
 */
public class RenderView {
    
    Login l = new Login();
    
    private static Node root;
    
    private String  title;

    public RenderView() {
        RenderView.root = l;
    }
    

      
    public void setRoot(Node root){
        RenderView.root = root;
    }
    public Pane getRoot(){
       return (Pane) RenderView.root;
    }
   
    
}
