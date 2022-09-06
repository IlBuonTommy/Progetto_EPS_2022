package it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibs.eps.ludogame.networking.ProvaClient;
import it.unibs.eps.ludogame.networking.ProvaServer;
import it.unibs.eps.ludogame.networking.ServerGameLudo;
import it.unibs.eps.ludogame.networking.TestProvaServer;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class HostInserimento extends JFrame {

	private JPanel contentPane;
	private JFrame frame;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HostInserimento frame = new HostInserimento();
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
	public HostInserimento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		setTitle("Ludo");
		frame=this;
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
		
		
		
		JLabel lblNumeroGiocatori = new JLabel("Numero Giocatori:");
		lblNumeroGiocatori.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblNumeroGiocatori.setBounds(26, 134, 178, 33);
		contentPane.add(lblNumeroGiocatori);
		
		JSlider sliderngiocatori = new JSlider();
		sliderngiocatori.setSnapToTicks(true);
		sliderngiocatori.setFont(new Font("MV Boli", Font.PLAIN, 10));
		sliderngiocatori.setToolTipText("Numero Giocatori");
		sliderngiocatori.setMinimum(2);
		sliderngiocatori.setMaximum(4);
		sliderngiocatori.setValue(3);
		sliderngiocatori.setBounds(242, 145, 172, 22);
		contentPane.add(sliderngiocatori);
		
		Label n2 = new Label("2");
		n2.setFont(new Font("MV Boli", Font.PLAIN, 15));
		n2.setBounds(242, 161, 19, 29);
		contentPane.add(n2);
		
		Label n3 = new Label("3");
		n3.setFont(new Font("MV Boli", Font.PLAIN, 15));
		n3.setBounds(320, 161, 19, 29);
		contentPane.add(n3);
		
		Label n4 = new Label("4");
		n4.setFont(new Font("MV Boli", Font.PLAIN, 15));
		n4.setBounds(395, 161, 19, 29);
		contentPane.add(n4);
		
		JButton btnNext = new JButton("Avvia Server");
		
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
		
		btnNext.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				//faccio controlli se inserito e avvio
				if(!textFieldNome.getText().isEmpty() && !textFieldNome.getText().isBlank())
				{
					
					System.out.println("actionPerformed inserimento");
					System.out.println(textFieldNome.getText());
					System.out.println(sliderngiocatori.getValue());
					ExecutorService executor = Executors.newFixedThreadPool(2);
					HostWaitingRoom frameWaiting = new HostWaitingRoom(textFieldNome.getText(),sliderngiocatori.getValue());
					ProvaServer prova = new ProvaServer(sliderngiocatori.getValue(),frameWaiting,textFieldNome.getText());
					
					executor.execute(new Runnable() {
						public void run() {
							prova.avvia();
							//s.avvia();
							
						}});
					
					executor.execute(new Runnable() {
						public void run() {
							
							frame.dispose();
							frameWaiting.setVisible(true);
							frameWaiting.setLocationRelativeTo(null);
							System.out.println("finito questo");
						}});
					

				}
				
			}
		}
		);
	}
}
