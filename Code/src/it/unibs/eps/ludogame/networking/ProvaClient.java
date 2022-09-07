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
	private boolean isMyTurn = true;
	private Posizione posizioneUtente;
	private  int valoreDado;
	private ClientWaitingRoom clientFrame;
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
	
	public void avviaMainFrame() {
		String[] listaGiocatori = new String[modelClient.getPlayer().length];

		for(int i = 0;i<listaGiocatori.length;i++) {
			listaGiocatori[i] = modelClient.getPlayer()[i].getUsername();
		}

		MainFrame framePrincipale = new MainFrame(listaGiocatori);
		framePrincipale.setVisible(true);
		framePrincipale.setLocationRelativeTo(null);
		clientFrame.closeFrame();
	}
	
}
