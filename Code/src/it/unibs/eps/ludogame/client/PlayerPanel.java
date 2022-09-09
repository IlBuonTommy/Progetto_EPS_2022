package it.unibs.eps.ludogame.client;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
/**
 * 
 * Classe che genera la parte di pannello contenente gli avatar dei giocatori
 *
 */
public class PlayerPanel extends JPanel {

	private FacePanel fp;
	private String nome;

	/**
	 * Creo il panel.
	 */
	public PlayerPanel(int n, String nome) {

		this.nome = nome;

		JLabel lblNewLabel_1 = new JLabel(nome);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBackground(Color.RED);
		this.add(lblNewLabel_1);

		this.fp = new FacePanel(n);

		this.add(this.fp);

	}
	/**
	 * richiama facePanel setTurno
	 * @param n
	 */
	public void setTurno(boolean n) {
		this.fp.setTurno(n);
	}
	
	/**
	 * setta il nome di questo player
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
