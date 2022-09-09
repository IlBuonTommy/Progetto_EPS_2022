package it.unibs.eps.ludogame.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Sconfitta extends JFrame {

	private JPanel contentPane;
	private String[] listaSconfitta = new String[] { "un fico secco", "un panino al salame", "uno spiedo", "20 nuggets",
			"una ferrari" };
	private JFrame frame;

	/**
	 * Create the frame.
	 * @param nomeVincitore
	 */
	public Sconfitta(String nomeVincitore) {
		frame = this;
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("dice0.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("P");
		lblNewLabel.setBounds(26, 79, 34, 63);
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblI = new JLabel("E");
		lblI.setBounds(78, 51, 34, 63);
		lblI.setForeground(Color.BLUE);
		lblI.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblT = new JLabel("R");
		lblT.setBounds(122, 32, 34, 63);
		lblT.setForeground(new Color(34, 139, 34));
		lblT.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblT_1 = new JLabel("D");
		lblT_1.setBounds(174, 21, 34, 63);
		lblT_1.setForeground(Color.ORANGE);
		lblT_1.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblO = new JLabel("E");
		lblO.setBounds(226, 21, 34, 63);
		lblO.setForeground(Color.RED);
		lblO.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblR = new JLabel("N");
		lblR.setBounds(283, 32, 34, 63);
		lblR.setForeground(Color.BLUE);
		lblR.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblI_1 = new JLabel("T");
		lblI_1.setBounds(327, 51, 34, 63);
		lblI_1.setForeground(new Color(34, 139, 34));
		lblI_1.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JLabel lblA = new JLabel("E");
		lblA.setBounds(367, 79, 34, 63);
		lblA.setForeground(Color.ORANGE);
		lblA.setFont(new Font("MV Boli", Font.PLAIN, 40));

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(184, 204, 82, 31);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});

		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel);
		contentPane.add(lblI);
		contentPane.add(lblT);
		contentPane.add(lblT_1);
		contentPane.add(lblO);
		contentPane.add(lblR);
		contentPane.add(lblI_1);
		contentPane.add(lblA);

		Random rand = new Random();
		int n = rand.nextInt(listaSconfitta.length);

		JLabel lblCosaVinci = new JLabel("Devi pagare " + listaSconfitta[n]);
		lblCosaVinci.setHorizontalAlignment(SwingConstants.CENTER);
		lblCosaVinci.setFont(new Font("MV Boli", Font.PLAIN, 15));
		lblCosaVinci.setBounds(52, 152, 333, 45);
		contentPane.add(lblCosaVinci);

		JLabel lblNomeVincitore = new JLabel("Ha vinto " + nomeVincitore);
		lblNomeVincitore.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeVincitore.setFont(new Font("MV Boli", Font.PLAIN, 15));
		lblNomeVincitore.setBounds(52, 114, 333, 45);
		contentPane.add(lblNomeVincitore);
	}
}
