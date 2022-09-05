package it.unibs.eps.ludogame.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import it.unibs.eps.ludogame.client.Posizione;
import it.unibs.eps.ludogame.game.GameModel;

public class ClientGameLudo {
	private  String SERVER_IP;
	private static final int SERVER_PORT = 50358;
	private  Socket clientSocket;
	private  String playerName;
	private  ObjectOutputStream out;
	private  ObjectInputStream in;
	private  GameModel model = null;
	private boolean isMyTurn = true;
	private Posizione posizioneUtente;
	private  int valoreDado;
	
	public ClientGameLudo(String serverIp,String playerName) {
		this.SERVER_IP = serverIp;
		this.playerName = playerName;
		try {
			clientSocket = new Socket(SERVER_IP,SERVER_PORT);
			this.in  = new ObjectInputStream(clientSocket.getInputStream());
			this.out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkConnection() {
		System.out.println("[CLIENT]: in esecuzione.");
	
		//	clientSocket = new Socket(SERVER_IP,SERVER_PORT);
			System.out.println("primacom");
			comunica();
			System.out.println("dopocom");
			return true;
		
	}
	
	public  void inizializza() {
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
	public  void richiediDado()
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
	public void comunica() {
		try {
			System.out.println("sono in comunica");
			
			/*model = (GameModel)in.readObject();
			inizializza();
			System.out.println(model.toString());
			System.out.println("Sono il giocatore:" + playerName);*/
			System.out.println("prima di invio");
			out.writeObject(playerName);
			System.out.println("dopo di invio");
		}catch (IOException e){
			System.err.println("Eccezione dal client");
			System.err.println(e.getMessage());
		} /*catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	/*public static void main(String[] args) {
		connect();
	}*/

}
