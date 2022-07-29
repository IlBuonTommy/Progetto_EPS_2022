package src.it.unibs.eps.ludogame.client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class Board extends JPanel {
	
	private static String blupedina="#0E5DF1";
	private static String redpedina="#E74C3C";
	private static String verdepedina="#2AC503";
	private static String giallopedina="#FCFC5C";
	private static String blucasella="#0000FF";
	private static String redcasella="#FF0000";
	private static String verdecasella="#008000";
	private static String giallocasella="#E1E100";
	private static String white="#FFFFFF";
	
	
	
	/**
	 * Create the panel.
	 */	public Board() {
		this.setLayout(new GridLayout(11, 11, 0, 0));
		ArrayList a=new ArrayList();
		for(int i=0;i<11*11;i++) {
			MyButton b = new MyButton(i);
			b.setBackground(Color.decode(getColor(i)));
			b.setOpaque(true);
			
			if(getBorder(i)) {
				
		    	b.setBorder(new RoundedBorder(10));
		    	setComponentZOrder(b, i);
		    	
		    	if(i==0||i==5||i==11||i==6)
		    		b.setState(true,redpedina);
		    	if(i==110||i==111||i==92||i==70)
		    		b.setState(true,giallopedina);
		    	if(i==10||i==73)
		    		b.setState(true,blupedina);
		    	if(i==74)
		    		b.setState(true,blupedina,true);
		    	if(i==45||i==54)
		    		b.setState(true,verdepedina);
		    	if(i==66)
		    		b.setState(true,verdepedina,true);
		    	
		    	b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						MyButton o = (MyButton)e.getSource();
						o.getPosition();
						
						
					}
				}
				);
			}	
			else {
				b.setBorderPainted(false);
				b.setContentAreaFilled(false);
			}
				
			
			this.add(b,-1);
		}
		
		
	   
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w=getWidth();
		int h=getHeight();
		int x1=(int) (w*0.3);
		int y1=(int) (h*0.2);
		int x2=(int) (w*0.7);
		int y2=(int) (h*0.7);
		
		int dy=(int) (h*0.1);
		int dx=dy/5;
		//disegno freccia sulla sinistra
		g.drawLine(x1,y1 ,x1 ,y1+dy );
		g.drawLine(x1, y1, x1+dx, y1+dx);
		g.drawLine(x1, y1, x1-dx, y1+dx);
		g.drawLine(x1, y1+dy, x1-dx*2, y1+dy);
		
		
		//disegno freccia sulla destra
		g.drawLine(x2,y2 ,x2 ,y2+dy );
		g.drawLine(x2, y2+dy, x2+dx, y2+dy-dx);
		g.drawLine(x2, y2+dy, x2-dx, y2+dy-dx);
		g.drawLine(x2, y2, x2+dx*2, y2);
		
		int cx=(int)w/2;
		int cy=(int)h/2;
		
		int qx=(int)w/22;
		int qy=(int)h/22;
		
		int rx=6*qx;
		int ry=6*qy;
		//disegno il triangolino in mezzo e disegno del cerchio base
		g.setColor(Color.decode(giallocasella));
		g.fillPolygon(new int[]{cx,cx+qx,cx-qx},new int[]{cy,cy+qy,cy+qy},3);
		g.fillOval(-rx,h-ry,2*rx,2*ry);
		
		g.setColor(Color.decode(blucasella));
		g.fillPolygon(new int[]{cx,cx+qx,cx-qx},new int[]{cy,cy-qy,cy-qy},3);
		g.fillOval(w-rx,-ry,2*rx,2*ry);
		
		g.setColor(Color.decode(verdecasella));
		g.fillPolygon(new int[]{cx,cx+qx,cx+qx},new int[]{cy,cy+qy,cy-qy},3);
		g.fillOval(w-rx,h-ry,2*rx,2*ry);
		
		g.setColor(Color.decode(redcasella));
		g.fillPolygon(new int[]{cx,cx-qx,cx-qx},new int[]{cy,cy+qy,cy-qy},3);
		g.fillOval(-rx,-ry,2*rx,2*ry);
		g.setColor(Color.BLACK);
		g.drawLine(cx-qx,cy+qy ,cx+qx , cy+qy);
		
		 Graphics2D g2 = (Graphics2D)g;
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		     RenderingHints.VALUE_ANTIALIAS_ON);
	}
	

	private String getColor(int n) {
			
			//verde
			
			if(n>=11*9+9 && n<11*10|| (n>=11*10+9)||(n==11*7-1))
				return verdecasella;
			
			if(n>11*5+5 & n<11*6-1)
				return verdecasella;
			
			//giallo
			
			if(n>=11*9 && n<11*9+2|| (n>=11*10 && n<11*10+2)||(n==11*10+4))
				return giallocasella;
			
			if(n%11==5 & n>11*6 & n<11*10)
				return giallocasella;
			
			//rosso
			if(n<2|| (n<13&n>10)||(n==11*4))
				return redcasella;
			
			if(n>11*5 & n<11*5+5)
				return redcasella;
			
			//blu
			if((n<11 & n>8)|| (n<22 & n>19) ||(n==6))
				return blucasella;
			if(n%11==5 & n<11*5 & n>11)
				return blucasella;
			
			return white;
		}
	
	
	
	
	private boolean getBorder(int n) {
		//creo buco in centro
		if(n==11*5+5)
			return false;
		//mostro 3 righe centrali
		if(n>=11*4 & n<11*7)
			return true;
		//mostro 3 colonne centrali
		if(n%11==4||n%11==5 || n%11==6)
			return true;
		//mostro i 4 angoli
		if(n<2|| (n<13&n>10))
			return true;
		
		if((n<11&n>8)|| (n<22&n>19))
			return true;
		
		if(n>=11*9 && n<11*9+2|| (n>=11*10 && n<11*10+2))
			return true;
		
		if(n>=11*9+9 && n<11*10|| (n>=11*10+9))
			return true;
		
		return false;
	}

}
