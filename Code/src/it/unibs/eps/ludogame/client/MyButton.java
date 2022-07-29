package src.it.unibs.eps.ludogame.client;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MyButton extends JButton {
	
	private boolean draw;
	private String color;
	private boolean doppio;
	
	public MyButton () {
		draw=false;
		color="#ffffff";
		doppio=false;
	}
	
	 public void paintComponent(Graphics g) {
	       super.paintComponent(g);
	       if(draw) {
	    	   g.setColor(Color.decode(color));
	    	   int w=getWidth();
		       int h=getHeight();
		       int min=w;
		       
		       if(min>h)
		    	   min=h;
		       min*=0.8;
		       int x=(w-min)/2;
		       int y=(h-min)/2;
		       
		       g.fillOval(x, y, min, min);
		       g.setColor(Color.BLACK);
		       g.drawOval(x, y, min, min);
		       
		       if(this.doppio) {
		    	   JLabel label = new JLabel("D");
		    	   label.setAlignmentX((float) 0.55);
		    	   label.setAlignmentY((float) 0.55);
		    	   label.setForeground(Color.white);
		   		   add(label);
		       }
		       
	       }
	       
	    }
	 
	 public void setState(boolean state,String color) {
		 this.draw=state;
		 this.color=color;
		 
	 }
	 
	 public void setState(boolean state) {
		 this.draw=state;
	 }
	 
	 public void setState(boolean state,String color,boolean doppio) {
		 this.draw=state;
		 this.color=color;
		 this.doppio=doppio;
		 
	 }
	 
	 public void setState(boolean state,boolean doppio) {
		 this.draw=state;
		 this.doppio=doppio;
	 }
	 
}
