package it.unibs.eps.ludogame.client;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibs.eps.ludogame.networking.ProvaServer;

//import it.unibs.eps.ludogame.networking.ServerGameLudo;

public class HostWaitingRoom extends JFrame {

	private JPanel contentPane;
	private int contaGiocatori = 1;
	private JLabel[] arrJlabel;
	private int numGiocatori;
	private boolean lobbystate = false;
	private JFrame frame;
	private String ip;
	private String nomeGiocatore;
	private ProvaServer s;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) {
	 * 
	 * EventQueue.invokeLater(new Runnable() {
	 * public void run() {
	 * try {
	 * HostWaitingRoom frame = new HostWaitingRoom("Paolo",numGiocatori);
	 * frame.setVisible(true);
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * });
	 * }
	 */

	public ProvaServer getS() {
		return s;
	}

	public void setS(ProvaServer s) {
		this.s = s;
	}

	/**
	 * Create the frame.
	 */
	public HostWaitingRoom(String nomehost, int numGiocatori) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		try {
			ip = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.numGiocatori = numGiocatori;
		this.frame = this;
		this.arrJlabel = new JLabel[4];
		this.nomeGiocatore = nomehost;
		System.out.println("cipolla");
		setResizable(false);
		setTitle("Ludo");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelnomeIP = new JLabel("IP Partita");
		labelnomeIP.setFont(new Font("MV Boli", Font.PLAIN, 20));
		labelnomeIP.setBounds(53, 65, 108, 33);
		contentPane.add(labelnomeIP);

		JLabel lblListaGiocatori = new JLabel("Lista Giocatori");
		lblListaGiocatori.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblListaGiocatori.setBounds(53, 125, 154, 33);
		contentPane.add(lblListaGiocatori);

		JButton btnNext = new JButton("Avvia");
		btnNext.setEnabled(true);

		btnNext.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		btnNext.setBounds(140, 295, 134, 31);
		contentPane.add(btnNext);

		JLabel lblIp = new JLabel(ip);
		lblIp.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblIp.setBounds(206, 65, 177, 33);
		contentPane.add(lblIp);

		arrJlabel[0] = new JLabel(nomehost);
		arrJlabel[0].setFont(new Font("MV Boli", Font.PLAIN, 20));
		arrJlabel[0].setBounds(229, 124, 154, 33);
		contentPane.add(arrJlabel[0]);

		arrJlabel[1] = new JLabel("");
		arrJlabel[1].setFont(new Font("MV Boli", Font.PLAIN, 20));
		arrJlabel[1].setBounds(229, 152, 154, 33);
		contentPane.add(arrJlabel[1]);

		arrJlabel[2] = new JLabel("");
		arrJlabel[2].setFont(new Font("MV Boli", Font.PLAIN, 20));
		arrJlabel[2].setBounds(229, 195, 154, 33);
		contentPane.add(arrJlabel[2]);

		arrJlabel[3] = new JLabel("");
		arrJlabel[3].setFont(new Font("MV Boli", Font.PLAIN, 20));
		arrJlabel[3].setBounds(229, 240, 154, 33);
		contentPane.add(arrJlabel[3]);

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Controlli su numero eventualmente

				System.out.println("Avvia");
				s.creaBot();
				s.gestioneTurnoIniziale();
				s.creaMainFrame();
				s.inizioGame();
				frame.dispose();

			}
		});

	}

	public void addPlayer(String nome) {
		if (this.contaGiocatori < numGiocatori) {
			this.arrJlabel[this.contaGiocatori].setText(nome);
			this.contaGiocatori++;
		}
		contentPane.repaint();
	}
}
