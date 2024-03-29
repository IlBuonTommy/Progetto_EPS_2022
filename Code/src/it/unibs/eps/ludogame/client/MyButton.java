package it.unibs.eps.ludogame.client;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
/**
 * 
 * classe che crea un bottone personalizzato, che sarebbe la singola casella della board
 *
 */
public class MyButton extends JButton {

	private String[] listaColori = new String[] { "#E74C3C", "#0E5DF1", "#2AC503", "#FCFC5C", "#FFFFFF" };
	private int color;
	private boolean draw;
	private boolean doppio;
	private Posizione posizione;
	private JLabel label;
	
	/**
	 * costruttore
	 * @param p
	 */
	public MyButton(int p) {
		this.color = -1;
		this.draw = false;
		doppio = false;
		this.posizione = new Posizione(p);
		label = new JLabel("");
		add(label);
	}
	/**
	 * genero il componente
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// controllo se � presente una pedina
		if (color > -1) {
			String c = this.listaColori[color];
			g.setColor(Color.decode(c));

			int w = getWidth();
			int h = getHeight();
			int min = w;

			if (min > h)
				min = h;
			min *= 0.8;
			int x = (w - min) / 2;
			int y = (h - min) / 2;

			g.fillOval(x, y, min, min);
			g.setColor(Color.BLACK);
			g.drawOval(x, y, min, min);

			if (this.doppio) {
				label.setText("D");
				label.setAlignmentX((float) 0.55);
				label.setAlignmentY((float) 0.55);
				label.setForeground(Color.white);
			} else {
				label.setText("");
			}

		}

	}

	/**
	 * imposto lo stato del bottone
	 * @param state
	 * @param color
	 */
	public void setState(boolean state, int color) {
		this.draw = state;
		this.color = color;

	}

	/**
	 * imposto lo stato del bottone
	 * @param state
	 */
	public void setState(boolean state) {
		this.draw = state;
	}

	/**
	 * imposto lo stato del bottone
	 * @param state
	 * @param color
	 * @param doppio
	 */
	public void setState(boolean state, int color, boolean doppio) {
		this.draw = state;
		this.color = color;
		this.doppio = doppio;

	}

	/**
	 * imposto lo stato del bottone
	 * @param state
	 * @param doppio
	 */
	public void setState(boolean state, boolean doppio) {
		this.draw = state;
		this.doppio = doppio;
	}

	/**
	 * 
	 * @return posizione del bottone
	 */
	public Posizione getButtonPosition() {
		// System.out.println(this.posizione.getNomeposizione()+" riga
		// "+this.posizione.getColor()+" colonna "+this.posizione.getArrayposizione());
		return this.posizione;
	}

}
