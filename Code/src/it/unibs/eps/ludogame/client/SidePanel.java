package it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JRadioButton;

public class SidePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SidePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(4,0, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Player1");
		lblNewLabel_1.setBackground(Color.RED);
		panel_2.add(lblNewLabel_1);
		
		JRadioButton pedina1 = new JRadioButton("");
		panel_2.add(pedina1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		
		JLabel lblPlayer = new JLabel("Player2");
		panel_3.add(lblPlayer);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		panel_3.add(rdbtnNewRadioButton);
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Player3");
		panel_4.add(lblNewLabel_3);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		panel_4.add(rdbtnNewRadioButton_1);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JLabel lblNewLabel = new JLabel("Player4");
		panel_5.add(lblNewLabel);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		panel_5.add(rdbtnNewRadioButton_2);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("IMMAGINE DADO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("LANCIA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnNewButton);
		
		
		
		
	}

}
