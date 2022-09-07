package it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import it.unibs.eps.ludogame.game.Casella;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private Board board;
	private SidePanel panel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//devo inserire lista giocatori per creare 
					MainFrame frame = new MainFrame(new String[]{"Paoli","Tommasio","SIUM"});
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(String[] giocatori) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 700);
		this.board=new Board();
		
		this.panel=new SidePanel(giocatori) ;
		panel.setTurno(0);
		this.setMinimumSize(new Dimension(600,600));
		this.setMaximumSize(new Dimension(900,800));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(board);
		board.disableall();
		
		
		getContentPane().add(panel, BorderLayout.EAST);
		
		//board.resetta(new Casella[] {},new Casella[][] {},new Casella[][] {});
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		System.out.println(getWidth());
	}
	
	/**
	 * disabilita tutti i bottoni
	 */
	public void disableAllButtons() {
		this.board.disableall();
		this.panel.setRollButton(false);
	}
	
	/**
	 * abilita il lancio del dado
	 */
	public void enableRoll() {
		this.panel.setRollButton(true);
	}
	
	/**
	 * disabilita lancio del dado e abilita movimenti nella board
	 * @param posizioni
	 */
	public void enableBoardButtons(Posizione[] posizioni) {
		this.panel.setRollButton(false);
		
		this.board.enableButton(posizioni);
	}
	
	
	/**
	 * richiama il metodo di sidePanel
	 * @param giocatore
	 */
	public void setTurno(int giocatore) {
		this.panel.setTurno(giocatore);
	}
	
	public void resetta(Casella[][] base,Casella[][] fine,Casella[] board) {
		this.board.resetta(board, base, fine);
		this.repaint();
		System.out.println("base:" + base[0][0].toString());
	}

}
