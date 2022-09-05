package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.GameModel;

public class ClientGameLudo {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 50358;
	private static Socket clientSocket;
	private static String playerName;
	private static ObjectOutputStream out = null;
	private static ObjectInputStream in = null;
	private static GameModel model = null;
	private boolean isMyTurn = true;
	private Posizione posizioneUtente;
	private static int valoreDado;
	
	public static void inizializza() {
		Pacchetto p = new Pacchetto("nome",playerName);
		try {
			out.writeObject(p);
			playerName = (String)in.readObject();
			//out.writeObject(p);
			//model = (GameModel)in.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void richiediDado()
	{
		try {
			out.writeObject(new Pacchetto("dado",null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			valoreDado = (int)in.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(valoreDado);
	}
	public static void connect() {
		System.out.println("[CLIENT]: in esecuzione.");
		try {
			clientSocket = new Socket(SERVER_IP,SERVER_PORT);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			model = (GameModel)in.readObject();
			inizializza();
			System.out.println(model.toString());
			System.out.println("Sono il giocatore:" + playerName);
			while(true) {
				//svolgimento del gioco
				
				
			}
			
		}catch (IOException e){
			System.err.println("Eccezione dal client");
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		connect();
	}

}
