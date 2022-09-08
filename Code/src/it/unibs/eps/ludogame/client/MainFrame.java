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
	 * Create the frame.
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

		// board.resetta(new Casella[] {},new Casella[][] {},new Casella[][] {});

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

	public void setDado(int dado) {
		this.panel.dadoAnimation(dado);
	}

	public void resetta(Casella[][] base, Casella[][] fine, Casella[] board, int currentPlayer) {
		
		this.board.resetta(board, base, fine);
		this.panel.setTurno(currentPlayer);

		this.repaint();
		System.out.println("base:" + base[0][0].toString());
	}

	public void sendDado(int n) {
		if (this.client == null) {
			System.out.println("sono nel send dado mainframe");
			server.starTurnoDue(n);
		} else {
			// chiamo ProvaClient e gli faccio inviare il dado
			client.sendDadoToServer(n);
		}

	}

	public void sendPosition(Posizione p) {
		if (this.client == null) {
			server.startTurnoTre(p);
		} else {
			// chiamo ProvaClient e gli faccio inviare la posizione
			client.sendPositionToServer(p);
		}
	}

}
