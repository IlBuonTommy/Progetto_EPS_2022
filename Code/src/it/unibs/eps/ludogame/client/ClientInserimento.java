package src.it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ClientInserimento extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldIP;

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
	 * Create the frame.
	 */
	public ClientInserimento() {
		setTitle("Ludo");
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
		
		textFieldIP = new JTextField();
		textFieldIP.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 15));
		textFieldIP.setColumns(15);
		textFieldIP.setBounds(242, 143, 172, 29);
		contentPane.add(textFieldIP);
		
		btnNext.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				//Controlli su ip e se connette
				if(!textFieldNome.getText().isEmpty() && !textFieldNome.getText().isBlank())
					System.out.println("apposto");
				
				
			}
		}
		);
	}

}
