/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linedistance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.math.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 *
 * @author nc184
 */
public class LineDistance {

   public static void main(String[] args) {
        JFrame jf = new JFrame("Line Distance");
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawingPanel mp = new DrawingPanel();
        jf.add(mp);
        mp.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent m){
                if (m.getButton()==MouseEvent.BUTTON1){
                    Point p = new Point(m.getX(),m.getY());
                    mp.addPoint(p);
                    mp.repaint();
                }
            }
        }
        );
        jf.setVisible(true);
    }
}

class Point{
    int x;
    int y;
    Point(int newx, int newy){
        x = newx; y=newy;
    }

    Point(){
        this(0,0);
    }
}

class DrawingPanel extends JPanel{
    ArrayList<Point> al;
    final int size=10;
    DrawingPanel(){
        super();
        al = new ArrayList<Point>();
    }
    void addPoint(Point p){ 
        al.add(p);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        for (int i = 0; i < al.size(); i++){
            int xcord = al.get(i).x;
            int ycord = al.get(i).y;
            g.fillOval(xcord-(size/2), al.get(i).y - (size/2), size, size);
            g.drawString("(" + xcord + ","+ ycord + ")", xcord, ycord+20);
            if (i != 0){
                int xcord2 = al.get(i-1).x;
                int ycord2 = al.get(i-1).y;
                double distance = Math.sqrt(Math.pow(xcord-xcord2, 2) + Math.pow(ycord-ycord2, 2));
                NumberFormat formatter = new DecimalFormat("#0.00"); 
                String distanceRound = formatter.format(distance);
                g.drawLine(xcord, ycord, xcord2, ycord2);
                g.drawString(String.valueOf(distanceRound), (xcord+xcord2)/2, (ycord+ycord2)/2);
            }
        }
    }
    
}