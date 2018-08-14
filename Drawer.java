/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawer;

import static java.lang.System.exit;

/**
 *
 * @author abdolhameed
 */
public class Drawer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        painter [] s=new painter[3];
        s[0]=new painter();
        s[1]=new painter();
        s[2]=new painter();
        
        s[0].setScalex(5);
        s[0].setScaley(5);
        s[0].paintEllipse(10,5);
        
        s[1].setScalex(5);
        s[1].setScaley(5);
        s[1].paintParabola(10);
        
        s[2].setScalex(5);
        s[2].setScaley(5);
        s[2].paintHyperbola(10,6);
        
        //exit(0);
    }
    
}
