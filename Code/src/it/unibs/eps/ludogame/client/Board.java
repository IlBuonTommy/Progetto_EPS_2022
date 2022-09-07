package it.unibs.eps.ludogame.client;

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

import it.unibs.eps.ludogame.game.Casella;

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
	private MyButton boardButton[]=new MyButton[40];
	private MyButton baseButton[][]=new MyButton[4][4];
	private MyButton fineButton[][]=new MyButton[4][4];
	
	
	//RED(0),BLUE(1),GREEN(2),YELLOW(3),EMPTY(-1)
    //3 array di caselle: plancia[40] base[colore][4] finale[colore][4]
	
	/**
	 * Create the panel.
	 */	public Board() {
		this.setLayout(new GridLayout(11, 11, 0, 0));
		
		
		
		//creo ciclo data una griglie di 11*11
		for(int y=0;y<11;y++)
		for(int x=0;x<11;x++){
			
			int i=y*11+x;
			
			MyButton b = new MyButton(i);
			b.setBackground(Color.decode(getColor(i)));
			b.setOpaque(true);
			//controllo se è un bottone visibile
			if(isVisible(i)) {
				//inserisco bottone in matrix base
				if(b.getButtonPosition().getNomeposizione()==Posizione.NomePosizione.Base) {
					this.baseButton[b.getButtonPosition().getColor()][b.getButtonPosition().getArrayposizione()]=b;
				}else {
					if(b.getButtonPosition().getNomeposizione()==Posizione.NomePosizione.Fine) {
						this.fineButton[b.getButtonPosition().getColor()][b.getButtonPosition().getArrayposizione()]=b;
					}else {
						this.boardButton[b.getButtonPosition().getArrayposizione()]=b;
					}
					
				}
				
		    	b.setBorder(new RoundedBorder(10));
		    	
		    
		    	
		 	
		    	//aggiungo un action listener ai bottoni
		    	b.addActionListener(new ActionListener() {
		    		
					@Override
					public void actionPerformed(ActionEvent e) {
						MyButton o = (MyButton)e.getSource();
						o.getButtonPosition();
						
						
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
		//ottengo dimensioni
		int w=getWidth();
		int h=getHeight();
		
		//calcolo posizioni di partenza delle 2 frecce
		int x1=(int) (w*0.3);
		int y1=(int) (h*0.2);
		int x2=(int) (w*0.7);
		int y2=(int) (h*0.7);
		//calcolo lunghezze delle frecce
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
		
		//calcolo coordinate del centro e di un quadratino
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
		
		//inseirsco filtro 
		 Graphics2D g2 = (Graphics2D)g;
		 g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		     RenderingHints.VALUE_ANTIALIAS_ON);
		 
	}
	
	/**
	 * disattivo tutti i bottoni
	 */
	public void disableall() {
		
		for (int i=0;i<40;i++) {
			this.boardButton[i].setEnabled(false);
			
		}
		
		for(int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				this.baseButton[i][j].setEnabled(false);
				
				this.fineButton[i][j].setEnabled(false);
				
			}
		}
	}
	
	/**
	 * dato un insieme di Posizioni attivo quei bottoni
	 * @param arrpos
	 */
	public void enableButton(Posizione[] arrpos) {
		for (int i=0;i<arrpos.length;i++) {
			Posizione.NomePosizione nompos=arrpos[i].getNomeposizione();
			int color=arrpos[i].getColor();
			int posizione=arrpos[i].getArrayposizione();
			if(nompos==Posizione.NomePosizione.Base) {
				this.baseButton[color][posizione].setEnabled(true);
			}else {
				if(nompos==Posizione.NomePosizione.Fine) {
					this.fineButton[color][posizione].setEnabled(true);
				}else {
					this.boardButton[posizione].setEnabled(true);
				}
			}
		}
	}
	
/**
 * dato la posizione in input ritorna il colore della casella
 * @param n
 * @return
 */
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
	
		/**
		 * setta come false lo stato di tutte le caselle
		 * controlla gli array passati dal model e setta tutte le caselle
		 * @param board
		 * @param base
		 * @param fine
		 */
		public void resetta(Casella[] board, Casella[][] base,Casella[][] fine) {
			System.out.println("ENTRA IN REPAINT");
			
			for (int i=0;i<40;i++) {
				this.boardButton[i].setState(false);
				if(board[i].getColore()>-1) {
					
					this.boardButton[i].setState(true,board[i].getColore(),board[i].getDoppio());
				}
			}
			
			for(int i=0;i<4;i++) {
				for (int j=0;j<4;j++) {
					this.baseButton[i][j].setState(false);
					
					if(base[i][j].getColore()>-1) {
						System.out.println("sono nell'if");
						this.baseButton[i][j].setState(true,base[i][j].getColore(),base[i][j].getDoppio());
					}
					
					this.fineButton[i][j].setState(false);
					
					if(fine[i][j].getColore()>-1) {
						this.fineButton[i][j].setState(true,fine[i][j].getColore(),fine[i][j].getDoppio());
					}
				}
			}
			
			
		}
	
	/**
	 * dato un intero restituisce un true se il bottone fa parte del tabellone
	 * @param n
	 * @return
	 */
	private boolean isVisible(int n) {
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
		//alto a sinistra
		if(n<2|| (n<13&n>10))
			return true;
		//alto a destra
		if((n<11&n>8)|| (n<22&n>19))
			return true;
		//basso a sinistra
		if(n>=11*9 && n<11*9+2|| (n>=11*10 && n<11*10+2))
			return true;
		//basso a destra
		if(n>=11*9+9 && n<11*10|| (n>=11*10+9))
			return true;
		
		return false;
	}

	
	
}
