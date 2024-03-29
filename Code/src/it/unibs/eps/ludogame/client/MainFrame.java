package it.unibs.eps.ludogame.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibs.eps.ludogame.game.Casella;
import it.unibs.eps.ludogame.networking.ProvaClient;
import it.unibs.eps.ludogame.networking.ProvaServer;
/**
 * classe che genera tutta l'interfaccia principale
 *
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Board board;
	private SidePanel panel;
	private ProvaClient client;
	private ProvaServer server;

	/**
	 * Launch the application.
	 */

	/**
	 * Creo il frame.
	 */
	public MainFrame(String[] giocatori, ProvaClient client, ProvaServer server) {
		
		this.client = client;
		this.server = server;
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 700);
		this.board = new Board(this);

		this.panel = new SidePanel(giocatori, this);
		panel.setTurno(0);
		this.setMinimumSize(new Dimension(600, 600));
		this.setMaximumSize(new Dimension(900, 800));
		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(board);
		board.disableall();

		getContentPane().add(panel, BorderLayout.EAST);

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
	 * abilita movimenti nella board
	 * 
	 * @param posizioni
	 */
	public void enableBoardButtons(Posizione[] posizioni) {

		this.board.enableButton(posizioni);
	}

	/**
	 * richiama il metodo di sidePanel
	 * 
	 * @param giocatore
	 */
	public void setTurno(int giocatore) {
		this.panel.setTurno(giocatore);
	}
	
	/**
	 * richiama setDado del Panel
	 * @param dado
	 */
	public void setDado(int dado) {
		this.panel.dadoAnimation(dado);
	}
	
	/**
	 * prende in input il modello dati del tabellone e il currentPlayer, richiama board e sidepanel
	 * @param baseV
	 * @param fineV
	 * @param boardV
	 * @param currentPlayerV
	 */
	public void resetta(Casella[][] baseV, Casella[][] fineV, Casella[] boardV, int currentPlayerV) {
		
		this.board.resetta(boardV, baseV, fineV);
		this.panel.setTurno(currentPlayerV);

		this.repaint();
		
	}

	/**
	 * invocato da click del bottone lancia
	 * invia dado a server o client
	 * @param n
	 */
	public void sendDado(int n) {
		if (this.client == null) {

			server.starTurnoDue(n);
		} else {
			// chiamo ProvaClient e gli faccio inviare il dado
			client.sendDadoToServer(n);
		}

	}

	/**
	 * invocato da click del bottone sul Tabellone
	 * invia posizione a server o client
	 * @param n
	 */
	public void sendPosition(Posizione p) {
		if (this.client == null) {
			server.startTurnoTre(p);
		} else {
			// chiamo ProvaClient e gli faccio inviare la posizione
			client.sendPositionToServer(p);
		}
	}

}
