package it.unibs.eps.ludogame.client;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SidePanel extends JPanel {

	private JButton rollButton;
	PlayerPanel[] listaPlayerPanel=new PlayerPanel[4];
	private  ImageIcon diceImage;
	private JLabel rollLabel;
	private int nGiocatori;
	/**
	 * Create the panel.
	 */
	public SidePanel(String[] nome) {
		Random rand = new Random();
		setLayout(new BorderLayout(0, 0));
		
		JPanel toppanel = new JPanel();
		add(toppanel, BorderLayout.CENTER);
		toppanel.setLayout(new GridLayout(4,0, 0, 0));
		
		for(int i=0;i<nome.length;i++) {
			listaPlayerPanel[i]=new PlayerPanel(i,nome[i]);
			toppanel.add(listaPlayerPanel[i]);
		}
		this.nGiocatori=nome.length;
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		this.diceImage = new ImageIcon("dice0.png");
		
		this.rollLabel = new JLabel(diceImage);
		this.rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(this.rollLabel, BorderLayout.NORTH);
		
		rollButton = new JButton("LANCIA");
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				

				
				int n = rand.nextInt(6)+1;
				dadoAnimation(n);
				//chiamo funzione che prende dado
			}
		});
		panel_1.add(rollButton);
		
		
		
		
	}
	
	public void setRollButton(boolean state) {
		this.rollButton.setEnabled(state);
	}
	
	/**
	 * funzione che richiamo sia su client attivo che gli altri per fare vedere numero che esce
	 * @param n
	 */
	public void dadoAnimation(int n) {
		diceImage=new ImageIcon("dice"+n+".png");
		rollLabel.setIcon(diceImage);
	}
	
	
	public void setTurno(int player) {
		
		for(int i=0;i<this.nGiocatori;i++) 
			listaPlayerPanel[i].setTurno(i==player);

	}

}
