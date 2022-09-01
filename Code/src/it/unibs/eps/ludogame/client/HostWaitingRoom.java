package src.it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class HostWaitingRoom extends JFrame {

	private JPanel contentPane;
	private int contaGiocatori=1;
	private JLabel[] arrJlabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HostWaitingRoom frame = new HostWaitingRoom("Paolo");
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
	public HostWaitingRoom( String nomehost) {
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
		labelnomeIP.setBounds(53, 53, 108, 33);
		contentPane.add(labelnomeIP);
		
		
		
		JLabel lblListaGiocatori = new JLabel("Lista Giocatori");
		lblListaGiocatori.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblListaGiocatori.setBounds(53, 113, 154, 33);
		contentPane.add(lblListaGiocatori);
		
		JButton btnNext = new JButton("Avvia");
		btnNext.setEnabled(false);
		
		btnNext.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 14));
		btnNext.setBounds(140, 295, 134, 31);
		contentPane.add(btnNext);
		
		JLabel lblIp = new JLabel("192.168.xyz.xyz");
		lblIp.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lblIp.setBounds(206, 53, 177, 33);
		contentPane.add(lblIp);
		
		
		
		JLabel lbl0 = new JLabel(nomehost);
		lbl0.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lbl0.setBounds(229, 112, 154, 33);
		contentPane.add(lbl0);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lbl1.setBounds(229, 152, 154, 33);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		lbl2.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lbl2.setBounds(229, 195, 154, 33);
		contentPane.add(lbl2);
		
		JLabel lbl3 = new JLabel("");
		lbl3.setFont(new Font("MV Boli", Font.PLAIN, 20));
		lbl3.setBounds(229, 240, 154, 33);
		contentPane.add(lbl3);
		
		
		btnNext.addActionListener(new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				//Controlli su numero eventualmente
				
					System.out.println("Avvia");
				
				
			}
		}
		);
	}
	
	public void addPlayer(String nome) {
		if(this.contaGiocatori<4) {
			this.arrJlabel[this.contaGiocatori].setText(nome);
			this.contaGiocatori++;
		}
		
	}

}
