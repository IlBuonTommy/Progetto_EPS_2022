package it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class ClientWaitingRoom extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWaitingRoom frame = new ClientWaitingRoom();
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
	public ClientWaitingRoom() {
		setTitle("Ludo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(63, 45, 300, 276);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 22, 23);
		panel.add(lblNewLabel);
		
		JLabel lblS = new JLabel("S");
		lblS.setForeground(new Color(255, 0, 0));
		lblS.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblS.setBounds(42, 10, 22, 23);
		panel.add(lblS);
		
		JLabel lblNewLabel_1_1 = new JLabel("T");
		lblNewLabel_1_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_1.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 36, 22, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("A");
		lblNewLabel_1_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_2.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(42, 36, 22, 23);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("T");
		lblNewLabel_1_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_3.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(268, 10, 22, 23);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("E");
		lblNewLabel_1_4.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_4.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_4.setBounds(236, 10, 22, 23);
		panel.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("A");
		lblNewLabel_1_5.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_5.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_5.setBounds(236, 36, 22, 23);
		panel.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("P");
		lblNewLabel_1_6.setForeground(new Color(0, 0, 255));
		lblNewLabel_1_6.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_6.setBounds(268, 36, 22, 23);
		panel.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("P");
		lblNewLabel_1_7.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_7.setBounds(136, 10, 22, 23);
		panel.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("L");
		lblNewLabel_1_8.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_8.setBounds(136, 44, 22, 23);
		panel.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_9 = new JLabel("A");
		lblNewLabel_1_9.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_9.setBounds(136, 77, 22, 23);
		panel.add(lblNewLabel_1_9);
		
		JLabel lblNewLabel_1_10 = new JLabel("R");
		lblNewLabel_1_10.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_10.setBounds(10, 118, 22, 23);
		panel.add(lblNewLabel_1_10);
		
		JLabel lblNewLabel_1_11 = new JLabel("T");
		lblNewLabel_1_11.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_11.setBounds(42, 118, 22, 23);
		panel.add(lblNewLabel_1_11);
		
		JLabel lblNewLabel_1_12 = new JLabel("I");
		lblNewLabel_1_12.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_12.setBounds(74, 118, 22, 23);
		panel.add(lblNewLabel_1_12);
		
		JLabel lblNewLabel_1_13 = new JLabel("T");
		lblNewLabel_1_13.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_13.setBounds(103, 118, 22, 23);
		panel.add(lblNewLabel_1_13);
		
		JLabel lblNewLabel_1_14 = new JLabel("A");
		lblNewLabel_1_14.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_14.setBounds(168, 118, 22, 23);
		panel.add(lblNewLabel_1_14);
		
		JLabel lblNewLabel_1_15 = new JLabel("S");
		lblNewLabel_1_15.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_15.setBounds(198, 118, 22, 23);
		panel.add(lblNewLabel_1_15);
		
		JLabel lblNewLabel_1_16 = new JLabel("T");
		lblNewLabel_1_16.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_16.setBounds(230, 118, 22, 23);
		panel.add(lblNewLabel_1_16);
		
		JLabel lblNewLabel_1_17 = new JLabel("A");
		lblNewLabel_1_17.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_17.setBounds(268, 118, 22, 23);
		panel.add(lblNewLabel_1_17);
		
		JLabel lblNewLabel_1_18 = new JLabel("P");
		lblNewLabel_1_18.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_18.setBounds(136, 161, 22, 23);
		panel.add(lblNewLabel_1_18);
		
		JLabel lblNewLabel_1_19 = new JLabel("I");
		lblNewLabel_1_19.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_19.setBounds(136, 199, 22, 23);
		panel.add(lblNewLabel_1_19);
		
		JLabel lblNewLabel_1_20 = new JLabel("A");
		lblNewLabel_1_20.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_20.setBounds(136, 236, 22, 23);
		panel.add(lblNewLabel_1_20);
		
		JLabel lblNewLabel_1_21 = new JLabel("E");
		lblNewLabel_1_21.setForeground(new Color(255, 215, 0));
		lblNewLabel_1_21.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_21.setBounds(0, 199, 22, 23);
		panel.add(lblNewLabel_1_21);
		
		JLabel lblNewLabel_1_22 = new JLabel("R");
		lblNewLabel_1_22.setForeground(new Color(255, 215, 0));
		lblNewLabel_1_22.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_22.setBounds(32, 199, 22, 23);
		panel.add(lblNewLabel_1_22);
		
		JLabel lblNewLabel_1_23 = new JLabel("Z");
		lblNewLabel_1_23.setForeground(new Color(255, 215, 0));
		lblNewLabel_1_23.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_23.setBounds(0, 236, 22, 23);
		panel.add(lblNewLabel_1_23);
		
		JLabel lblNewLabel_1_24 = new JLabel("I");
		lblNewLabel_1_24.setForeground(new Color(255, 215, 0));
		lblNewLabel_1_24.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_24.setBounds(32, 236, 22, 23);
		panel.add(lblNewLabel_1_24);
		
		JLabel lblNewLabel_1_25 = new JLabel("N");
		lblNewLabel_1_25.setForeground(new Color(34, 139, 34));
		lblNewLabel_1_25.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_25.setBounds(230, 207, 22, 23);
		panel.add(lblNewLabel_1_25);
		
		JLabel lblNewLabel_1_26 = new JLabel("I");
		lblNewLabel_1_26.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_26.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_26.setBounds(268, 207, 22, 23);
		panel.add(lblNewLabel_1_26);
		
		JLabel lblNewLabel_1_27 = new JLabel("E");
		lblNewLabel_1_27.setForeground(new Color(34, 139, 34));
		lblNewLabel_1_27.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_27.setBounds(268, 244, 22, 23);
		panel.add(lblNewLabel_1_27);
		
		JLabel lblNewLabel_1_28 = new JLabel("R");
		lblNewLabel_1_28.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_28.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNewLabel_1_28.setBounds(230, 244, 22, 23);
		panel.add(lblNewLabel_1_28);
		
		JLabel lblNewLabel_1 = new JLabel("\u263A");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(194, 6, 35, 29);
		contentPane.add(lblNewLabel_1);
	}
}
