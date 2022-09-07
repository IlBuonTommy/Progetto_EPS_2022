package it.unibs.eps.ludogame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import it.unibs.eps.ludogame.client.ClientWaitingRoom;
import it.unibs.eps.ludogame.client.MainFrame;
import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.GameModel;

public class ProvaClient {
	private  String SERVER_IP;
	private static final int SERVER_PORT = 50358;
	private  Socket clientSocket;
	private  String playerName;
	private  ObjectOutputStream out;
	private  ObjectInputStream in;
	private PrintWriter stampa;
	private BufferedReader leggi;
	private  GameModel modelClient = null;
	private int playerIndex;
	private Posizione posizioneUtente;
	private  int valoreDado;
	private ClientWaitingRoom clientFrame;
	private MainFrame framePrincipale;
	public ProvaClient(String serverIp,String playerName, ClientWaitingRoom clientframe) {
		this.SERVER_IP = serverIp;
		this.playerName = playerName;
		this.clientFrame = clientframe;
		
	}
	
	public boolean checkConnection() {
		System.out.println("[CLIENT]: in esecuzione.");
	
		//	clientSocket = new Socket(SERVER_IP,SERVER_PORT);
			System.out.println("primacom");
			try {
				clientSocket = new Socket(SERVER_IP,SERVER_PORT);

				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
				return false;
			}

		
	}
	
	public void richiestaModelIniziale() {
		Pacchetto p = new Pacchetto("model",null);
		try {
			out.writeObject(p);
			out.flush();
			modelClient = (GameModel)in.readObject();
			System.out.println("Model client ricevuto: " + modelClient.toString());
			for(int i=0;i<modelClient.getPlayer().length;i++) {
				if(this.playerName.equals(modelClient.getPlayer()[i])) {
					this.playerIndex=i+1;
					System.out.println("Player Index di "+this.playerName +" : "+this.playerIndex);
				}
			}
			//Avvia view
			this.avviaMainFrame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void comunica() {

				try {
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					in = new ObjectInputStream(clientSocket.getInputStream());
					out.flush();
					out.writeObject(playerName);
					out.flush();
					System.out.println("mandato");
					//richiestaModelIniziale();
					//Avviare View
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		
	}
	
	public void comunicazioneInGameDaServer() {
		try {
			while(true) {
			Pacchetto p = (Pacchetto)in.readObject();
			String tipoRicevuto = p.getType();
				if(tipoRicevuto.equals("disable")) {
					System.out.println("sono disabile");
					framePrincipale.disableAllButtons();
					
				}
				
				if(tipoRicevuto.equals("repaint")) {
					framePrincipale.resetta(modelClient.getBase(), modelClient.getFinale(), modelClient.getPlancia(), modelClient.getCurrentPlayerIndex());
				}
				
				if(tipoRicevuto.equals("setTurno")) {
					framePrincipale.setTurno((int)p.getMessage());
					if((int)p.getMessage()==this.playerIndex) {
						framePrincipale.enableRoll();
					}
				}
				
				if(tipoRicevuto.equals("setDado")) {
					framePrincipale.setDado((int)p.getMessage());
				}
				
				if(tipoRicevuto.equals("setModel")) {
					modelClient = (GameModel)p.getMessage();
				}
				
				if(tipoRicevuto.equals("setPosizioni")) {
					framePrincipale.enableBoardButtons((Posizione[])p.getMessage());
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void sendDadoToServer(int n) {

		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			out.flush();
			out.writeObject(n);
			out.flush();
			System.out.println("mandato Dado");
			//richiestaModelIniziale();
			//Avviare View
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	


}

	
	public void avviaMainFrame() {
		String[] listaGiocatori = new String[modelClient.getPlayer().length];

		for(int i = 0;i<listaGiocatori.length;i++) {
			listaGiocatori[i] = modelClient.getPlayer()[i].getUsername();
		}
		
		framePrincipale = new MainFrame(listaGiocatori,this,null);
		framePrincipale.setVisible(true);
		framePrincipale.setLocationRelativeTo(null);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				comunicazioneInGameDaServer();
				
			}
			
		});
		t.run();
		
		clientFrame.closeFrame();
	
		
	}
	
}
