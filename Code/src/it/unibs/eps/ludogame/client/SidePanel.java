package it.unibs.eps.ludogame.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * pannello laterale 
 * 
 *
 */
public class SidePanel extends JPanel {

	private JButton rollButton;
	PlayerPanel[] listaPlayerPanel = new PlayerPanel[4];
	private ImageIcon diceImage;
	private JLabel rollLabel;
	private int nGiocatori;
	private MainFrame framePrincipale;

	/**
	 * Creo il panel.
	 */
	public SidePanel(String[] nome, MainFrame framePrincipale) {
		Random rand = new Random();
		setLayout(new BorderLayout(0, 0));
		this.framePrincipale = framePrincipale;
		JPanel toppanel = new JPanel();
		add(toppanel, BorderLayout.CENTER);
		toppanel.setLayout(new GridLayout(4, 0, 0, 0));

		for (int i = 0; i < nome.length; i++) {
			listaPlayerPanel[i] = new PlayerPanel(i, nome[i]);
			toppanel.add(listaPlayerPanel[i]);
		}
		this.nGiocatori = nome.length;

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

				int n = rand.nextInt(6) + 1;
				dadoAnimation(n);
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						framePrincipale.sendDado(n);
					}

				});
				t.start();
				setRollButton(false);
				// chiamo funzione che prende dado

			}
		});
		panel_1.add(rollButton);

	}
	
	/**
	 * 
	 * @param state
	 */
	public void setRollButton(boolean state) {
		this.rollButton.setEnabled(state);
	}

	/**
	 * funzione che richiamo sia su client attivo che gli altri per fare vedere
	 * numero che esce
	 * 
	 * @param n
	 */
	public void dadoAnimation(int n) {
		diceImage = new ImageIcon("dice" + n + ".png");
		rollLabel.setIcon(diceImage);
	}
	
	/**
	 * in base al turno del giocatore setto le facce o spirale per ogni PlayerPanel
	 * @param player
	 */
	public void setTurno(int player) {

		for (int i = 0; i < this.nGiocatori; i++)
			listaPlayerPanel[i].setTurno(i == player);

	}

}
