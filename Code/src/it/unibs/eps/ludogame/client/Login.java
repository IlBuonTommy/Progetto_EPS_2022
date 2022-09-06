package it.unibs.eps.ludogame.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setTitle("Ludo");
		frame = this;

		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("L");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 40));
		lblNewLabel.setBounds(150, 20, 31, 39);
		panel.add(lblNewLabel);

		JLabel lblT = new JLabel("T");
		lblT.setForeground(Color.RED);
		lblT.setFont(new Font("MV Boli", Font.PLAIN, 30));
		lblT.setBounds(164, 180, 31, 39);
		panel.add(lblT);

		JLabel lblA = new JLabel("A");
		lblA.setForeground(Color.ORANGE);
		lblA.setFont(new Font("MV Boli", Font.PLAIN, 30));
		lblA.setBounds(192, 179, 31, 39);
		panel.add(lblA);

		JLabel lblP = new JLabel("P");
		lblP.setForeground(Color.BLUE);
		lblP.setFont(new Font("MV Boli", Font.PLAIN, 30));
		lblP.setBounds(226, 181, 31, 39);
		panel.add(lblP);

		JLabel lblAvviaInModalit = new JLabel("Avvia in modalit\u00E0:");
		lblAvviaInModalit.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblAvviaInModalit.setBounds(125, 69, 204, 39);
		panel.add(lblAvviaInModalit);

		JButton btnHost = new JButton("Host");
		btnHost.setBounds(85, 130, 85, 21);

		btnHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					HostInserimento framehost = new HostInserimento();
					framehost.setLocationRelativeTo(null);
					framehost.setVisible(true);
					frame.dispose();

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnHost);

		JButton btnClient = new JButton("Client");
		btnClient.setBounds(244, 130, 85, 21);

		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientInserimento frameclient = new ClientInserimento();
				frameclient.setLocationRelativeTo(null);
				frameclient.setVisible(true);
				frame.dispose();
			}
		});

		panel.add(btnClient);

		JLabel lblU = new JLabel("U");
		lblU.setForeground(Color.BLUE);
		lblU.setFont(new Font("MV Boli", Font.PLAIN, 40));
		lblU.setBounds(179, 20, 37, 39);
		panel.add(lblU);

		JLabel lblD = new JLabel("D");
		lblD.setForeground(Color.GREEN);
		lblD.setFont(new Font("MV Boli", Font.PLAIN, 40));
		lblD.setBounds(214, 20, 31, 39);
		panel.add(lblD);

		JLabel lblO = new JLabel("O");
		lblO.setForeground(Color.ORANGE);
		lblO.setFont(new Font("MV Boli", Font.PLAIN, 40));
		lblO.setBounds(244, 20, 31, 39);
		panel.add(lblO);
	}

}
