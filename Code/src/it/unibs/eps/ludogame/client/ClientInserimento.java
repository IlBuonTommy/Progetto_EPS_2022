package it.unibs.eps.ludogame.client;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import it.unibs.eps.ludogame.networking.ProvaClient;
/**
 * 
 * Classe che si occupa di generare il frame dove il client fa login
 *
 */
public class ClientInserimento extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientInserimento frame = new ClientInserimento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creo il frame
	 */
	public ClientInserimento() {
		setTitle("Ludo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		frame = this;
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelnome = new JLabel("Nome Giocatore:");
		labelnome.setFont(new Font("MV Boli", Font.PLAIN, 20));
		labelnome.setBounds(26, 53, 178, 33);
		contentPane.add(labelnome);

		JTextField textFieldNome = new JTextField();
		textFieldNome.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		textFieldNome.setBounds(242, 58, 172, 29);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNumeroGiocatori = new JLabel("IP partita");
		lblNumeroGiocatori.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNumeroGiocatori.setBounds(53, 140, 108, 33);
		contentPane.add(lblNumeroGiocatori);

		JButton btnNext = new JButton("Unisciti");

		btnNext.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		btnNext.setBounds(154, 222, 134, 31);
		contentPane.add(btnNext);

		Icon imageIcon = new ImageIcon("back.png");
		JButton btnBack = new JButton(imageIcon);
		btnBack.setBounds(0, 10, 43, 33);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Login login = new Login();
					login.setLocationRelativeTo(null);
					login.setVisible(true);
					frame.dispose();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnBack);

		textFieldIP = new JTextField();
		textFieldIP.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		textFieldIP.setColumns(15);
		textFieldIP.setBounds(242, 143, 172, 29);
		contentPane.add(textFieldIP);

		btnNext.addActionListener(new ActionListener() {
			@Override
			public synchronized void actionPerformed(ActionEvent e) {
				// Controlli su ip e se connette
				if (!textFieldNome.getText().isEmpty() && !textFieldNome.getText().isBlank()) {
					
					ClientWaitingRoom clientframe = new ClientWaitingRoom();
					ProvaClient client = new ProvaClient(textFieldIP.getText(), textFieldNome.getText(), clientframe);

				
					if (client.checkConnection()) {

						client.comunica();

						frame.dispose();
						clientframe.setVisible(true);
						clientframe.setLocationRelativeTo(null);
						Thread t = new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								clientframe.richiediModel(client);
							}

						});
						t.start();

					} else {

						JOptionPane.showMessageDialog(frame, "Errore inserimento IP", "Connection Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "I campi non possono essere vuoti", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

}
