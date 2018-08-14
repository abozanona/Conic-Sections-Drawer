/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author abdolhameed
 */

//http://math2.org/math/algebra/conics.htm
//http://www.wolframalpha.com/widgets/view.jsp?id=4be4308d0f9d17d1da68eea39de9b2ce
enum shp{
    test,nothing,circuit,ellipse,hyperbola,parabola
}
public class painter extends JFrame{
    private float scalex=1, scaley=1;
    private int width, height;
    
    private int _radius, _a, _b, _p;
    private shp shape=shp.nothing;
    private String eq="";
    
    public painter(){
        
        JPanel panel=new JPanel();
        getContentPane().add(panel);
        setSize(450,450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        
    }
    public void windowClosed(WindowEvent e){
        System.exit(0);
    }
    public float getScalex() {
        return scalex;
    }

    public void setScalex(float scalex) {
        this.scalex = scalex;
    }

    public float getScaley() {
        return scaley;
    }

    public void setScaley(float scaley) {
        this.scaley = scaley;
    }


    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        boolean toinnerdraw;
        width=getWidth();
        height=getHeight();
        
        int centerx=width/2;
        int centery=height/2;
        
        g.drawString(eq, 5, 15);
        //draw the axises
        g.drawLine(0, centery, width, centery);
        g.drawLine(centerx, 0, centerx, height);
        
        int oldx=0,oldy=0,newx=0,newy=0;
        int oldx2=0,oldy2=0,newx2=0,newy2=0;
        boolean isdraw1=false, isdraw2=false;
        for(int i=-centerx;i<=centerx;i+=1){
            int p1x,p2x,p1y,p2y;

            oldx=newx;
            oldy=newy;
            newx=i;
            newy=fn(i);

            p1x = (int) (oldx * scalex);
            p1y = (int) (oldy * scaley);
            p2x = (int) (newx * scalex);
            p2y = (int) (newy * scaley);
            toinnerdraw=true;
            if(p1x<-centerx || p1y<-centery || p2x<-centerx || p2y<-centery || p1x>centerx || p1y>centery || p2x>centerx || p2y>centery ){
                isdraw1 = false;
                toinnerdraw = false;
            }
            if(isdraw1 && toinnerdraw){
                g.drawLine(centerx + p1x, centery-p1y  , centerx + p2x,  centery - p2y );
            }
            if(toinnerdraw)
                isdraw1=true;
            
            oldx2=newx2;
            oldy2=newy2;
            newx2=i;
            newy2=fn2(i);

            p1x = (int) (oldx2 * scalex);
            p1y = (int) (oldy2 * scaley);
            p2x = (int) (newx2 * scalex);
            p2y = (int) (newy2 * scaley);
            toinnerdraw=true;
            if(p1x<-centerx || p1y<-centery || p2x<-centerx || p2y<-centery || p1x>centerx || p1y>centery || p2x>centerx || p2y>centery ){
                isdraw2 = false;
                toinnerdraw = false;
            }
            if(isdraw2 && toinnerdraw){
                g.drawLine(centerx + p1x, centery-p1y  , centerx + p2x,  centery - p2y );
            }
            if(toinnerdraw)
                isdraw2=true;

        
        }
        
    }
    
    public void paintNothing(int radius){
        eq="";
        shape=shp.nothing;
    }

    public void paintCircle(int radius){
        _radius=radius;
        eq="X^2 + Y^2 = "+ radius*radius;
        shape=shp.circuit;
    }

    public void paintEllipse(int a, int b){
        _a=a;
        _b=b;
        eq= "X^2 / " + a*a + " + Y2 / " + b*b + " = 1";
        shape=shp.ellipse;
    }

    public void paintHyperbola(int a, int b){
        _a=a;
        _b=b;
        eq= "X^2 / " + a*a + " - Y2 / " + b*b + " = 1";
        shape=shp.hyperbola;
    }

    public void paintParabola(int p){
        _p=p;
        eq = 4*p+"X = Y^2";
        shape=shp.parabola;
    }

    public void paintTestFunction(/*No parametrs needed*/){
        //prints (x^2) & (x^3) functions 
        shape=shp.test;
    }

    private int fn(int x){
        if(shape==shp.circuit)
            return (int) Math.sqrt(_radius*_radius - x*x); 
        if(shape==shp.ellipse)
            return (int)(_b*Math.sqrt(_a*_a-x*x)/_a);     
        if(shape==shp.hyperbola)
            return (int)(Math.sqrt(-_b*_b*(_a*_a-x*x))/_a);
        if(shape==shp.parabola)
            return (int)(2*Math.sqrt(_p)*Math.sqrt(x));     
        if(shape==shp.test)
            return x*x;
        
        return 0;       
    }

    private int fn2(int x){
        if(shape==shp.circuit)
            return -(int) Math.sqrt(_radius*_radius - x*x);
        if(shape==shp.ellipse)
            return -(int)(_b*Math.sqrt(_a*_a-x*x)/_a);     
        if(shape==shp.hyperbola)
            return -(int)(Math.sqrt(-_b*_b*(_a*_a-x*x))/_a);    
        if(shape==shp.parabola)
            return -(int)(2*Math.sqrt(_p)*Math.sqrt(x));     
        if(shape==shp.test)
            return -x*x;
        
        return 0;
    }
}
