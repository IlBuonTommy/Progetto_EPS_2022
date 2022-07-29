package src.it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.Component;

public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		Board p=new Board();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel.add(textField, BorderLayout.EAST);
		textField.setColumns(10);
		setMinimumSize(new Dimension(600,600));
		setMaximumSize(new Dimension(700,700));
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		getContentPane().add(p);
		getContentPane().add(panel, BorderLayout.EAST);
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		System.out.println(getWidth());
	}
	
	

}
