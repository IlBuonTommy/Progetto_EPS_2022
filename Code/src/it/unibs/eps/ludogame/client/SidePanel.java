package it.unibs.eps.ludogame.client;
import java.awt.BorderLayout;
import java.util.Random;
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

	private JButton rollButton;
	/**
	 * Create the panel.
	 */
	public SidePanel() {
		setLayout(new BorderLayout(0, 0));
		Random rand=new Random();
		JPanel toppanel = new JPanel();
		add(toppanel, BorderLayout.CENTER);
		toppanel.setLayout(new GridLayout(4,0, 0, 0));
		
		PlayerPanel ppanel0=new PlayerPanel(0,"Paolo");
		toppanel.add(ppanel0);
		
		
		
		PlayerPanel ppanel1=new PlayerPanel(1,"Pippo");
		toppanel.add(ppanel1);
		
		ppanel1.setTurno(true);
		
		PlayerPanel ppanel2=new PlayerPanel(2,"Leo");
		toppanel.add(ppanel2);
		
		PlayerPanel ppanel3=new PlayerPanel(3,"Sium");
		toppanel.add(ppanel3);
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("IMMAGINE DADO");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2, BorderLayout.NORTH);
		
		rollButton = new JButton("LANCIA");
		rollButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("LANCIO");
				int n = rand.nextInt(6)+1;
				//richiamo metodo a cui serve lancio
				dadoAnimation(n);
			}
		});
		panel_1.add(rollButton);
		
		
		
		
	}
	
	public void setRollButton(boolean state) {
		this.rollButton.setEnabled(state);
	}
	
	/**
	 * funzione che richiamo sia su client attivo che gli altri per fare vedere numero che esce
	 * @param n
	 */
	public void dadoAnimation(int n) {
		
	}

}
